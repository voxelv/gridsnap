package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;

/**
 * Created by Tim on 1/10/2016.
 */
public class Grid extends Rectangle {
    private float x_cells, y_cells;
    private float cell_w, cell_h;
    private Color color;

    public Grid(int x, int y, int width, int height, int x_cells, int y_cells, Color c) {
        super(x, y, width, height);
        this.x_cells = x_cells;
        this.y_cells = y_cells;
        this.cell_w = width / x_cells;
        this.cell_h = height / y_cells;
        this.color = c;
    }

    public void render(ShapeRenderer sr) {
        sr.setAutoShapeType(true);
        sr.begin();
        sr.setColor(color);
        for(int i = 0; i < x_cells + 1; i++){
            sr.line(x + (i * cell_w), y, x + (i * cell_w), (y+(y_cells * cell_h)));
        }
        for(int i = 0; i < y_cells + 1; i++){
            sr.line(x, y + (i * cell_h), (x+(x_cells * cell_w)), y + (i * cell_h));
        }
        sr.end();
    }

    public Vector2 snap(Vector2 pos) {
        Vector2 v = new Vector2(0, 0);

        v.x = (pos.x - this.x + (this.cell_w / 2));
        v.y = (pos.y - this.y + (this.cell_h / 2));

        v.x = (float) ((int) v.x / (int) this.cell_w);
        v.x = v.x * this.cell_w;
        v.y = (float) ((int) v.y / (int) this.cell_h);
        v.y = v.y * this.cell_h;

        if(v.x > this.width) v.x = this.width;
        if(v.y > this.height) v.y = this.height;

        v.add(this.x, this.y);
        return v;
    }
}
