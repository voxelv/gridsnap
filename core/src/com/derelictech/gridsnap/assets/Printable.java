package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tim on 1/22/2016.
 */
public class Printable {
    public String str;
    public Vector2 pos;
    public Printable(String str, float x, float y) {
        this.pos = new Vector2(x, y);
        this.str = str;
    }
}
