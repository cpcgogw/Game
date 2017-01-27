package com.cpcgogw.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class Game extends ApplicationAdapter {
	private BitmapFont font;
	private Texture texture;
	private SpriteBatch spriteBatch;
	private Animation<TextureRegion> animation;

	private final int SHEET_COLUMNS = 4;
    private final int SHEET_ROWS = 4;
    private int spriteX = 200;
    private int spriteY = 200;

    private float stateTime;

    @Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(3, 3);

		texture = new Texture(Gdx.files.internal("bat_sprite.png"));
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/SHEET_COLUMNS, texture.getHeight()/SHEET_ROWS);
        TextureRegion[] frames = new TextureRegion[SHEET_ROWS*SHEET_COLUMNS];
        int index = 0;
        for (int i = 0; i < SHEET_ROWS; i++){
            for (int j = 0; j < SHEET_COLUMNS; j++){
                frames[index++] = tmp[i][j];
            }
        }

        animation = new Animation<TextureRegion>(0.25f, frames);
		spriteBatch = new SpriteBatch();
		stateTime = 0f;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            spriteX--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spriteX++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            spriteY++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            spriteY--;
        }
            spriteBatch.begin();
		font.draw(spriteBatch, "This is a game", 165, 350);
		spriteBatch.draw(currentFrame, spriteX, spriteY);
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		font.dispose();
		texture.dispose();
	}
}
