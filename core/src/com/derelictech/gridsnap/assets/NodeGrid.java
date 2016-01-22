package com.derelictech.gridsnap.assets;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 1/20/2016.
 */
public class NodeGrid extends Grid {

    private List<Node> nodes;
    private Color nodecolor = new Color(Color.WHITE);

    public NodeGrid(int x, int y, int width, int height, int x_cells, int y_cells, Color c) {
        super(x, y, width, height, x_cells, y_cells, c);

        nodes = new ArrayList<Node>();
    }

    @Override
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

    private void addNode(int grid_x, int grid_y) {
        for(Node n : nodes){
            if(n.gridCoords.x == grid_x && n.gridCoords.y == grid_y) return;
        }

        Node n = new Node(grid_x, grid_y, this.x + (grid_x * cell_w), this.y + (grid_y * cell_h));
        n.setColor(nodecolor.sub(0.01f, 0.02f, 0.03f, 1));
        n.setRadius(5);
        nodes.add(n);
    }

    private void addNode(Vector2 gridCoords) {
        addNode((int) gridCoords.x, (int) gridCoords.y);
    }

    @Override
    public void render_steps(ShapeRenderer sr) {
        super.render_steps(sr);

        Vector2 nodepos;
        sr.set(ShapeRenderer.ShapeType.Filled);
        for(Node node : nodes) {
            node.render_steps(sr);
        }
    }

    @Override
    public void clicked(float screenX, float screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            addNode(gridCoordFromPos(snap(screenX, screenY)));
        }
    }
}
