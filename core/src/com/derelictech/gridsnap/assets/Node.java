package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tim on 1/21/2016.
 */
public class Node extends Vector2{
    private Color color;
    private boolean visible = false;
    private int radius = 6;

    public Node(float x, float y) {
        super(x, y);
        color = new Color(Color.WHITE);
        visible = true;
    }

    public void setColor(Color c) {
        color.set(c);
    }

    public Color getColor() {
        return color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean b) {
        visible = b;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int r) {
        this.radius = r;
    }
}
