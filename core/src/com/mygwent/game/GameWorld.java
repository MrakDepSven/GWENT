package com.mygwent.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

import static com.mygwent.game.Constants.*;

/**
 * Created by Александр on 30.06.2016.
 */
public class GameWorld implements GestureDetector.GestureListener{

    // Создаем экземпляры ироков
    Player player;
    Player enemy_player;

    // Игровое поле
    Battlefield battlefield;

    // Жребий
    boolean first_move;

    // Строки для отображения информации
    BitmapFont namePlayer;      // Инфа об имени игрока
    BitmapFont amountDeck;      // Инфа о колоде
    BitmapFont brokenCard;      // Инфа о битых картах
    BitmapFont totalPower;      // Инфа о общей силе карт
    BitmapFont amountHand;      // Инфа о кол-ве карт в руке

    public GameWorld(){

        // Инициализация игроков
        player = new Player("Player", 1);
        enemy_player = new Player("Enemy", 0);

        // Генерация руки игроков
        player.takeCardFromDeck(10);
        enemy_player.takeCardFromDeck(10);

        // Бросаем жребий
        first_move = MathUtils.randomBoolean();

        // Инициализация битмапов
        namePlayer = new BitmapFont();
        amountDeck = new BitmapFont();
        brokenCard = new BitmapFont();
        totalPower = new BitmapFont();
        amountHand = new BitmapFont();

        // Инициализация игрового поля
        battlefield = new Battlefield(this);

        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    // отображение информации об игроках
    public void renderInfo(Batch batch) {

        batch.begin();

        // Отображение информации об игрках
        for(int i = 0; i < 2; i++){
            switch (i){
                case 0: // Инфа об игроке
                    namePlayer.draw(batch, player.name, 65, 196);                                                           // Имя
                    amountDeck.draw(batch, "000", 62, 62);   // Карт в колоде
                    brokenCard.draw(batch, Integer.toString(player.getAmountBrockenCard()), 230, 62);                       // Битых карт
                    totalPower.draw(batch, Integer.toString(player.getTotalPower()), 253, 246);                             // Общая сила карт
                    amountHand.draw(batch, Integer.toString(player.getAmountPlayerCard()), 197, 202);                       // Карт в руке
                    if(player.getPlayerPass()){/*******************/}                                                       // ПАС игрока
                    switch (player.getAmountPlayerLives()){
                        case 2: // Если все жизни целы
                            batch.draw(AssetLoader.sapphire_skull_s, 136, 239);
                            batch.draw(AssetLoader.sapphire_skull_s, 174, 239);
                            break;
                        case 1: // Если одна сгорела
                            batch.draw(AssetLoader.sapphire_skull_s, 136, 239);
                            break;
                    }
                    // сила полей боя
                    //player_power_11.draw(batch, Integer.toString(power_11), 312, 340);
                    //player_power_11.draw(batch, Integer.toString(power_12), 312, 217);
                    //player_power_11.draw(batch, Integer.toString(power_13), 312, 86);
                    break;

                case 1: // Инфа о противнике
                    namePlayer.draw(batch, enemy_player.name, 65, 519);                                                           // Имя
                    amountDeck.draw(batch, "000", 62, 673);  // Карт в колоде
                    brokenCard.draw(batch, Integer.toString(enemy_player.getAmountBrockenCard()), 232, 672);                                                                      // Битых карт
                    totalPower.draw(batch, Integer.toString(enemy_player.getTotalPower()), 253, 561);                             // Общая сила карт
                    amountHand.draw(batch, Integer.toString(enemy_player.getAmountPlayerCard()), 193, 526);                       // Карт в руке
                    if(enemy_player.getPlayerPass()){/*******************/}                                                       // ПАС противника
                    switch (player.getAmountPlayerLives()){
                        case 2: // Если все жизни целы
                            batch.draw(AssetLoader.ruby_skull_s, 134, 562);
                            batch.draw(AssetLoader.ruby_skull_s, 172, 562);
                            break;
                        case 1: // Если одна сгорела
                            batch.draw(AssetLoader.ruby_skull_s, 134, 562);
                            break;
                    }
                    // сила полей боя
                    //enemy_power_21.draw(batch, Integer.toString(power_11), 312, 469);
                    //enemy_power_22.draw(batch, Integer.toString(power_12), 312, 595);
                    //enemy_power_23.draw(batch, Integer.toString(power_13), 312, 719);

                    break;

            }
        }

        batch.end();

    }

    // Ход логики игры
    public  void update(float delta){




    }


    // Обработка нажатий на карты
    @Override
    public boolean tap(float x, float y, int count, int button) {

        Iterator<Card> iter = player.cardPlayer.iterator();

        while(iter.hasNext()){
            Card card_temp = iter.next();

            // Проверю, есть ли карта в этой точке
            if((x >= card_temp.getX())&&(x <= card_temp.getX() + 80)
                    &&(y >= crutch_for_y(card_temp.getY()))&&(y <= crutch_for_y(card_temp.getY()) + 115)){

                // Помещаем карту на поле боя
                battlefield.addCardInBattlefield(card_temp, 1);
                // Удаляем её из руки
                iter.remove();

                player.setAmountPlayerCard(-1);
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

            player.playerPass();

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
