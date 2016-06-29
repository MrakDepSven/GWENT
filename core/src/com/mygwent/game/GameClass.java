package com.mygwent.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameClass extends ApplicationAdapter {

	// Камера
	OrthographicCamera camera;
	// контейнер для отрисовки графики
	SpriteBatch batch;
	// Текстуры игры
	Texture bg_texture;
	Rectangle bg_object;
	
	@Override
	public void create () {

		// подключение камеры
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 800);

		// инициализация полтна
		batch = new SpriteBatch();

		// Инициализация текстур
		bg_texture = new Texture("background.png");

		// Обозначение параметров фонового компонента
		bg_object = new Rectangle();
		bg_object.x = 0;
		bg_object.y = 0;
		bg_object.width = 1280;
		bg_object.height = 800;


	}


	@Override
	public void render () {
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
		batch.draw(bg_texture, bg_object.x, bg_object.y);


		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		bg_texture.dispose();
	}


}
