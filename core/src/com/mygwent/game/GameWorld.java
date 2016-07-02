package com.mygwent.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import static com.mygwent.game.Constants.*;

/**
 * Created by Александр on 30.06.2016.
 */
public class GameWorld implements GestureDetector.GestureListener{

    int FPS;

    // Создаем экземпляры ироков
    Player player;
    Player enemy_player;

    // Жребий
    boolean first_move;

    // Строки для отображения информации
    BitmapFont namePlayer;      // Инфа об имени игрока
    BitmapFont amountDeck;      // Инфа о колоде
    BitmapFont brokenCard;      // Инфа о битых картах
    BitmapFont amountPower;     // Инфа о общей силе карт
    BitmapFont amountHand;      // Инфа о кол-ве карт в руке

    // Игровое поле
    // Поле противника
    Array<Card> enemy_socket_23;    // Осадные отряды противника
    Array<Card> enemy_socket_22;    // Дальнобойные отряды противника
    Array<Card> enemy_socket_21;    // Ближние отряды противнка
    // Поле игрока
    Array<Card> player_socket_11;    // Ближние отряды игрока
    Array<Card> player_socket_12;    // Дальнобойные отряды игрока
    Array<Card> player_socket_13;    // Осадные отряды игрока
    // Погодные карты
    Array<Card> weather_socket;      // Погодные карты


    public GameWorld(){

        // Инициализация игроков
        player = new Player("Player", true);
        enemy_player = new Player("Enemy", false);

        // Генерация руки игроков
        player.takeCardFromDeck(10);
        enemy_player.takeCardFromDeck(10);

        // Бросаем жребий
        first_move = MathUtils.randomBoolean();

        // Инициализация битмапов
        namePlayer = new BitmapFont();
        amountDeck = new BitmapFont();
        brokenCard = new BitmapFont();
        amountPower = new BitmapFont();
        amountHand = new BitmapFont();

        // Инициализация сокетов
        enemy_socket_23 = new Array<Card>();
        enemy_socket_22 = new Array<Card>();
        enemy_socket_21 = new Array<Card>();
        player_socket_11 = new Array<Card>();
        player_socket_12 = new Array<Card>();
        player_socket_13 = new Array<Card>();
        weather_socket = new Array<Card>();

        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    // Метод отображения информации об игроках
    public void renderInfo(Batch batch) {

        batch.begin();

        // Отображение информации об игрках
        for(int i = 0; i < 2; i++){
            switch (i){
                case 0: // Инфа об игроке
                    namePlayer.draw(batch, player.name, 57 + 8, 182 + 14);                                                           // Имя
                    amountDeck.draw(batch, Integer.toString(AMOUNT_CARD_IN_DECK - player.getAmountPlayerCard()), 59 + 3, 48 + 14);   // Карт в колоде
                    brokenCard.draw(batch, "000", 230, 48 + 14);                                                                 // Битых карт
                    amountPower.draw(batch, "000", 253, 230 + 16);                                                               // Общая сила карт
                    amountHand.draw(batch, Integer.toString(player.getAmountPlayerCard()), 197, 188 + 14);                       // Карт в руке
                    switch (player.getAmountPlayerLives()){
                        case 2: // Если все жизни целы
                            batch.draw(AssetLoader.sapphire_skull_s, 136, 239);
                            batch.draw(AssetLoader.sapphire_skull_s, 174, 239);
                            break;
                        case 1: // Если одна сгорела
                            batch.draw(AssetLoader.sapphire_skull_s, 136, 239);
                            break;
                    }
                    break;

                case 1: // Инфа о противнике
                    namePlayer.draw(batch, enemy_player.name, 53 + 12, 505 + 14);                                                            // Имя
                    amountDeck.draw(batch, Integer.toString(AMOUNT_CARD_IN_DECK - enemy_player.getAmountPlayerCard()), 65 - 3, 659 + 14);   // Карт в колоде
                    brokenCard.draw(batch, "000", 235 - 3, 659 + 13);                                                                       // Битых карт
                    amountPower.draw(batch, "000", 253, 547 + 14);                                                                      // Общая сила карт
                    amountHand.draw(batch, Integer.toString(enemy_player.getAmountPlayerCard()), 193, 512 + 14);                        // Карт в руке
                    switch (player.getAmountPlayerLives()){
                        case 2: // Если все жизни зелы
                            batch.draw(AssetLoader.ruby_skull_s, 134, 562);
                            batch.draw(AssetLoader.ruby_skull_s, 172, 562);
                            break;
                        case 1: // Если одна сгорела
                            batch.draw(AssetLoader.ruby_skull_s, 134, 562);
                            break;
                    }
                    break;

            }
        }

        batch.end();

    }

    // Метод рендера карт выложеных на стол
    public void renderDesk(SpriteBatch batch) {

        // Поле противника
        renderSocketCard(enemy_socket_23, 23, batch);
        renderSocketCard(enemy_socket_22, 22, batch);
        renderSocketCard(enemy_socket_21, 21, batch);
        // Поле игрока
        renderSocketCard(player_socket_11, 11, batch);
        renderSocketCard(player_socket_12, 12, batch);
        renderSocketCard(player_socket_13, 13, batch);
        // Карты погоды
        renderSocketCard(weather_socket, 0, batch);
    }

    // Отрисовка конкретного поля
    public void renderSocketCard(Array<Card> cards, int index, SpriteBatch batch){

        // Шаг отрисовки карт на поле
        int step = 50;

        int x = 0;
        int y = 0;

        // Координаты начала ячеек
        switch (index){
            case 23: x = 460; y = 659;
                break;
            case 22: x = 460; y = 533;
                break;
            case 21: x = 460; y = 406;
                break;
            case 11: x = 460; y = 280;
                break;
            case 12: x = 460; y = 156;
                break;
            case 13: x = 460; y = 30;
                break;
            case 0: x = 35; y = 344; step = 85;
                break;
        }

        // Итератор для пробега по картам
        Iterator<Card> iter = cards.iterator();


        // Цикл отрисовки
        while(iter.hasNext()){
            Card card_temp = iter.next();

            card_temp.setPosition(x, y); // Приняли координату
            card_temp.renderCard(batch); // Нарисовали

            x += step;
        }

    }

    // Ход игры
    public  void update(float delta){

    }


    public void DragAndDropCard(Card card_temp){
        enemy_socket_21.add(card_temp);
        Gdx.graphics.requestRendering();
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        Iterator<Card> iter = player.playerCard.iterator();

        while(iter.hasNext()){
            Card card_temp = iter.next();

            if((x >= card_temp.x)&&(x <= card_temp.x + 80)&&(y >= crutch_for_y(card_temp.y))&&(y <= crutch_for_y(card_temp.y) + 115)){
                // Помещаем карту на поле
                DragAndDropCard(card_temp);
                // Удаляем её из руки
                iter.remove();
                break;
            }
        }
        return true;
    }



    // Костыль для Y координаты карты,
    // Дает возможность правильно тапнуть по карте
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

    // Пустые методы
    @Override
    public boolean longPress(float x, float y) {



        return false;
    }
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
