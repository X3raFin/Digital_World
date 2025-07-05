package plant;

import organism.Organism;
import position.Position;
import world.World;

import java.util.Random;
import java.awt.Color;

public abstract class Plant implements Organism {

    @Override
    public char getType() {
        return type;
    }

    @Override
    public int isStunned() {
        return stunned;
    }

    @Override
    public void setStun() {
        stunned = 3;
    }

    @Override
    public void decrementStun() {
        stunned--;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int x) {
        this.strength = x;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void incrementAge() {
        age++;
    }

    public void multiply() {
        w.createChild(this);
    }

    public Plant(int x, int y, World w) {
        strength = 0;
        initiative = 0;
        this.w = w;
        position = new Position(x, y);
        age = 1;
    }

    public Plant() {

    }

    @Override
    public void collision(Organism org) {
        w.addToMessage(org.getType() + " depcze trawe.\n");
        w.killOrganism(this);
    }

    @Override
    public void action() {
        prop = rand.nextInt(50);
        if (prop == 0) {
            multiply();
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    protected int strength;
    protected int initiative;
    protected int age;
    protected Position position;
    protected World w;
    protected char type;
    protected Random rand = new Random();
    protected int prop;
    protected int stunned = 0;
    protected Color color;
}
