package com.mygwent.game;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	// Обновление стола(логика) и графики
	private GameWorld world;
	private GameRender renderer;

	// Подключаем экземпляр стартовой точки
	final GameIn game;

	public GameScreen(final GameIn game) {

		// Инициализация обновлений стола и графики
		world = new GameWorld();
		renderer = new GameRender(world);

		// Определяем стартовый класс
		this.game = game;

	}

	@Override
	public void render (float delta) {

		// Запуск обновлений
		world.update(delta);
		renderer.render();

	}

	@Override
	public void dispose () {
		//batch.dispose();
		//bg_texture.dispose();
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