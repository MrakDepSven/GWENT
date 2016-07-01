package com.mygwent.game;


/**
 * Created by Александр on 30.06.2016.
 */
public class GameWorld {

    // Создаем экземпляр ирока
    Player player_test;

    public GameWorld(){

        // Инициализация игрока
        player_test = new Player();
        // Генерация руки игрока
        player_test.generateHand();

    }

    public  void update(float delta){


    }
}
