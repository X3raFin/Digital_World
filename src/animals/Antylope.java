package animals;

import animal.Animal;
import organism.Organism;
import plant.Plant;
import position.Position;
import world.World;

import java.awt.Color;

public class Antylope extends Animal {

    @Override
    public Color draw() {
        return color;
    }

    public Antylope(int x, int y, World w) {
        strength = 4;
        initiative = 4;
        type = 'A';
        this.w = w;
        position = new Position(x, y);
        age = 30;
        color = Color.pink;
    }

    @Override
    public Organism clone(Position p, World w) {
        return new Antylope(p, w);
    }

    public Antylope(Position p, World w) {
        strength = 4;
        initiative = 4;
        type = 'A';
        this.w = w;
        position = p;
        age = 0;
        color = Color.pink;
    }

    @Override
    public void action() {
        move = rand.nextInt(4);
        position.setX(position.getX() + Antylope.dx[move]);
        position.setY(position.getY() + Antylope.dy[move]);
    }

    @Override
    public void collision(Organism org) {
        if (org instanceof Plant)
            org.collision(this);
        else if (type == org.getType()) {
            multiply(org);
        } else {
            if (strength >= org.getStrength()) {
                if (org.getType() == 'H') {
                    setStun();
                    w.addToMessage(type + " zjada jeza i traci 3 kolejki.\n");
                }
                w.addToMessage(getType() + " zabija " + org.getType() + ".\n");
                w.killOrganism(org);
            } else {
                prop = rand.nextInt(2);
                if (prop == 0) {
                    Position newPosition = w.freeSpace(position);
                    if (newPosition != null) {
                        position = newPosition;
                        w.addToMessage("Antylopa ucieka.\n");
                    } else {
                        w.addToMessage(org.getType() + " zabija " + getType() + ".\n");
                        w.killOrganism(this);
                    }
                }
            }
        }
    }

    private static int[] dx = { 2, 0, 0, -2 };
    private static int[] dy = { 0, 2, -2, 0 };
    private int prop;
}
