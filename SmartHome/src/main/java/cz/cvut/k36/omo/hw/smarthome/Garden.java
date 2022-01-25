package cz.cvut.k36.omo.hw.smarthome;

import cz.cvut.k36.omo.hw.appliances.LawnmowerAPI;
import cz.cvut.k36.omo.hw.items.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for the garden.
 */
public class Garden {
    private final int area;
    private List<Item> items = new ArrayList();
    private LawnmowerAPI lawnmowerAPI;

    /**
     * Constructor - sets the area of the garden.
     * @param area - area of the garden
     */
    public Garden(int area) {
        this.area = area;
    }

    /**
     * Method that returns area of the garden.
     * @return the area of the garden
     */
    public int getArea() {
        return this.area;
    }

    /**
     * Method that sets the lawnmower for the garden.
     * @param lawnmowerAPI - api for the lawnmower
     */
    public void setLawnmowerAPI(LawnmowerAPI lawnmowerAPI) {
        this.lawnmowerAPI = lawnmowerAPI;
    }
}
