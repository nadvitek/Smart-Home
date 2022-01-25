package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.HouseAPI;

/**
 * Class for all the grandparents in the house.
 */
public class Grandparent extends Adult{

    /**
     * Constructor - grandparents don't go to work.
     * @param typeOfPerson - name of the person
     * @param house - the house
     * @param wakeUpTime - time when grandparents wake up
     */
    public Grandparent(String typeOfPerson, HouseAPI house, int wakeUpTime) {
        super(typeOfPerson, house, wakeUpTime);
    }
}
