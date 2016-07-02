package com.mygwent.game;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Александр on 30.06.2016.
 */
public class Card {

    // Параметры карты
    String nameCard;        // Имя карты
    String power_s;         // Сила (string)
    int power_i;            // Сила (int)
    int cardSprite;         // Индекс карты
    int cardAbility;        // Индекс абилки

    // Положение карты
    int x;
    int y;

    // Для рендера карты
    BitmapFont power_f; // для отображения значения силы на карте
    SpriteBatch batch;  // полотно для отрисовки

    // Конструктор карты
    public Card(String nameCard, int power, int cardSprite, int cardAbility) {

        this.nameCard = nameCard;

        this.power_i = power;
        this.power_s = Integer.toString(power);

        this.cardSprite = cardSprite;
        this.cardAbility = cardAbility;

        power_f = new BitmapFont();

        x = 0;
        y = 0;
    }

    // Устанавливаем позицию для карты
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Отрисовка карты
    public void renderCard(SpriteBatch batch){

        batch.begin();

        batch.draw(AssetLoader.CardSprite[cardSprite], x, y);
        if(cardSprite <= 2){
            batch.draw(AssetLoader.AbilitySprite[cardAbility], x + 15, y + 59);
            power_f.draw(batch, power_s, x + 19, y + 94);
        }

        batch.end();
    }

    public void onClick(){

    }


}
