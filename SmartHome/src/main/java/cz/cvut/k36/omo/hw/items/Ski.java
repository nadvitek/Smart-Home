package cz.cvut.k36.omo.hw.items;

import cz.cvut.k36.omo.hw.livings.Person;

/**
 * Class that represents ski in the house.
 */
public class Ski extends Item{
    /**
     * Constructor, sets also the time for using the ski.
     * @param person - gives the Person whose ski it is
     */
    public Ski(Person person) {
        super(person);
        this.setUsingBorders(18, 30);
    }
    
}
