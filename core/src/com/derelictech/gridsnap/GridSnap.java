package com.derelictech.gridsnap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.derelictech.gridsnap.assets.Grid;

import java.util.ArrayList;
import java.util.List;

public class GridSnap extends ApplicationAdapter {
	ShapeRenderer shape_renderer;
	SpriteBatch batch;

    List<Grid> gridList;
    Vector2 mouse;
    Circle circle;

	@Override
	public void create () {
		batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();

        gridList = new ArrayList<Grid>();
        mouse = new Vector2(0, 0);
        circle = new Circle(0, 0, 5);

        gridList.add(new Grid(50, 50, 250, 250, 5, 5, new Color(0, 1, 0, 1)));
        gridList.add(new Grid(400, 50, 100, 400, 10, 40, new Color(1, 0, 0, 1)));
        gridList.add(new Grid(50, 400, 200, 50, 10, 5, new Color(0, 0, 1, 1)));
        gridList.add(new Grid(535, 200, 35, 150, 2, 5, new Color(0.7f, 0, 0.7f, 1)));
        gridList.add(new Grid(50, 350, 10, 10, 5, 5, new Color(1, 1, 1, 1)));
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
            public boolean mouseMoved(int x, int y){
                mouse.x = x;
                mouse.y = Gdx.graphics.getHeight() - y;
                return true;
            }
        });

        for(Grid g : gridList) {
            if(new Rectangle(mouse.x - 5f, mouse.y - 5f, 10, 10).overlaps(g)) {
                circle.set(g.snap(mouse), 5);
                break;
            }
        }

		// Renders
        for(Grid g : gridList) {
            g.render(shape_renderer);
        }

        shape_renderer.setAutoShapeType(true);
        shape_renderer.begin();
        shape_renderer.setColor(new Color(1, 0, 0, 1));
        shape_renderer.circle(circle.x, circle.y, 5);
        shape_renderer.end();

		// Test
	}

}
