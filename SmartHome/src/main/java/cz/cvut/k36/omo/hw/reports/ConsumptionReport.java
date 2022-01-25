package cz.cvut.k36.omo.hw.reports;

import cz.cvut.k36.omo.hw.appliances.ApplianceAPI;

import java.util.List;

/**
 * Report that tells how many kwH has been spent and how much money we must pay.
 */
public class ConsumptionReport extends Report {

    /**
     * Constructor.
     * @param applianceAPIS list of appliances in the house
     */
    public ConsumptionReport(List<ApplianceAPI> applianceAPIS) {
        this.updateMessage("----CONSUMPTION REPORT----\n");
        for( ApplianceAPI applianceAPI : applianceAPIS) {
            this.updateMessage(applianceAPI.calculateConsumption());
        }
    }
}
