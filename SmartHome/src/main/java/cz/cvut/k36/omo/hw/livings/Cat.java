package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.HouseAPI;

/**
 * Class for a living cat.
 */
public class Cat extends Pet{

    /**
     * Constructor.
     * @param canMove - tells if it's a moving pet
     * @param api - the house
     */
    public Cat(boolean canMove, HouseAPI api){
        super(canMove, api);
    }
}
