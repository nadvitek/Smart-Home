package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.HouseAPI;

/**
 * Class that is a superclass of all adult people in the house.
 */
public class Adult extends Person {
    /**
     * Constructor.
     * @param typeOfPerson - name of the person
     * @param house - the house
     * @param wakeUpTime - time when the adult wakes up
     */
    public Adult(String typeOfPerson, HouseAPI house, int wakeUpTime) {
        super(typeOfPerson, house, wakeUpTime);
    }
}
