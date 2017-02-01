package com.cpcgogw.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Game extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Hero hero;
    private SpriteBatch batch;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private float stateTime;

    private int mapPixelWidth;
    private int mapPixelHeight;
    private int cameraWidth;
    private int cameraHeight;

    @Override
	public void create () {
        tiledMap = new TmxMapLoader().load("maps/TestMap1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        int mapWidth = tiledMap.getProperties().get("width", Integer.class);
        int mapHeight = tiledMap.getProperties().get("height", Integer.class);
        int tileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        int tileHeight = tiledMap.getProperties().get("tileheight", Integer.class);
        mapPixelWidth = mapWidth*tileWidth;
        mapPixelHeight = mapHeight*tileHeight;
        cameraWidth = 512;
        cameraHeight = 512;
        camera = new OrthographicCamera(cameraWidth, cameraHeight);

        camera.translate(cameraWidth/2, cameraHeight/2);
        camera.update();

		batch = new SpriteBatch();
		stateTime = 0f;
        hero = new Hero(cameraWidth/2, cameraHeight/2);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		TextureRegion currentFrame = hero.drawHero().getKeyFrame(stateTime, true);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            if(hero.getXPos() > cameraWidth/2 && hero.getXPos() < (mapPixelWidth - cameraWidth/2)) {
                camera.translate(-2, 0);
                camera.update();
            }
            hero.setXPos(hero.getXPos()-2);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            if(hero.getXPos() > cameraWidth/2 && hero.getXPos() < (mapPixelWidth - cameraWidth/2)) {
                camera.translate(2, 0);
                camera.update();
            }
            hero.setXPos(hero.getXPos()+2);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){

            if(hero.getYPos() > cameraHeight/2 && hero.getYPos() < (mapPixelHeight - cameraHeight/2)) {
                camera.translate(0, 2);
                camera.update();
            }
            hero.setYPos(hero.getYPos() + 2);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            if(hero.getYPos() > cameraHeight/2 && hero.getYPos() < (mapPixelHeight - cameraHeight/2)) {
                camera.translate(0, -2);
                camera.update();
            }
            hero.setYPos(hero.getYPos() - 2);
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(currentFrame, hero.getXPos(), hero.getYPos());
        batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
