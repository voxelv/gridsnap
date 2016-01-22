package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tim on 1/21/2016.
 */
public class Node extends Vector2{
    public Vector2 gridCoords;
    private Color color;
    private boolean visible = false;
    private int radius = 6;

    public Node(int grid_x, int grid_y, float x, float y) {
        super(x, y);
        gridCoords = new Vector2(grid_x, grid_y);
        color = new Color(Color.WHITE);
        visible = true;
    }

    public Node(int grid_x, int grid_y, Vector2 pos) {
        this(grid_x, grid_y, pos.x, pos.y);
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
