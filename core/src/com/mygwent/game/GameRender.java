package com.mygwent.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



/**
 * Created by Александр on 30.06.2016.
 */
public class GameRender {

    // Создаем экземпляр стола, для доступа к нему
    private GameWorld myWorld;



    // Камера
    OrthographicCamera camera;

    // контейнер для отрисовки графики
    SpriteBatch batch;

    public GameRender(GameWorld world){
        myWorld = world;

        // подключение камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 800);

        // инициализация полтна
        batch = new SpriteBatch();



        // Отключаем постоянный рендер карты
        // Gdx.graphics.setContinuousRendering(false);
    }

    public void render(){

        // Включение 1 обновления кадра
        Gdx.graphics.requestRendering();

        // Обновление экрана (очистка)
        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Обновление камеры
        camera.update();

        // Устанавливаем матрицу проекции на камеру
        batch.setProjectionMatrix(camera.combined);

        // Обновление полотна
        batch.begin();

        // Рисуем фон
        batch.disableBlending();
        AssetLoader.background_s.draw(batch);
        batch.enableBlending();

        // Рисуем все остальное


        batch.end();

        // Отрисовка карт

        myWorld.player_test.renderHand(batch);

        Card card_test = new Card("", 0, 0, 0);
        card_test.setPosition(460, 30);
        card_test.renderCard(batch);
        card_test.setPosition(510, 30);
        card_test.renderCard(batch);
        card_test.setPosition(560, 30);
        card_test.renderCard(batch);
        card_test.setPosition(460, 156);
        card_test.renderCard(batch);
        card_test.setPosition(460, 280);
        card_test.renderCard(batch);
        card_test.setPosition(460, 406);
        card_test.renderCard(batch);
        card_test.setPosition(460, 533);
        card_test.renderCard(batch);
        card_test.setPosition(460, 659);
        card_test.renderCard(batch);
        card_test.setPosition(35, 344);
        card_test.renderCard(batch);
        card_test.setPosition(120, 344);
        card_test.renderCard(batch);
        card_test.setPosition(205, 344);
        card_test.renderCard(batch);


    }



}
