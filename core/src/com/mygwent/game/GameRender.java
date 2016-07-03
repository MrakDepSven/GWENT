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

        Gdx.graphics.setContinuousRendering(false);
        
        // Подключаем игровое поле
        myWorld = world;

        // Подключение камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 800);

        // Инициализация полотна
        batch = new SpriteBatch();
    }

    public void render(){
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

        batch.end();

        // Рисуем инфо об игроках
        myWorld.renderInfo(batch);

        // Отрисовка карт
        myWorld.player.renderHand(batch);               // Рендер карт в руке
        myWorld.battlefield.renderBattlefield(batch);   // Рендер карт на столе


    }



}
