package com.derelictech.gridsnap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.derelictech.gridsnap.util.*;

import java.util.ArrayList;
import java.util.List;

public class GridSnap extends ApplicationAdapter {
	ShapeRenderer sr;
	SpriteBatch batch;
    public BitmapFont font;

    List<Grid> gridList;
    public Printable textArea;
    Vector2 mouse;
    Selector selector;

	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
		sr = new ShapeRenderer();

        gridList = new ArrayList<Grid>();
        textArea = new Printable("", 200, 23);
        mouse = new Vector2(0, 0);
        selector = new Selector();

        gridList.add(new TileGrid(50, 50, 250, 250, 5, 5, new Color(0, 1, 0, 1)));
        gridList.add(new TileGrid(400, 50, 100, 400, 10, 40, new Color(1, 0, 0, 1)));
        gridList.add(new NodeGrid(50, 400, 200, 50, 10, 5, new Color(0, 0, 1, 1)));
        gridList.add(new NodeGrid(535, 200, 35, 150, 2, 5, new Color(0.7f, 0, 0.7f, 1)));
        gridList.add(new NodeGrid(50, 350, 10, 10, 5, 5, new Color(1, 1, 1, 1)));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();

        // Logics
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean mouseMoved(int screenX, int screenY){
                mouse.x = screenX;
                mouse.y = Gdx.graphics.getHeight() - screenY;
                selector.collisionBox.setPosition(mouse.x, mouse.y);
                boolean onGrid = false;
                for(Grid g : gridList) {
                    if(selector.collisionBox.overlaps(g)) {
                        onGrid = true;
                        if(g instanceof NodeGrid) {
                            selector.setNodeSelectVisible(true);
                            selector.setTileSelectVisible(false);
                            selector.setPosition(g.snap(mouse));
                            textArea.str = "NodeGrid";
                        }
                        else if(g instanceof TileGrid) {
                            selector.setNodeSelectVisible(false);
                            selector.setTileSelectVisible(true);
                            selector.setPosition(g.snap(mouse));
                            selector.tileSelect.set(selector.position.x, selector.position.y, g.cell_w, g.cell_h);
                            selector.setPosition(g.snap(mouse));
                            textArea.str = "TileGrid";
                        }
                    }
                }
                if(!onGrid) {
                    textArea.str = "";
                    selector.setNodeSelectVisible(false);
                    selector.setTileSelectVisible(false);
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                for(Grid g : gridList) {
                    if(selector.collisionBox.overlaps(g)) {
                        g.clicked(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
                        break;
                    }
                }
                return true;
            }
        });

        // ShapeRenderer
        sr.setAutoShapeType(true);
        sr.begin();
        for(Grid g : gridList) {
            g.render_steps(sr);
        }
        selector.render_steps(sr);
        sr.end();

        // SpriteBatch
        batch.begin();
        font.draw(batch, textArea.str +" "+ selector.isNodeSelectVisible() +" "+ selector.isTileSelectVisible(), textArea.pos.x, textArea.pos.y);
        batch.end();
	}
}
