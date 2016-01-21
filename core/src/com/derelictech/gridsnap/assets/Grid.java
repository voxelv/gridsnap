package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;

/**
 * Created by Tim on 1/10/2016.
 */
public class Grid extends Rectangle {
    protected float x_cells, y_cells;
    protected float cell_w, cell_h;
    protected Color color;

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

    public Vector2 snap(Vector2 pos) {
        return snap(pos.x, pos.y);
    }

    public Vector2 snap(float pos_x, float pos_y) {
        Vector2 v = new Vector2();

        v.x = (float) ((int) (pos_x - this.x + (this.cell_w / 2)) / (int) this.cell_w);
        v.x *= this.cell_w;

        v.y = (float) ((int) (pos_y - this.y + (this.cell_h / 2)) / (int) this.cell_h);
        v.y *= this.cell_h;

        if(v.x > this.width) v.x = this.width;
        if(v.y > this.height) v.y = this.height;

        v.add(this.x, this.y);
        return v;
    }

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

    public void clicked(int screenX, int screenY, int pointer, int button) {
    }
}
