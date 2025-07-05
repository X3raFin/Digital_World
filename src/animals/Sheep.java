package animals;

import animal.Animal;
import organism.Organism;
import position.Position;
import world.World;

import java.awt.Color;

public class Sheep extends Animal {

    @Override
    public Color draw() {
        return color;
    }

    public Sheep(int x, int y, World w) {
        strength = 4;
        initiative = 4;
        type = 'S';
        this.w = w;
        position = new Position(x, y);
        age = 30;
        color = Color.gray;
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Sheep(p, w);
    }

    public Sheep(Position p, World w) {
        strength = 4;
        initiative = 4;
        type = 'S';
        this.w = w;
        position = p;
        age = 0;
        color = Color.gray;
    }
}
