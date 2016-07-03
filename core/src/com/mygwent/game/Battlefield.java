package com.mygwent.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import static com.mygwent.game.Constants.*;
import java.util.Iterator;

/**
 * Created by Александр on 03.07.2016.
 */
public class Battlefield {

    // Экземпляр стола
    GameWorld world;

    // Массивы игровых полей                    (сверху -> вниз)[ вражеские(1, 2, 3), свои(4, 5, 6)]
    public Array<Array<Card>> bf;     // поле боя
    public Array<Card> weatherSocket; // погода
    public Array<Card> brokenCards;   // битые карты

    // Влияние погоды на поле [ 0 - чистое небо, 1 - мороз, 2 - мгла, 3 - ливень]
    public boolean weatherEffect[];

    // Общая сила ряда (значение / рендер на битмапе)
    public int powerField[];
    BitmapFont powerRender[];

    // Конструктор игрового поля
    Battlefield(GameWorld world) {
        this.world = world;

        // Инициализация игрового стола
        bf = new Array<Array<Card>>();
        for(int i = 0; i < 6; i++){
            bf.add(new Array<Card>());
        }
        // Инициализация погоды
        weatherSocket = new Array<Card>();

        // Инициализация битых карт
        brokenCards = new Array<Card>();

        // Инициализация остальных полей класса
        powerField = new int[6]; for (int i = 0; i < 6; i++) powerField[i] = 0;
        powerRender = new BitmapFont[6];

        weatherEffect = new boolean[4]; for (int i = 0; i < 4; i++) { weatherEffect[i] = false; }



    }

    // Добавить карту на поле боя
    public void addCardInBattlefield(Card card, int player_check){

        // Проверяем тип карты (боец / погода)
        if(card.cardSprite <= 2){ // боец

            int index = 1; // индекс поля

            // Перебираем массив полей
            for(Array<Card> field: bf){

                if(card.cardAbility == 2){ // Определение шпионской карты

                    switch (player_check){ // Подбрасываем шпиона сопернику и берем 2 карты
                        case 1: // игрок
                            if(index == 3) { field.add(card); world.player.takeCardFromDeck(2); }
                            break;
                        case 0: // противник
                            if(index == 4) { field.add(card); world.enemy_player.takeCardFromDeck(2); }
                            break;
                    }

                } else { // Если не шпион

                    switch (player_check) {
                        case 1: // игрок
                            if((card.cardSprite == 0) && (index == 4)) { field.add(card); }
                            if((card.cardSprite == 1) && (index == 5)) { field.add(card); }
                            if((card.cardSprite == 2) && (index == 6)) { field.add(card); }
                            break;
                        case 0: // противник
                            if((card.cardSprite == 0) && (index == 3)) { field.add(card); }
                            if((card.cardSprite == 1) && (index == 2)) { field.add(card); }
                            if((card.cardSprite == 2) && (index == 1)) { field.add(card); }
                            break;
                    }
                }
                index++;
            }

        } else { // погода

            switch (card.cardSprite){
                case 3:// Карта "Чистое небо"
                    weatherEffect[1] = false;
                    weatherEffect[2] = false;
                    weatherEffect[3] = false;
                    weatherSocket.clear();
                    break;
                case 4: // карта "Мороз"
                    weatherEffect[1] = true;        // если карта уже есть, не добавляем
                    if(weatherEffect[1])weatherSocket.add(card);
                    break;
                case 5: // карта "Мгла"
                    weatherEffect[2] = true;
                    if(weatherEffect[2])weatherSocket.add(card);
                    break;
                case 6: // карта "Ливень"
                    weatherEffect[3] = true;
                    if(weatherEffect[3])weatherSocket.add(card);
                    break;
            }

        }

        // Разрешаем рендеринг сцены
        Gdx.graphics.requestRendering();
    }

    // Рендер игрового поля
    public void renderBattlefield(SpriteBatch batch){

        int x = COORDINATE_BATTLE_SOCKET_X;
        int y, i = 0;
        int step = STEP_CARD_BATTLE[0];

        // Отрисовка поля боя **************************
        for(Array<Card> field: bf){

            y = COORDINATE_BATTLE_SOCKET_Y[i];

            Iterator<Card> iterator = field.iterator();
            while (iterator.hasNext()){

                Card temp_card = iterator.next();

                temp_card.setPosition(x, y);
                temp_card.renderCard(batch);

                x += step;
            }
            x = COORDINATE_BATTLE_SOCKET_X;
            i++;
        }

        // Отрисовка погодных карт **********************
        x = COORDINATE_WEATHER_SOCKET_XY[0];
        y = COORDINATE_WEATHER_SOCKET_XY[1];
        step = STEP_CARD_BATTLE[1];

        Iterator<Card> iterator = weatherSocket.iterator();
        while (iterator.hasNext()){
            Card temp_card = iterator.next();

            temp_card.setPosition(x, y);
            temp_card.renderCard(batch);

            x += step;
        }
    }
}
