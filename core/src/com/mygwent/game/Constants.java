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

    // Отображение информации об игроках **********************************************************
    // Строка имени
    public static final int COORDINATE_PLAYER_NAME_X[] = { 65, 65 };
    public static final int COORDINATE_PLAYER_NAME_Y[] = { 519, 196 };
    // Количество карт в колоде
    public static final int COORDINATE_AMOUNT_IN_DECK_X[] = { 62, 62 };
    public static final int COORDINATE_AMOUNT_IN_DECK_Y[] = { 673, 62 };
    // Количество битых карт
    public static final int COORDINATE_BROKEN_CARD_X[] = { 232, 230 };
    public static final int COORDINATE_BROKEN_CARD_Y[] = { 672, 62 };
    // Общяя сила карт игроков
    public static final int COORDINATE_TOTAL_POWER_X[] = { 253, 253 };
    public static final int COORDINATE_TOTAL_POWER_Y[] = { 561, 246 };
    // Количество карт в руке
    public static final int COORDINATE_AMOUNT_HAND_X[] = { 193, 197 };
    public static final int COORDINATE_AMOUNT_HAND_Y[] = { 526, 202 };
    // Координаты сообщения ПАС
    public static final int COORDINATE_PASS_X[] = { 14, 14 };
    public static final int COORDINATE_PASS_Y[] = { 522, 200 };
    // Координаты черепов
    public static final int COORDINATE_FIRST_SKULL_X[] = { 136, 136 };
    public static final int COORDINATE_SECOND_SKULL_X[] = { 172, 174 };
    public static final int COORDINATE_SKULL_Y[] = { 562, 239 };
    // Отображение силы конкретного поля
    public static final int COORDINATE_POWER_X = 312;
    public static final int COORDINATE_POWER_Y[] = { 719, 595, 469, 340, 217, 86 };
    // ********************************************************************************************

}
