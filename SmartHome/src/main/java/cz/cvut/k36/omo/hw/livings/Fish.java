package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.HouseAPI;
/**
 * Pet that doesn't move, it lives in its aquarium.
 */
public class Fish extends Pet{
    /**
     * Constructor.
     * @param canMove - tells if it's a moving pet
     * @param api - the house
     */
    public Fish(boolean canMove, HouseAPI api){
        super(canMove, api);
    }
}
