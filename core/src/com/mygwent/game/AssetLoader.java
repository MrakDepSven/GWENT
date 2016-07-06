package com.mygwent.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Александр on 30.06.2016.
 */
public class AssetLoader {

    // Размеры текстур
    public static int card_width;
    public static int card_height;
    public static int ability_width;
    public static int ability_height;
    public static int skull_width;
    public static int skull_height;
    public static int text_menu_width;
    public static int text_menu_height;

    // Текстуры игры
    public static Texture background_t;        // Игровой стол (фон)
    public static Texture pass_t;

    // Значки способностей
    public static Texture burst_ability_t;        // Прилив сил
    public static Texture medic_ability_t;        // Медик
    public static Texture spy_ability_t;          // Шпион
    public static Texture strong_bond_ability_t;  // Прочная связь
    public static Texture null_t;                 // Пустая текстура

    // Карты отрядов
    public static Texture melee_card_t;        // Ближний бой
    public static Texture range_card_t;        // Дальний бой
    public static Texture siege_card_t;        // Осадные отряды

    // Карты погоды
    public static Texture clear_sky_card_t;    // Чистое небо
    public static Texture frost_card_t;        // Мороз
    public static Texture shower_card_t;       // Ливень
    public static Texture mist_card_t;         // Мгла

    // Черепа
    public static Texture ruby_skull_t;        // Рубиновый черепок
    public static Texture sapphire_skull_t;    // Сапфировый черепок

    // Текстукры меню
    public static Texture background_menu_t,
            new_game_t,
            continue_t,
            complexity_game_t,
            records_t,
            exit_t,
            accept_t,
            back_t;



    public static Sprite background_s,
            null_s,
            burst_ability_s,
            medic_ability_s,
            spy_ability_s,
            strong_bond_ability_s,
            melee_card_s,
            range_card_s,
            siege_card_s,
            clear_sky_card_s,
            frost_card_s,
            shower_card_s,
            mist_card_s,
            ruby_skull_s,
            sapphire_skull_s,
            pass_s,
            background_menu_s,
            back_s,
            accept_s,
            complexity_game_s,
            continue_s,
            exit_s,
            new_game_s,
            records_s;

    public static Sprite[]
            AbilitySprite,
            CardSprite,
            SkullSprite,
            MenuSpriteDown,
            MenuSpriteUp;


