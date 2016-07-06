package com.mygwent.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Александр on 30.06.2016.
 */
public class MenuScreen implements Screen, GestureDetector.GestureListener {

    // Подключаем точку входа
    final GameIn game;

    // Подключаем камеру
    OrthographicCamera camera;

    // Конструктор класса Меню
    public MenuScreen(final GameIn gam){
        this.game = gam;

        // Задаем параметры камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 800);

        Gdx.input.setInputProcessor(new GestureDetector(this));
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

        // Рисуем фон
        game.batch.disableBlending();
        AssetLoader.background_menu_s.draw(game.batch);
        game.batch.enableBlending();

        // Рисуем все остальное
        for (int i = 0; i < 5; i++){
            AssetLoader.MenuSpriteUp[i].draw(game.batch);
        }


        game.batch.end();
    }


    @Override
    public boolean tap(float x, float y, int count, int button) {

        for (int i = 0; i < 5; i++){

            float xX = AssetLoader.MenuSpriteUp[i].getX();
            int yY = crutch_for_y(AssetLoader.MenuSpriteUp[i].getY());

            if((x >= xX)&&(x <= xX + 230)
                    &&(y >= yY)&&(y <= yY + 70)){
                switch (i){
                    case 0:
                        Gdx.app.log("Новая игра", "");
                        game.setScreen(new GameScreen(game));
                        dispose();
                        break;
                    case 1:
                        Gdx.app.log("продолжить", "");
                        break;
                    case 2:
                        Gdx.app.log("рекорды", "");

                        break;
                    case 3:
                        Gdx.app.log("сложность", "");

                        break;
                    case 4:
                        Gdx.app.log("выход", "");
                        Gdx.app.exit();
                        dispose();
                        break;
                }
            }
        }

        return true;
    }
    // Костыль для Y
    public int crutch_for_y(float y){

        switch ((int) y){
            case 606: return 119;
            case 531: return 194;
            case 456: return 269;
            case 381: return 344;
            case 306: return 419;
        }
        return 0;
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
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }
    @Override
    public boolean longPress(float x, float y) {
        return false;
    }
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }
    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
    @Override
    public void pinchStop() {

    }
}
