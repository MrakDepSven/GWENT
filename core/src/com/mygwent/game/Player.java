package com.mygwent.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import static com.mygwent.game.Constants.*;
import java.util.Iterator;

/**
 * Created by Александр on 01.07.2016.
 */
public class Player {

    // Имя игрока
    String name;

    // Карты в руке игрока
    public Array<Card> cardPlayer;

    // Индексты взятых из колоды карт
    public Array<Integer> cardNumbers;

    private int amountPlayerCard;       // Количество карт в руке
    private int amountPlayerLives;      // Количество жизней
    private int totalPower;             // Общая сила карт на столе
    private int amountBrockenCard;      // Количество битых карт

    // 1 - если игрок, 0 - если ИИ
    private int player_check;
    // true - если спасовал, false - игра продолжается
    private boolean pass;

    Player(String name, int player_check){

        // Принимаем имя игрока
        this.name = name;
        // Проверяем игрок это или ИИ
        this.player_check = player_check;
        // Инициализация карт в руке и индексов карт взятых из колоды
        cardPlayer = new Array<Card>();
        cardNumbers = new Array<Integer>();

        amountPlayerCard = 0;      // Начальное количество карт
        amountPlayerLives = 2;     // Стандартное количество жизней
        amountBrockenCard = 0;     // Битые карты
        totalPower = 0;            // Общая сила карт

    }

    // Добавление карт в руку
    public void takeCardFromDeck(int amountCard){

        // ограничение количества карт в руке
        if((amountCard + amountPlayerCard) > MAX_CARD_IN_HAND){
            // Определяем сколько ещё пустых мест есть в руке
            amountCard = MAX_CARD_IN_HAND - amountPlayerCard;
        }

        // См. цикл генерации индексов
        amountCard += amountPlayerCard;

        // Генерация индексов ***********************************
        // пока не возмем нужное количество карт в руку, цикл продолжать
        while (amountPlayerCard != amountCard){

            if(amountPlayerCard == 0){ // если нет карт в руке

                int random = MathUtils.random(0, 37);

                amountPlayerCard++;     // увеличил количество карт в руке
                cardNumbers.add(random);    // Добавил индекс карты в список использованных
                cardPlayer.add(CardDeckLoader.deckCard[random]); // Берем карту в руку

            }else { // если карты в руке уже есть

                int random = MathUtils.random(0, 37);
                Iterator<Integer> iter = cardNumbers.iterator();

                int dub = 0; // поиск дубляжей

                while (iter.hasNext()){
                    Integer index = iter.next();
                    if(random == index){
                        dub++; // нали дубляж, добавили +1
                    }

                }

                // если дубли были, возвращаемся и генерируем новое число
                if(dub != 0){
                    continue;
                }else { // если небыло, добавляем эту карту в руку
                    amountPlayerCard++;
                    cardNumbers.add(random);
                    cardPlayer.add(CardDeckLoader.deckCard[random]); // Берем карту в руку
                }
            }
        }
    }


    // Отрисовка руки игрока на игровом поле
    public void renderHand(SpriteBatch batch){

        int x_point = 1092;     // позиция первой карты по х
        int x_step = 82;        // шаг по координате х
        int y_point = 657;      // для расчета первой координаты у
        int y_step = 125;       // шаг по координатам y

        int ii = 1;

        Iterator<Card> iter = cardPlayer.iterator(); // массив индексов

        while (iter.hasNext()){

            Card card_temp = iter.next();

            // Для четных позиций карт
            if(ii <= 6) {
                // Перед отрисовкой карты присваиваю ей координаты
                card_temp.setPosition(x_point, y_point);
                card_temp.renderCard(batch);

                y_point -= y_step;
                ii++;
            }
            // Если нечетная позиция карты
            else {

                card_temp.setPosition(x_point + x_step, y_point);
                card_temp.renderCard(batch);

                y_point -= y_step;
                ii++;
            }

            if(ii == 7) y_point = 657;

        }

    }

    // Принятие паса игрока
    public void playerPass() {
        pass = true;
    }

    // Блок ИИ ******************************





    // Все SET и GET
    public int getAmountBrockenCard() {
        return amountBrockenCard;
    }
    public void setAmountBrockenCard(int amountBrockenCard) {
        this.amountBrockenCard = amountBrockenCard;
    }
    public int getTotalPower() {
        return totalPower;
    }
    public void setTotalPower(int totalPower) {
        this.totalPower = totalPower;
    }
    public boolean getPlayerPass(){
        return pass;
    }
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