    public static void load(){

        // Размеры текстур
        card_width = 80;
        card_height = 115;
        ability_width = 17;
        ability_height = 17;
        skull_width = 62;
        skull_height = 60;
        text_menu_width = 230;
        text_menu_height = 70;

        // Инициализация текстурных паков
        AbilitySprite = new Sprite[5];
        CardSprite = new Sprite[7];
        SkullSprite = new Sprite[2];
        MenuSpriteDown = new Sprite[5];
        MenuSpriteUp = new Sprite[5];

        // Игровое меню
        background_menu_t = new Texture("data/background_menu.png");
        background_menu_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        background_menu_s = new Sprite(background_menu_t, 1280, 800);
        background_menu_s.setPosition(0, 0);

        back_t = new Texture("data/back.png");
        back_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        back_s = new Sprite(back_t, text_menu_width, text_menu_height);

        accept_t = new Texture("data/accept.png");
        accept_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        accept_s = new Sprite(accept_t, text_menu_width, text_menu_height);

        complexity_game_t = new Texture("data/complexity_game.png");
        complexity_game_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        MenuSpriteUp[3] = new Sprite(complexity_game_t, text_menu_width, text_menu_height);
        MenuSpriteUp[3].setPosition(87, 381);

        continue_t = new Texture("data/continue.png");
        continue_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        MenuSpriteUp[1] = new Sprite(continue_t, text_menu_width, text_menu_height);
        MenuSpriteUp[1].setPosition(87, 531);

        exit_t = new Texture("data/exit.png");
        exit_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        MenuSpriteUp[4] = new Sprite(exit_t, text_menu_width, text_menu_height);
        MenuSpriteUp[4].setPosition(87, 306);

        new_game_t = new Texture("data/new_game.png");
        new_game_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        MenuSpriteUp[0] = new Sprite(new_game_t, text_menu_width, text_menu_height);
        MenuSpriteUp[0].setPosition(87, 606);

        records_t = new Texture("data/records.png");
        records_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        MenuSpriteUp[2] = new Sprite(records_t, text_menu_width, text_menu_height);
        MenuSpriteUp[2].setPosition(87, 456);

        // Инициализация текстур и их спрайтов

        // Кнопка пас
        pass_t = new Texture("data/pass.png");
        pass_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        pass_s = new Sprite(pass_t, 154, 111);

        // Фон
        background_t = new Texture("data/background.png");
        background_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        background_s = new Sprite(background_t, 1280, 800);
        background_s.setPosition(0, 0);

        // Способности
        burst_ability_t = new Texture("data/burst_ability.png");
        burst_ability_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        AbilitySprite[0] = (burst_ability_s = new Sprite(burst_ability_t, ability_width, ability_height));

        medic_ability_t = new Texture("data/medic_ability.png");
        medic_ability_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        AbilitySprite[1] = (medic_ability_s = new Sprite(medic_ability_t, ability_width, ability_height));

        spy_ability_t = new Texture("data/spy_ability.png");
        spy_ability_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        AbilitySprite[2] = (spy_ability_s = new Sprite(spy_ability_t, ability_width, ability_height));

        strong_bond_ability_t = new Texture("data/strong_bond_ability.png");
        strong_bond_ability_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        AbilitySprite[3] = (strong_bond_ability_s = new Sprite(strong_bond_ability_t, ability_width, ability_height));

        null_t = new Texture("data/null.png");
        null_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        AbilitySprite[4] = (null_s = new Sprite(null_t, ability_width, ability_height));

        // Карты отрядов
        melee_card_t = new Texture("data/melee_card.png");
        strong_bond_ability_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[0] = (melee_card_s = new Sprite(melee_card_t, card_width, card_height));

        range_card_t = new Texture("data/range_card.png");
        range_card_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[1] = (range_card_s = new Sprite(range_card_t, card_width, card_height));

        siege_card_t = new Texture("data/siege_card.png");
        siege_card_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[2] = (siege_card_s = new Sprite(siege_card_t, card_width, card_height));

        // Карты погоды
        clear_sky_card_t = new Texture("data/clear_sky_card.png");
        clear_sky_card_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[3] = (clear_sky_card_s = new Sprite(clear_sky_card_t, card_width, card_height));

        frost_card_t = new Texture("data/frost_card.png");
        frost_card_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[4] = (frost_card_s = new Sprite(frost_card_t, card_width, card_height));

        mist_card_t = new Texture("data/mist_card.png");
        mist_card_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[5] = (mist_card_s = new Sprite(mist_card_t, card_width, card_height));

        shower_card_t = new Texture("data/shower_card.png");
        shower_card_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        CardSprite[6] = (shower_card_s = new Sprite(shower_card_t, card_width, card_height));

        // Черепухи
        ruby_skull_t = new Texture("data/ruby_skull.png");
        ruby_skull_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        SkullSprite[0] = (ruby_skull_s = new Sprite(ruby_skull_t, skull_width, skull_height));

        sapphire_skull_t = new Texture("data/sapphire_skull.png");
        sapphire_skull_t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        SkullSprite[1] = (sapphire_skull_s = new Sprite(sapphire_skull_t, skull_width, skull_height));

    }

    public static void dispose(){
        burst_ability_t.dispose();
        clear_sky_card_t.dispose();
        frost_card_t.dispose();
        background_t.dispose();
        medic_ability_t.dispose();
        melee_card_t.dispose();
        mist_card_t.dispose();
        range_card_t.dispose();
        ruby_skull_t.dispose();
        sapphire_skull_t.dispose();
        shower_card_t.dispose();
        siege_card_t.dispose();
        spy_ability_t.dispose();
        strong_bond_ability_t.dispose();

    }


}
