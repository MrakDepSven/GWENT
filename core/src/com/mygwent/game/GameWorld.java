package com.mygwent.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


import static com.mygwent.game.Constants.*;
import java.util.Iterator;

/**
 * Created by Александр on 30.06.2016.
 */
public class GameWorld implements GestureDetector.GestureListener{

    // Создаем экземпляры ироков
    Player players[];

    // Игровое поле
    Battlefield battlefield;

    // Жребий (true - ход игрока, false - ход ИИ)
    boolean move;

    // Конец игры
    boolean end_game;

    // Строки для отображения информации
    BitmapFont namePlayer;      // Инфа об имени игрока
    BitmapFont amountDeck;      // Инфа о колоде
    BitmapFont brokenCard;      // Инфа о битых картах
    BitmapFont totalPower;      // Инфа о общей силе карт
    BitmapFont amountHand;      // Инфа о кол-ве карт в руке

    public GameWorld(){

        // Инициализация игроков
        players = new Player[2];
        players[0] = new Player("Enemy", 0);
        players[1] = new Player("Player", 1);

        // Генерация руки игроков
        players[1].takeCardFromDeck(10);
        players[0].takeCardFromDeck(10);

        // Бросаем жребий
        move = MathUtils.randomBoolean();

        // Инициализация битмапов
        namePlayer = new BitmapFont();
        amountDeck = new BitmapFont();
        brokenCard = new BitmapFont();
        totalPower = new BitmapFont();
        amountHand = new BitmapFont();

        // Инициализация игрового поля
        battlefield = new Battlefield(this);

        // Конец игры
        end_game = false;

        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    // отображение информации об игроках
    public void renderInfo(Batch batch) {

        batch.begin();

        // Отображение информации об игрках
        for(int i = 0; i < 2; i++){
            namePlayer.draw(batch, players[i].name, COORDINATE_PLAYER_NAME_X[i], COORDINATE_PLAYER_NAME_Y[i]);                                          // Имя
            amountDeck.draw(batch, Integer.toString(players[i].getAmountPlayerDeck()), COORDINATE_AMOUNT_IN_DECK_X[i], COORDINATE_AMOUNT_IN_DECK_Y[i]); // Карт в колоде
            brokenCard.draw(batch, Integer.toString(players[i].getAmountBrokenCard()), COORDINATE_BROKEN_CARD_X[i], COORDINATE_BROKEN_CARD_Y[i]);       // Битых карт
            totalPower.draw(batch, Integer.toString(players[i].getTotalPower()), COORDINATE_TOTAL_POWER_X[i], COORDINATE_TOTAL_POWER_Y[i]);             // Общая сила карт
            amountHand.draw(batch, Integer.toString(players[i].getAmountPlayerCard()), COORDINATE_AMOUNT_HAND_X[i], COORDINATE_AMOUNT_HAND_Y[i]);       // Карт в руке
            if(players[i].getPlayerPass()){                                                                                                             // ПАС игрока
                AssetLoader.pass_s.setPosition(COORDINATE_PASS_X[i], COORDINATE_PASS_Y[i]);
                AssetLoader.pass_s.draw(batch);
            }

            if (players[i].getAmountPlayerLives() == 2){
                batch.draw(AssetLoader.SkullSprite[i], COORDINATE_FIRST_SKULL_X[i], COORDINATE_SKULL_Y[i]);
                batch.draw(AssetLoader.SkullSprite[i], COORDINATE_SECOND_SKULL_X[i], COORDINATE_SKULL_Y[i]);
            } else if (players[i].getAmountPlayerLives() == 1){
                batch.draw(AssetLoader.SkullSprite[i], COORDINATE_FIRST_SKULL_X[i], COORDINATE_SKULL_Y[i]);
            }
        }

        batch.end();
    }

    // Ход логики игры
    public  void update(float delta){

        if(players[0].getAmountPlayerCard() == 0 || players[1].getAmountPlayerCard() == 0){
            end_game = true;

        } else if (players[0].getAmountPlayerLives() == 0 || players[1].getAmountPlayerLives() == 0){
            end_game = true;
        }else if(!move && !players[0].getPlayerPass()){
            // ИИ делает ход если может
            players[0].makeMove(battlefield);

        }
        // Передаем эстафету игроку если он может ходить
        //if(!players[1].getPlayerPass())
        move = true;
    }


    // Обработка нажатий на карты
    @Override
    public boolean tap(float x, float y, int count, int button) {

        // Игрок не спасанул и его ход
        if(!players[1].getPlayerPass() && move){

            Iterator<Card> iter = players[1].cardPlayer.iterator();

            Gdx.app.log("Первый барьер пройден!", "");

            while(iter.hasNext()){
                Card card_temp = iter.next();

                Gdx.app.log("Я в цикле!", "");

                // Проверю, есть ли карта в этой точке
                if((x >= card_temp.getX())&&(x <= card_temp.getX() + 80)
                        &&(y >= crutch_for_y(card_temp.getY()))&&(y <= crutch_for_y(card_temp.getY()) + 115)){

                    Gdx.app.log("попал по карте!", "");

                    // Помещаем карту на поле боя
                    battlefield.addCardInBattlefield(card_temp, 1);
                    // Удаляем её из руки
                    iter.remove();

                    Gdx.app.log("Отправил на стол!", "");
                    players[1].setAmountPlayerCard(-1);

                    // Даем ход компьютеру если он может ходить
                    if(!players[0].getPlayerPass()){
                        move = false;
                        Gdx.app.log("Ход противника!", "");
                    }

                    break;
                }
            }
        }
        return true;
    }
    // Костыль для Y координаты карты,
    // Устраняет зеркальность координат
    public int crutch_for_y(int y){
        switch (y){
            case 658: return 32;
            case 532: return 157;
            case 407: return 282;
            case 282: return 407;
            case 157: return 532;
            case 32: return 658;
        }
        return 0;
    }

    // Отработка нажатия на кнопку ПАС
    @Override
    public boolean longPress(float x, float y) {

        // Ловим долгий тап в область кнопки пас
        if((x >= 37)&&(x <= 142)&&(y >= 496)&&(y <= 596)){

            players[1].playerPass();
            if(!players[0].getPlayerPass()) move = false;
        }
        return true;
    }










    // Пустые методы
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }
    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
    @Override
    public void pinchStop() {

    }

}
