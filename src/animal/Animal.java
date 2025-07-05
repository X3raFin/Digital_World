package animal;

import organism.Organism;
import plant.Plant;
import position.Position;
import world.World;

import java.util.Random;
import java.awt.Color;

public abstract class Animal implements Organism {

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
        stunned = 2;
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
        this.strength += x;
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

    public void fight(Organism org) {
        if (strength >= org.getStrength()) {
            if (org.getType() == 'H') {
                setStun();
                w.addToMessage(type + " zjada jeza i traci 2 kolejki.\n");
            }
            w.addToMessage(getType() + " zabija " + org.getType() + ".\n");
            w.killOrganism(org);
        } else {
            w.addToMessage(org.getType() + " zabija " + getType() + ".\n");
            w.killOrganism(this);
        }
    }

    public void multiply(Organism org) {
        if (age >= 30 && org.getAge() >= 30)
            w.createChild(this);
        else {
            position = w.freeSpace(position);
            w.addToMessage(
                    org.getType() + " nie rozmnozyl sie, nie ma 30 lat. Za to przechodzi na sasiednie wolne Pole.\n");
        }
    }

    @Override
    public void collision(Organism org) {
        if (org instanceof Plant)
            org.collision(this);
        else if (type == org.getType())
            multiply(org);
        else
            fight(org);
    }

    @Override
    public void action() {
        move = rand.nextInt(4);
        position.setX(position.getX() + Position.getIndexOfDx(move));
        position.setY(position.getY() + Position.getIndexOfDy(move));
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
    protected int move;
    protected int stunned = 0;
    protected Color color;
}
