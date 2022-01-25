package cz.cvut.k36.omo.hw.reports;

import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Report for the configuration of the house.
 */
public class HouseConfigurationReport extends Report {

    /**
     * Constructor.
     * @param house - the house
     */
    public HouseConfigurationReport(House house) {
        this.updateMessage("----CONFIGURATION OF THE HOUSE----\n");
        this.updateMessage(house.toString());
    }
}
