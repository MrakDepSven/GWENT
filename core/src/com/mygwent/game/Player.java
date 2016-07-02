package com.mygwent.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Александр on 01.07.2016.
 */
public class Player {

    // Имя игрока
    String name;

    // Карты в руке
    public Array<Card> playerCard;

    // Индексты использованных карт
    public Array<Integer> cardNumbers;

    private int amountPlayerCard;       // Количество карт в руке
    private int amountPlayerLives;      // Количество жизней

    // true - если игрок, false - если ИИ
    boolean player_check;


    Player(String name, boolean player_check){

        // Принимаем имя игрока
        this.name = name;
        // Проверяем игрок это или ИИ
        this.player_check = player_check;
        // Инициализация карт в руке
        playerCard = new Array<Card>();
        // Инициализация индексов карт
        cardNumbers = new Array<Integer>();

        amountPlayerCard = 0;      // Начальное количество карт
        amountPlayerLives = 2;     // Стандартное количество жизней
    }

    // Взять карту из колоды
    public void takeCardFromDeck(int amountCard){

        Integer random;

        boolean i = true;

        int x_point = 1092;     // позиция первой карты по х
        int x_step = 82;        // шаг по координате х
        int y_point = 657;      // для расчета первой координаты у
        int y_step = 125;       // шаг по координатам y

        amountCard += amountPlayerCard;

        for(int ii = 0; ii < 6; ii++){

            /*
            // Если рука пустая, просто берем карту
            if(amountPlayerCard == 0){
                random = MathUtils.random(0, 37);
            }
            //Если в руке есть карта
            else{

                // Для проверки индекса взятой карты
                Iterator<Integer> iter = cardNumbers.iterator();

                // Пробегаем массив индексов
                while(iter.hasNext()){

                    // Генерируем число
                    random = MathUtils.random(0, 37);

                    // Проверяем на наличие его в массиве
                    Integer cardNum = iter.next();

                    // Находя совпадения, пробуем ещё раз
                    if(random == cardNum){ continue; }
                }
            }
            */

            random = MathUtils.random(0, 37);

            // Если четная позиция карты
          //  if(i) {
          //      CardDeckLoader.deckCard[random].setPosition(x_point, y_point);
           //     i = false;
           // }
            // Если нечетная позиция карты
           // else {
            CardDeckLoader.deckCard[random].setPosition(1092, y_point);

               // i = true;
            //}


            playerCard.add(CardDeckLoader.deckCard[random]);       // Добавили карту в колоду
            //cardNumbers.add(random);    // Записали индекс этой карты
            //amountPlayerCard++;         // Увеличиваем количество карт в руке
            //i++;                        // Счетчик ++
            y_point -= 125;
        }
    }

    // Отрисовка руки игрока на игровом поле
    public void renderHand(SpriteBatch batch){

        // Итератор для пробега по "руке"
        Iterator<Card> iter = playerCard.iterator();

        while(iter.hasNext()){
            Card card_temp = iter.next();   // Выбрал
            card_temp.renderCard(batch);    // Нарисовал
        }

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
