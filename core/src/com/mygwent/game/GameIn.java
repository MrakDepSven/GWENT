package com.mygwent.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Александр on 30.06.2016.
 */
public class GameIn extends Game {

    // Отрисовка фона
    SpriteBatch batch;
    // Отрисовка текста
    BitmapFont font;

    @Override
    public void create() {
        // Определяем ресурсы
        batch = new SpriteBatch();
        font = new BitmapFont();

        AssetLoader.load();

        // Рисуем меню
        this.setScreen(new MenuScreen(this));
    }

    // Запускаем рендер
    @Override
    public void render() {
        super.render();
    }

    // Очищаем от мусора
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        AssetLoader.dispose();

    }
}
