package com.derelictech.gridsnap.util;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;

/**
 * Created by Tim on 1/10/2016.
 */
public abstract class Grid extends Rectangle {
    public float x_cells, y_cells;
    public float cell_w, cell_h;
    public Color color;

    public Grid(int x, int y, int width, int height, int x_cells, int y_cells, Color c) {
        super(x, y, width, height);
        this.x_cells = x_cells;
        this.y_cells = y_cells;
        this.cell_w = width / x_cells;
        this.cell_h = height / y_cells;
        this.color = c;
    }

    public void render_steps(ShapeRenderer sr) {
        sr.set(ShapeRenderer.ShapeType.Line);
        sr.setColor(color);
        for(int i = 0; i < x_cells + 1; i++){
            sr.line(x + (i * cell_w), y, x + (i * cell_w), (y+(y_cells * cell_h)));
        }
        for(int i = 0; i < y_cells + 1; i++){
            sr.line(x, y + (i * cell_h), (x+(x_cells * cell_w)), y + (i * cell_h));
        }
    }

    public abstract Vector2 snap(Vector2 v);

    public Vector2 gridCoordFromPos(Vector2 v) {
        return gridCoordFromPos(v.x, v.y);
    }
    public Vector2 gridCoordFromPos(float pos_x, float pos_y){
        Vector2 v = new Vector2();

        v.x = (float) ((int) (pos_x - this.x + (this.cell_w / 2)) / (int) this.cell_w);

        v.y = (float) ((int) (pos_y - this.y + (this.cell_h / 2)) / (int) this.cell_h);

        return v;
    }

    public Vector2 PosFromGridCoord(Vector2 v) {
        return PosFromGridCoord(v.x, v.y);
    }

    private Vector2 PosFromGridCoord(float x, float y) {
        Vector2 v = new Vector2();
        v.x = x * cell_w;
        v.y = y * cell_h;
        v.add(this.x, this.y);
        return v;
    }

    public void clicked(float screenX, float screenY, int pointer, int button) {
    }
}
