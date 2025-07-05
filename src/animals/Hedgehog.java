package animals;

import animal.Animal;
import organism.Organism;
import plant.Plant;
import position.Position;
import world.World;

import java.awt.Color;

public class Hedgehog extends Animal {

    @Override
    public Color draw() {
        return color;
    }

    public Hedgehog(int x, int y, World w) {
        strength = 2;
        initiative = 3;
        type = 'H';
        this.w = w;
        position = new Position(x, y);
        age = 30;
        color = Color.orange;
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Hedgehog(p, w);
    }

    public Hedgehog(Position p, World w) {
        strength = 2;
        initiative = 3;
        type = 'H';
        this.w = w;
        position = p;
        age = 0;
        color = Color.orange;
    }

    @Override
    public void collision(Organism org) {
        if (org instanceof Plant)
            org.collision(this);
        else if (type == org.getType()) {
            multiply(org);
        } else {
            if (strength >= org.getStrength()) {
                w.addToMessage(getType() + " zabija " + org.getType() + ".\n");
                w.killOrganism(org);
            } else {
                org.setStun();
                w.addToMessage(org.getType() + " zjada jeza i traci 2 kolejki.\n ");
                w.addToMessage(org.getType() + " zabija " + getType() + ".\n");
                w.killOrganism(this);
            }
        }
    }
}
