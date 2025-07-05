package animals;

import animal.Animal;
import organism.Organism;
import position.Position;
import world.World;

import java.awt.Color;

public class Boar extends Animal {

    @Override
    public Color draw() {
        return color;
    }

    public Boar(int x, int y, World w) {
        strength = 12;
        initiative = 1;
        type = 'B';
        this.w = w;
        position = new Position(x, y);
        age = 30;
        color = Color.black;
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Boar(p, w);
    }

    public Boar(Position p, World w) {
        strength = 12;
        initiative = 1;
        type = 'B';
        this.w = w;
        position = p;
        age = 0;
        color = Color.BLACK;
    }
}
