package com.mygwent.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Александр on 30.06.2016.
 */
public class AssetLoader {

    // Текстуры игры
    public static Texture background_t;        // Игровой стол (фон)

    // Значки способностей
    public static Texture burst_ability_t;        // Прилив сил
    public static Texture medic_ability_t;        // Медик
    public static Texture spy_ability_t;          // Шпион
    public static Texture strong_bond_ability_t;  // Прочная связь

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


    public static Sprite background_s,
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
            sapphire_skull_s;

    public static Array<Sprite> AbilitySprite,
            CardSprite,
            SkullSprite;


    public static void load(){

        // Инициализация текстурных паков
        AbilitySprite = new Array<Sprite>();
        CardSprite = new Array<Sprite>();
        SkullSprite = new Array<Sprite>();

        // Инициализация текстур и их спрайтов
        // Фон
        background_t = new Texture("data/background.png");
        background_s = new Sprite(background_t, 1280, 800);
        background_s.setPosition(0, 0);

        // Способности
        burst_ability_t = new Texture("data/burst_ability.png");
        AbilitySprite.add(burst_ability_s = new Sprite(burst_ability_t, 14, 14));

        medic_ability_t = new Texture("data/medic_ability.png");
        AbilitySprite.add(medic_ability_s = new Sprite(medic_ability_t, 14, 14));

        spy_ability_t = new Texture("data/spy_ability.png");
        AbilitySprite.add(spy_ability_s = new Sprite(spy_ability_t, 14, 14));

        strong_bond_ability_t = new Texture("data/strong_bond_ability.png");
        AbilitySprite.add(strong_bond_ability_s = new Sprite(strong_bond_ability_t, 14, 14));

        // Карты отрядов
        melee_card_t = new Texture("data/melee_card.png");
        CardSprite.add(melee_card_s = new Sprite(melee_card_t, 70, 96));

        range_card_t = new Texture("data/range_card.png");
        CardSprite.add(range_card_s = new Sprite(range_card_t, 70, 96));

        siege_card_t = new Texture("data/siege_card.png");
        CardSprite.add(siege_card_s = new Sprite(siege_card_t, 70, 96));

        // Карты погоды
        clear_sky_card_t = new Texture("data/clear_sky_card.png");
        CardSprite.add(clear_sky_card_s = new Sprite(clear_sky_card_t, 70, 96));

        frost_card_t = new Texture("data/frost_card.png");
        CardSprite.add(frost_card_s = new Sprite(frost_card_t, 70, 96));

        mist_card_t = new Texture("data/mist_card.png");
        CardSprite.add(mist_card_s = new Sprite(mist_card_t, 70, 96));

        shower_card_t = new Texture("data/shower_card.png");
        CardSprite.add(shower_card_s = new Sprite(shower_card_t, 70, 96));

        // Черепухи
        ruby_skull_t = new Texture("data/ruby_skull.png");
        SkullSprite.add(ruby_skull_s = new Sprite(ruby_skull_t, 62, 60));

        sapphire_skull_t = new Texture("data/sapphire_skull.png");
        SkullSprite.add(sapphire_skull_s = new Sprite(sapphire_skull_t, 62, 60));

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
