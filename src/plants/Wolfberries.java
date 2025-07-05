package plants;

import organism.Organism;
import plant.Plant;
import position.Position;
import world.World;

import java.awt.Color;

public class Wolfberries extends Plant {
    public Wolfberries(int x, int y, World w) {
        super(x, y, w);
        color = Color.magenta;
        type = 'O';
    }

    public Wolfberries(Position p, World w) {
        strength = 0;
        initiative = 0;
        type = 'O';
        this.w = w;
        position = p;
        age = 1;
        color = Color.magenta;
    }

    @Override
    public void collision(Organism org) {
        w.addToMessage(
                org.getType() + " zjada Wolfberries i umiera.\n");
        w.killOrganism(org);
        w.killOrganism(this);
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Wolfberries(p, w);
    }

    @Override
    public Color draw() {
        return color;
    }
}
