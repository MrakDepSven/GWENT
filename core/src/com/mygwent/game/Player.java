package com.mygwent.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Александр on 01.07.2016.
 */
public class Player {

    // Карты в руке
    public Array<Card> playerCard;

    private int amountPlayerCard;       // Количество карт в руке
    private int amountPlayerLives;      // Количество жизней

    // true - если игрок, false - если ИИ
    boolean player_check;


    Player(boolean player_check){

        // Проверяем игрок это или ИИ
        this.player_check = player_check;
        // Инициализация карт в руке
        playerCard = new Array<Card>();

        amountPlayerCard = 10;      // Стандартное количество карт
        amountPlayerLives = 2;      // Стандартное количество жизней
    }

    // Генерация карт в руке
    public void generateHand(){

        int random;

        for(int i = 0; i < amountPlayerCard; i++){

            random = MathUtils.random(0, 37);
            // Выбираем рандомну карту в колоду
            playerCard.add(CardDeckLoader.deckCard[random]);
        }
    }

    // Отрисовка руки игрока на игровом поле
    public void renderHand(SpriteBatch batch){


        // Итератор для пробега array
        Iterator<Card> iter = playerCard.iterator();
        int i = 0;              // счетчик
        int x_point = 1114;     // позиция первой карты по х
        int x_step = 60;        // шаг по координате х
        int y_point = 32;      // для расчета первой координаты у
        int y_step = 125;       // шаг по координатам y

        // Начало отрисовки руки
         while(iter.hasNext()){
            Card card_temp = iter.next();
            // Расчет текущего значения у


            // Если четная позиция карты
            if((i % 2) == 0) {
                card_temp.setPosition(x_point, y_point);
                card_temp.renderCard(batch);
            }
            // Если нечетная позиция карты
            else {
                card_temp.setPosition(x_point + x_step, y_point);
                card_temp.renderCard(batch);
                y_point += y_step;
            }

            i++;
        }


        //this.batch.end();

    }

    // Приянять изменение количества карт в руке
    public void setAmountPlayerCard(int amountPlayerCard) {
        this.amountPlayerCard += amountPlayerCard;
    }

    public int getAmountPlayerCard() {
        return amountPlayerCard;
    }

    public void setAmounPlayertLives(int amounPlayertLives) {
        this.amountPlayerLives -= amounPlayertLives;
    }

    public int getAmountPlayerLives() {
        return amountPlayerLives;
    }
}
