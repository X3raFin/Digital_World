package plants;

import organism.Organism;
import plant.Plant;
import position.Position;
import world.World;

import java.awt.Color;

public class Guarane extends Plant {

    public Guarane(int x, int y, World w) {
        super(x, y, w);
        color = Color.CYAN;
        type = 'U';
    }

    public Guarane(Position p, World w) {
        strength = 0;
        initiative = 0;
        type = 'U';
        this.w = w;
        position = p;
        age = 1;
        color = Color.CYAN;
    }

    @Override
    public void collision(Organism org) {
        org.setStrength(3);
        w.addToMessage(
                org.getType() + " zjada Guarane i otrzymuje +3 sily. Ma w sumie " + org.getStrength() + " sily.\n");
        w.killOrganism(this);
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Guarane(p, w);
    }

    @Override
    public Color draw() {
        return color;
    }
}
