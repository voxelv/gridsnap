package com.derelictech.gridsnap.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tim on 1/21/2016.
 */
public class Selector {
    public Rectangle collisionBox;

    private Color color = Color.YELLOW;

    public Vector2 position;

    public Circle nodeSelect;
    public Rectangle tileSelect;

    private boolean nodeSelectVisible = true;
    private boolean tileSelectVisible = true;

    public Selector() {
        this.position = new Vector2(0, 0);

        this.nodeSelect = new Circle(position, 5);
        this.tileSelect = new Rectangle(position.x, position.y, 5, 5);
        collisionBox = new Rectangle(this.position.x - 2.5f, this.position.y - 2.5f, 5, 5) {
            @Override
            public Rectangle setPosition(float x, float y) {
                super.setPosition(x - 2.5f, y - 2.5f);
                return this;
            }
        };
    }

    public void setNodeSelectVisible(boolean b) {
        nodeSelectVisible = b;
    }

    public void setTileSelectVisible(boolean b) {
        this.tileSelectVisible = b;
    }

    public boolean isNodeSelectVisible() {
        return nodeSelectVisible;
    }

    public boolean isTileSelectVisible() {
        return tileSelectVisible;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void render_steps(ShapeRenderer sr) {
        if(nodeSelectVisible){
            sr.set(ShapeRenderer.ShapeType.Filled);
            sr.setColor(color);
            sr.circle(this.nodeSelect.x, this.nodeSelect.y, this.nodeSelect.radius);
        }
        if(tileSelectVisible){
            sr.set(ShapeRenderer.ShapeType.Filled);
            sr.setColor(color);
            sr.rect(this.tileSelect.x, this.tileSelect.y, this.tileSelect.width, this.tileSelect.height);
        }
    }

    public void setPosition(Vector2 v) {
        this.nodeSelect.setPosition(v);
        this.tileSelect.setPosition(v);
    }
}
