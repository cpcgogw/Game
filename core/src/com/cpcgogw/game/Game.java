package com.cpcgogw.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class Game extends ApplicationAdapter {
    private OrthographicCamera camera;
	private BitmapFont font;
	private SpriteBatch spriteBatch;
    private Hero hero;
    private Texture background;
    private float stateTime;

    private int screenWidth = 720;
    private int screenHeight = 600;

    @Override
	public void create () {
        camera = new OrthographicCamera(screenWidth, screenHeight);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(3, 3);
		background = new Texture(Gdx.files.internal("sky-5.jpg"));
        spriteBatch = new SpriteBatch();
		stateTime = 0f;
        hero = new Hero(0, 0);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = hero.drawHero().getKeyFrame(stateTime, true);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            hero.setXPos(hero.getXPos()-2);

            if(((Math.abs(hero.getXPos())) + screenWidth/2) < background.getWidth()/2) {
                camera.translate(-2, 0);
                camera.update();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            hero.setXPos(hero.getXPos()+2);

            if(((Math.abs(hero.getXPos())) + screenWidth/2) < background.getWidth()/2) {
                camera.translate(2, 0);
                camera.update();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){

            hero.setYPos(hero.getYPos() + 2);

            if(((Math.abs(hero.getYPos())) + screenHeight/2) < background.getHeight()/2) {
                camera.translate(0, 2);
                camera.update();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            hero.setYPos(hero.getYPos() - 2);

            if(((Math.abs(hero.getYPos())) + screenHeight/2) < background.getHeight()/2) {
                camera.translate(0, -2);
                camera.update();
            }
        }

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, -background.getWidth()/2, -background.getHeight()/2);
		font.draw(spriteBatch, "This is a game", 165, 350);
		spriteBatch.draw(currentFrame, hero.getXPos(), hero.getYPos());
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		font.dispose();
		background.dispose();
	}
}
