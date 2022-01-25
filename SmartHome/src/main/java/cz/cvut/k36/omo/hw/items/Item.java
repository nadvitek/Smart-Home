package cz.cvut.k36.omo.hw.items;

import cz.cvut.k36.omo.hw.livings.Person;
import cz.cvut.k36.omo.hw.smarthome.Room;
import java.util.Random;

/**
 * Item class that represents Bike or Ski.
 */
public abstract class Item {
    private final Person usingPerson;
    private int lowerBorder;
    private int upperBorder;

    /**
     * Constructor, sets also the time for using the item.
     * @param person - gives the Person whose item it is
     */
    public Item(Person person) {
        this.usingPerson = person;
    }

    /**
     * This method sets time for using the item.
     * @param lower - the least time spend with the item
     * @param upper - the most time spend with the item
     */
    public void setUsingBorders(int lower, int upper) {
        this.lowerBorder = lower;
        this.upperBorder = upper;
    }

    /**
     * Method that returns a random value for using this item.
     * @return a random value for using this item
     */
    public int getUsingTime() {
        Random rand = new Random();
        return rand.nextInt((this.upperBorder - this.lowerBorder) + 1) + this.lowerBorder;
    }
}
