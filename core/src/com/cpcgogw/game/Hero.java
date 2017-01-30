package com.cpcgogw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by oskarwillman on 2017-01-30.
 */
public class Hero extends Creature {

    private int xPos;
    private int yPos;

    public Hero(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public Animation<TextureRegion> drawHero(){
        Texture texture = new Texture(Gdx.files.internal("bat_sprite.png"));
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/4, texture.getHeight()/4);
        TextureRegion[] frames = new TextureRegion[4*4];
        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                frames[index++] = tmp[i][j];
            }
        }
        return new Animation<TextureRegion>(0.25f, frames);
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public void setXPos(int newX){
        this.xPos = newX;
    }

    public void setYPos(int newY){
        this.yPos = newY;
    }


}
