package com.mygwent.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

	// Подключаем экземпляр стартового класса
	final GameIn game;

	// Камера
	OrthographicCamera camera;
	// контейнер для отрисовки графики
	SpriteBatch batch;
	// Текстуры игры
	Texture bg_texture;
	Sprite bg_sprite;


	public GameScreen(final GameIn game) {
		// Определяем стартовый класс
		this.game = game;

		// подключение камеры
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 800);

		// инициализация полтна
		batch = new SpriteBatch();

		// Инициализация текстур
		bg_texture = new Texture("gaming_table.png");
		bg_sprite = new Sprite(bg_texture, 1280, 800);
		bg_sprite.setPosition(0, 0);

		// Отключаем постоянный рендер карты
		Gdx.graphics.setContinuousRendering(false);

	}

	@Override
	public void render (float delta) {
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
		bg_sprite.draw(batch);
		batch.enableBlending();

		// Рисуем все остальное


		batch.end();


		// Включение 1 обновления кадра
		//Gdx.graphics.requestRendering();
	}

	@Override
	public void dispose () {
		batch.dispose();
		bg_texture.dispose();
	}



	// Остальные методы определяем пустыми
	@Override
	public void show() {


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
	public void hide() {


	}

}