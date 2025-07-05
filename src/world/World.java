package world;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import animals.*;
import organism.Organism;
import plant.Plant;
import plants.*;
import position.Position;

import java.util.Scanner;

public class World {

    public World(Integer N) {
        this.N = N;
        instance = this;
        window = new MainWindow(N, instance);
        window.show();
    }

    public void spawn(ArrayList<Organism> organisms) {
        for (Organism org : organisms) {
            this.organisms.add(org);
        }
        organismsSorted(this.organisms);
        window.drawWorld(organisms);
    }

    public void organismsSorted(ArrayList<Organism> org) {
        Organism tmp;
        for (int i = 0; i < org.size() - 1; i++) {
            for (int j = 0; j < org.size() - i - 1; j++) {
                if (org.get(j).getInitiative() < org.get(j + 1).getInitiative() ||
                        (org.get(j).getInitiative() == org.get(j + 1).getInitiative() &&
                                org.get(j).getAge() < org.get(j + 1).getAge())) {
                    tmp = org.get(j);
                    org.set(j, org.get(j + 1));
                    org.set(j + 1, tmp);
                }
            }
        }
    }

    public void ifOutOfWorld(Position p) {
        if (p.getX() < 0)
            p.setX(0);
        if (p.getX() >= N)
            p.setX(N - 1);
        if (p.getY() < 0)
            p.setY(0);
        if (p.getY() >= N)
            p.setY(N - 1);
    }

    public void killOrganism(Organism org) {
        died.add(org);
    }

    public void addToMessage(String msg) {
        message += msg;
    }

    public Position freeSpace(Position parentPosition) {
        boolean occupied = false;
        for (int i = 0; i < 4; i++) {
            occupied = false;
            if (parentPosition.getX() + Position.getIndexOfDx(i) >= 0
                    && parentPosition.getX() + Position.getIndexOfDx(i) < N
                    && parentPosition.getY() + Position.getIndexOfDy(i) >= 0
                    && parentPosition.getY() + Position.getIndexOfDy(i) < N) {
                for (Organism otherOrganism : organisms) {
                    if (parentPosition.getX() + Position.getIndexOfDx(i) == otherOrganism.getPosition().getX()
                            && parentPosition.getY() + Position.getIndexOfDy(i) == otherOrganism.getPosition().getY()) {
                        occupied = true;
                        break;
                    }
                }
            } else
                continue;
            if (!occupied) {
                return new Position(parentPosition.getX() + Position.getIndexOfDx(i),
                        parentPosition.getY() + Position.getIndexOfDy(i));
            }
        }
        return null;
    }

    public void createChild(Organism parent) {
        Position childPosition = freeSpace(parent.getPosition());
        if (childPosition != null) {
            newBorn.add(parent.clone(childPosition, instance));
            message += parent.getType() + " rozmnozyl sie. Potomek znajduje sie na pozycji " + childPosition.getX()
                    + " " + childPosition.getY() + ".\n";
        } else
            message += parent.getType() + " nie rozmnożył się, brak miejsca.\n";
    }

    public void makeRound() {
        message = "";
        message += ("Round number " + round + ".\n");
        for (Organism org : organisms) {
            if (died.contains(org))
                continue;
            if (org.isStunned() > 0) {
                org.decrementStun();
                message += org.getType() + " nie rusza sie jeszcze przez " + (org.isStunned() + 1) + " kolejek.\n";
                continue;
            }
            org.action();
            ifOutOfWorld(org.getPosition());
            for (Organism anotherOrganism : organisms) {
                if (org == anotherOrganism)
                    continue;
                if (died.contains(anotherOrganism))
                    continue;
                if (org.getPosition().getX() == anotherOrganism.getPosition().getX()
                        && org.getPosition().getY() == anotherOrganism.getPosition().getY()) {
                    org.collision(anotherOrganism);
                }
            }
            if (!(org instanceof Plant))
                message += org.getType() + " przemieścił się na miejsce " + org.getPosition().getX() + ", "
                        + org.getPosition().getY() + "\n";
            org.incrementAge();
        }

        for (Organism child : newBorn) {
            organisms.add(child);
        }
        organisms.removeAll(died);
        if (newBorn.isEmpty() == false || died.isEmpty() == false)
            organismsSorted(organisms);
        newBorn.clear();
        died.clear();
        window.cleanWorld();
        window.messageAppend(message);
        window.drawWorld(organisms);
        round++;
    }

    public static void readFromFile(String name) {
        File file = new File(name);
        ArrayList<Organism> createdOrganisms = new ArrayList<Organism>();
        try (Scanner scanner = new Scanner(file)) {
            World world = new World(scanner.nextInt());
            while (scanner.hasNext()) {
                String type = scanner.next();
                Position newPos = new Position(scanner.nextInt(), scanner.nextInt());
                world.ifOutOfWorld(newPos);
                switch (type) {
                    case "W":
                        createdOrganisms.add(
                                new Wolf(newPos, world));
                        break;
                    case "S":
                        createdOrganisms.add(
                                new Sheep(newPos, world));
                        break;
                    case "B":
                        createdOrganisms.add(
                                new Boar(newPos, world));
                        break;
                    case "A":
                        createdOrganisms.add(
                                new Antylope(newPos, world));
                        break;
                    case "H":
                        createdOrganisms.add(
                                new Hedgehog(newPos, world));
                        break;
                    case "G":
                        createdOrganisms.add(
                                new Grass(newPos, world));
                        break;
                    case "U":
                        createdOrganisms.add(
                                new Guarane(newPos, world));
                        break;
                    case "O":
                        createdOrganisms.add(
                                new Wolfberries(newPos, world));
                        break;
                    default:
                        // createdOrganisms.getLast().setStrength(y);
                        break;
                }
            }
            world.spawn(createdOrganisms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(String name, World w) {
        try (PrintWriter writer = new PrintWriter(name)) {
            writer.println(w.N);
            for (Organism org : w.organisms) {
                if (org instanceof Plant) {
                    writer.println(org.getType() + " " + org.getPosition().getX() + " " + org.getPosition().getY());
                } else
                    writer.println(org.getType() + " " + org.getPosition().getX() + " " + org.getPosition().getY());
            }
        } catch (IOException e) {

        }

    }

    private static World instance;
    private Integer N;
    private MainWindow window;
    private int round = 1;
    private ArrayList<Organism> organisms = new ArrayList<Organism>();
    private ArrayList<Organism> newBorn = new ArrayList<Organism>();
    private ArrayList<Organism> died = new ArrayList<Organism>();
    private String message;
}