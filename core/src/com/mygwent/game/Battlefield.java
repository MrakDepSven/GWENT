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
        for (int i = 0; i < 6; i++){ powerRender[i] = new BitmapFont(); }
        weatherEffect = new boolean[4]; for (int i = 0; i < 4; i++) { weatherEffect[i] = false; }

    }

    // Добавить карту на поле боя
    public void addCardInBattlefield(Card card, int player_check){

        int indexRow = 0;

        // Проверяем тип карты (боец / погода)
        if(card.cardSprite <= 2){ // боец

            int index = 1; // индекс поля

            // Перебираем массив полей
            for(Array<Card> field: bf){

                if(card.cardAbility == 2){ // Определение шпионской карты

                    if(index == 3 && player_check == 1) {
                        field.add(card);
                        indexRow = 2;
                        world.players[player_check].takeCardFromDeck(2);
                    }
                    if(index == 4 && player_check == 0) {
                        field.add(card);
                        indexRow = 3;
                        world.players[player_check].takeCardFromDeck(2);
                    }
                } else { // Если не шпион

                    switch (player_check) {
                        case 1: // игрок
                            if((card.cardSprite == 0) && (index == 4)) { field.add(card); indexRow = 3; }
                            if((card.cardSprite == 1) && (index == 5)) { field.add(card); indexRow = 4; }
                            if((card.cardSprite == 2) && (index == 6)) { field.add(card); indexRow = 5; }
                            break;
                        case 0: // противник
                            if((card.cardSprite == 0) && (index == 3)) { field.add(card); indexRow = 2; }
                            if((card.cardSprite == 1) && (index == 2)) { field.add(card); indexRow = 1; }
                            if((card.cardSprite == 2) && (index == 1)) { field.add(card); indexRow = 0; }
                            break;
                    }
                }
                index++;
            }

        } else { // погода

            indexRow = 6;

            switch (card.cardSprite){
                case 3:// Карта "Чистое небо"
                    weatherEffect[1] = false;
                    weatherEffect[2] = false;
                    weatherEffect[3] = false;
                    weatherSocket.clear();
                    break;
                case 4: // карта "Мороз"
                    if(!weatherEffect[1])weatherSocket.add(card);
                    weatherEffect[1] = true;
                    break;
                case 5: // карта "Мгла"
                    if(!weatherEffect[2])weatherSocket.add(card);
                    weatherEffect[2] = true;
                    break;
                case 6: // карта "Ливень"
                    if(!weatherEffect[3])weatherSocket.add(card);
                    weatherEffect[3] = true;
                    break;
            }

        }
        // Пересчитываем силу полей после добавления карты
        calculatePowerField(indexRow);
        calculateTotalPower();
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

        // Отрисовка силы ряда ***************************
        batch.begin();
        for (i = 0; i < 6; i++){
            powerRender[i].draw(batch, Integer.toString(powerField[i]), COORDINATE_POWER_X, COORDINATE_POWER_Y[i]);
        }
        batch.end();
    }

    // Расчет силы полей
    public void calculatePowerField(int indexRow){

        int i = 0;

        for(Array<Card> field: bf){

            if(i == indexRow){
                powerField[i] = calcOneRow(field);
            } else {
                powerField[i] = calcOneRow(field);
            }

            i++;
        }
    }

    // Функция рассчета силы 1 ряда карт
    public int calcOneRow(Array<Card> field){

        int powerRow = 0;

        // Цикл подсчета полученых сил
        Iterator<Card> iter = field.iterator();
        while (iter.hasNext()){
            Card card = iter.next();

            if(card.cardSprite == 0 && weatherEffect[1] == true){
                powerRow++;
            } else
            if(card.cardSprite == 1 && weatherEffect[2] == true){
                powerRow++;
            } else
            if(card.cardSprite == 2 && weatherEffect[3] == true){
                powerRow++;
            } else {
                powerRow += card.power_i;
            }

        }


        return powerRow;
    }

    public void calculateTotalPower(){

        world.players[0].totalPower = 0;
        world.players[1].totalPower = 0;

        for(int i = 0; i < 6; i++){
            if(i < 3) world.players[0].totalPower += powerField[i];
            if(i >= 3) world.players[1].totalPower += powerField[i];
        }

    }

}



