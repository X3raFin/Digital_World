package animals;

import java.awt.Color;

import animal.Animal;
import organism.Organism;
import position.Position;
import world.World;

public class Wolf extends Animal {

    @Override
    public Color draw() {
        return color;
    }

    public Wolf(int x, int y, World w) {
        strength = 9;
        initiative = 5;
        type = 'W';
        this.w = w;
        position = new Position(x, y);
        age = 30;
        color = Color.BLUE;
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Wolf(p, w);
    }

    public Wolf(Position p, World w) {
        strength = 9;
        initiative = 5;
        type = 'W';
        this.w = w;
        position = p;
        age = 0;
        color = Color.BLUE;
    }
}
