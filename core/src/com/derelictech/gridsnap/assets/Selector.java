package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tim on 1/21/2016.
 */
public class Selector {
    public float x;
    public float y;
    private int radius;
    private Color color = Color.YELLOW;
    private boolean visible = true;
    public Rectangle collisionBox;

    public Selector() {
        this(0, 0, 5);
    }
    public Selector(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        collisionBox = new Rectangle(this.x - 2.5f, this.y - 2.5f, 5, 5) {
            @Override
            public Rectangle setPosition(float x, float y) {
                super.setPosition(x - 2.5f, y - 2.5f);
                return this;
            }
        };
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setVisible(boolean b) {
        visible = b;
    }

    public void render_steps(ShapeRenderer sr) {
        if(visible){
            sr.set(ShapeRenderer.ShapeType.Filled);
            sr.setColor(color);
            sr.circle(this.x, this.y, this.radius);
        }
    }

    public void setPosition(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }
}
