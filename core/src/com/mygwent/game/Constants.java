package com.mygwent.game;

/**
 * Created by Александр on 02.07.2016.
 */
public class Constants {

    private Constants(){}

    // Количество карт в колоде
    public static final int AMOUNT_CARD_IN_DECK = 38;
    // Количество карт в руке (максимум)
    public static final int MAX_CARD_IN_HAND = 12;

    // Игровое поле *******************************************************************************
    // Координаты (X, Y) для полей битвы
    public static final int COORDINATE_BATTLE_SOCKET_X = 460;
    public static final int COORDINATE_BATTLE_SOCKET_Y[] = {659, 533, 406, 280, 156, 30};
    // Координаты (Х, Y) для погодных карт
    public static final int COORDINATE_WEATHER_SOCKET_XY[] = { 35, 344 };
    // Шаги для отрисовки карт поля битвы / погоды
    public static final int STEP_CARD_BATTLE[] = { 50, 85 };
    // ********************************************************************************************
}
