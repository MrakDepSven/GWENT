package com.mygwent.game;


/**
 * Created by Александр on 30.06.2016.
 */
public class GameWorld {

    // Создаем экземпляр ирока
    Player player_test;
    Player enemy_player;

    public GameWorld(){

        // Инициализация игроков
        player_test = new Player(true);
        enemy_player = new Player(false);


        // Генерация руки игрока
        player_test.generateHand();

    }

    public  void update(float delta){


    }
}
