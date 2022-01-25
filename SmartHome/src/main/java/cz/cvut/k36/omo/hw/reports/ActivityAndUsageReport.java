package cz.cvut.k36.omo.hw.reports;

import cz.cvut.k36.omo.hw.appliances.ApplianceAPI;

import java.util.List;

/**
 * Report that tells who used which appliance and how many times.
 */
public class ActivityAndUsageReport extends Report {

    /**
     * Constructor.
     * @param applianceAPIS list of appliances in the house
     */
    public ActivityAndUsageReport(List<ApplianceAPI> applianceAPIS) {
        this.updateMessage("----ACTIVITY AND USAGE REPORT----\n");
        for( ApplianceAPI applianceAPI : applianceAPIS) {
            this.updateMessage(applianceAPI.calculateUsage());
        }
    }
}
