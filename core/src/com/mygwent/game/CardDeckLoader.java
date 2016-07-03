package com.mygwent.game;

import static com.mygwent.game.Constants.*;

/**
 * Created by Александр on 01.07.2016.
 */
public class CardDeckLoader {

    // Колода карт
    public static Card[] deckCard;

    // Загрузка колоды карт
    public static void loadDeckCard(){

        deckCard = new Card[AMOUNT_CARD_IN_DECK];

        // Карты ближнего боя
        deckCard[0] = new Card("Гребаная пехтура", 1, 0, 3);
        deckCard[1] = new Card("Гребаная пехтура", 1, 0, 3);
        deckCard[2] = new Card("Гребаная пехтура", 1, 0, 3);
        deckCard[3] = new Card("Гребаная пехтура", 1, 0, 3);
        deckCard[4] = new Card("Реданский пехотинец", 1, 0, 4);
        deckCard[5] = new Card("Реданский пехотинец", 1, 0, 4);
        deckCard[6] = new Card("Реданский пехотинец", 1, 0, 4);
        deckCard[7] = new Card("Ярпен Зигрен", 2, 0, 4);
        deckCard[8] = new Card("Боец Синих Полосок", 4, 0, 3);
        deckCard[9] = new Card("Боец Синих Полосок", 4, 0, 3);
        deckCard[10] = new Card("Боец Синих Полосок", 4, 0, 3);
        deckCard[11] = new Card("Боец Синих Полосок", 4, 0, 3);
        deckCard[12] = new Card("Бьянка", 5, 0, 4);
        deckCard[13] = new Card("Зигфрид из Денесле", 5, 0, 4);
        deckCard[14] = new Card("Принц Стеннис", 5, 0, 2);
        deckCard[15] = new Card("Принц Стеннис", 5, 0, 2);

        // Карты дальнобойных отрядов
        deckCard[16] = new Card("Сабрина Глевессиг", 4, 1, 4);
        deckCard[17] = new Card("Шелдон Скаггс", 4, 1, 4);
        deckCard[18] = new Card("Кейра Мец", 5, 1, 4);
        deckCard[19] = new Card("Шеала де Транселрвилль", 5, 1, 4);
        deckCard[20] = new Card("Детмольд", 6, 1, 4);
        deckCard[21] = new Card("Лучник Бурой Хоругви", 6, 1, 4);
        deckCard[22] = new Card("Лучник Бурой Хоругви", 7, 1, 4);

        // Карты осадных отрядов
        deckCard[23] = new Card("Каэдвенский осадный мастер", 1, 2, 0);
        deckCard[24] = new Card("Каэдвенский осадный мастер", 1, 2, 0);
        deckCard[25] = new Card("Каэдвенский осадный мастер", 1, 2, 0);
        deckCard[26] = new Card("Каэдвенский осадный мастер", 1, 2, 0);
        deckCard[27] = new Card("Лекарь Бурой Хоругви", 5, 2, 1);
        deckCard[28] = new Card("Лекарь Бурой Хоругви", 5, 2, 1);
        deckCard[29] = new Card("Лекарь Бурой Хоругви", 5, 2, 1);
        deckCard[30] = new Card("Балиста", 6, 2, 4);
        deckCard[31] = new Card("Требушет", 6, 2, 4);

        // Карты погоды
        deckCard[32] = new Card("Ливень", 6, 6, 4);
        deckCard[33] = new Card("Мгла", 5, 5, 4);
        deckCard[34] = new Card("Мгла", 5, 5, 4);
        deckCard[35] = new Card("Мороз", 4, 4, 4);
        deckCard[36] = new Card("Мороз", 4, 4, 4);
        deckCard[37] = new Card("Ясное небо", 3, 3, 4);

    }

}
