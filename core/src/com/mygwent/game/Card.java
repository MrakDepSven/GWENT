package com.mygwent.game;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by Александр on 30.06.2016.
 */
public class Card {

    // Позиция карты на экране
    private Vector3 position;

    // Размер карты
    private int width;
    private int height;

    public Card(int x, int y, int width, int height){

        // Запись позиции
        position.x = x;
        position.y = y;
        // Запись размеров
        this.width = width;
        this.height = height;


    }
}
