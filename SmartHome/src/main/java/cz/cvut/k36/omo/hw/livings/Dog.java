package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.HouseAPI;

/**
 * Class for dog.
 */
public class Dog extends Pet{
    /**
     * Constructor.
     * @param canMove - tells if it's a moving pet
     * @param api - the house
     */
    public Dog(boolean canMove, HouseAPI api){
        super(canMove, api);
    }
}
