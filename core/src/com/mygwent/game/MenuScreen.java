package com.mygwent.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Александр on 30.06.2016.
 */
public class MenuScreen implements Screen {

    // Файл для хранения статистики
    FileHandle file;

    // Подключаем точку входа
    final GameIn game;

    // Подключаем камеру
    OrthographicCamera camera;

    // Конструктор класса Меню
    public MenuScreen(final GameIn game){
        this.game = game;

        // Локальный файл для хранения игровой статистики
        file = Gdx.files.local("myfile.txt");

        // Задаем параметры камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 800);
    }

    // Рисуем наше Меню
    @Override
    public void render(float delta) {
        // Обновление экрана (очистка)
        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Обновление камеры
        camera.update();
        // Устанавливаем матрицу проекции на камеру
        game.batch.setProjectionMatrix(camera.combined);

        // Обновление полотна
        game.batch.begin();

        // Рисуем текст на экране
        game.font.draw(game.batch, "Welcome to Gwent!!!", 570, 400);
        game.font.draw(game.batch, "Tap anywhere to begin!", 570, 370);

        game.batch.end();

        // По касаню запускаем игру
        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    // Прочие методы остануться пустыми
    @Override
    public void dispose() {

    }
    @Override
    public void show() {

    }
    @Override
    public void hide() {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void resize(int width, int height) {

    }


}
