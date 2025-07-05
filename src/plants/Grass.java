package plants;

import organism.Organism;
import plant.Plant;
import position.Position;
import world.World;

import java.awt.Color;

public class Grass extends Plant {

    public Grass(int x, int y, World w) {
        super(x, y, w);
        color = Color.green;
        type = 'G';
    }

    public Grass(Position p, World w) {
        strength = 0;
        initiative = 0;
        type = 'G';
        this.w = w;
        position = p;
        age = 1;
        color = Color.green;
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Grass(p, w);
    }

    @Override
    public Color draw() {
        return color;
    }

}
