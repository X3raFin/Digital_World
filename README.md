🌍 #**_Digital_World_**

The purpose of this project is to create a digital world, which is a small game inhabited by animals and plants. In this world, animals can fight with other species and multiply within their own. Various plants can provide buffs or debuffs to the animals that consume them.

This project was created entirely in Java and served as my first introduction to both the language and the concepts of object-oriented programming.

A key feature is the ability for the user to save the current state of the world—including all animal statistics and positions—to a text file, allowing them to resume their simulation at a later time.

---

### Features

*   **Dynamic Simulation:** A turn-based world where organisms can move, interact, and evolve.
*   **Diverse Organisms:** The world is populated with various species of animals and plants.
*   **Combat & Reproduction:** Animals can engage in combat with different species and reproduce with members of their own kind.
*   **Buffs & Debuffs:** Plants can be consumed by animals to gain special advantages or suffer penalties.
*   **Save/Load System:** The entire state of the simulation can be saved to a file and loaded later.

### The Inhabitants of the World

Our digital world is currently home to the following species:

#### Animals
*   **Wolf:** A natural predator of the ecosystem.
*   **Sheep:** A common prey animal.
*   **Boar:** A tough and resilient animal.
*   **Antelope:** A fast-moving creature.
*   **Hedgehog:** A small, defensive animal.

#### Plants
*   **Grass:** Basic vegetation, likely serves as a primary food source.
*   **Guarane:** (Guarana) A special plant that provides a strength boost.
*   **Wolfberries:** A poisonous plant that is fatal when consumed.

### How It Works: Core Mechanics

The simulation operates on a turn-based system where actions are resolved in order of each organism's `initiative` value. Here are the core concepts that govern the world:

#### 1. Core Attributes
Every organism in the world is defined by a set of key attributes:
*   **Strength:** The primary statistic used in combat. An organism with higher strength will defeat an organism with lower strength.
*   **Initiative:** Determines the order of action in a turn. Organisms with higher initiative act first.
*   **Age:** Increases with each turn. It is a key factor for reproduction.
*   **Position:** The (x, y) coordinates of the organism on the world grid.
*   **Stun:** A temporary state where an organism cannot act for a set number of turns.

#### 2. Turn-Based Actions
In each turn, organisms perform an `action()`:
*   **Animals:** Move to a random, adjacent tile.
*   **Plants:** Have a small (2%) chance of spreading to an adjacent empty tile.

#### 3. Interactions & Collisions
When an animal moves onto a tile occupied by another organism, a `collision` occurs:

*   **Animal vs. Same Species Animal:** If both animals are old enough (age >= 30), they will reproduce, creating a new organism of their kind on a nearby empty tile.
*   **Animal vs. Different Species Animal:** The two animals will `fight()`. The one with lower `strength` is killed and removed from the simulation.
    *   **Hedgehog's Defense:** Any animal that attacks a Hedgehog (even if it wins) becomes stunned for 2 turns as a penalty.
*   **Animal vs. Plant:** The animal attempts to eat the plant, triggering the plant's unique `collision` effect.

#### 4. Plant Effects (Buffs & Debuffs)
The true diversity of the world comes from its flora. Consuming a plant has a permanent effect:
*   **Grass:** Serves as basic food and is simply consumed with no special effect.
*   **Guarane:** A beneficial plant. The consuming animal gains a permanent **+3 Strength** bonus.
*   **Wolfberries:** A poisonous plant. The consuming animal **dies instantly**.
