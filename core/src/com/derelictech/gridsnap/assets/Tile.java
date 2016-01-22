package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tim on 1/21/2016.
 */
public class Tile extends Rectangle {
    public Tile(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Tile(Rectangle rect) {
        super(rect);
    }
}
