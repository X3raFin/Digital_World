package organism;

import java.awt.Color;

import position.Position;
import world.World;

public interface Organism {
    int getStrength();

    void setStrength(int x);

    int getInitiative();

    int getAge();

    void incrementAge();

    void collision(Organism org);

    void action();

    Color draw();

    Position getPosition();

    char getType();

    Organism clone(Position p, World w);

    void setStun();

    void decrementStun();

    int isStunned();
}