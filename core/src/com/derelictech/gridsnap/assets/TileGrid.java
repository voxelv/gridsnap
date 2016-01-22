package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 1/21/2016.
 */
public class TileGrid extends Grid {
    private List<Tile> tiles;

    public TileGrid(int x, int y, int width, int height, int x_cells, int y_cells, Color c) {
        super(x, y, width, height, x_cells, y_cells, c);
        tiles = new ArrayList<Tile>();
    }

    @Override
    public Vector2 snap(Vector2 pos) {
        Vector2 v = new Vector2(pos);

        v.set(v.sub(this.x, this.y));
        v.set((int) (v.x / this.cell_w), (int) (v.y / this.cell_h));
        v.set(v.x * this.cell_w, v.y * this.cell_h);
        v.add(this.x, this.y);
        return v;
    }

    @Override
    public void clicked(float screenX, float screenY, int pointer, int button) {

    }
}
