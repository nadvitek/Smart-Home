package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class for representing API of fish tank filter.
 */
public class FishTankFilterAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - fish tank filter of this API
     * @param house - in which it is
     */
    public FishTankFilterAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = cleaning tank if it's dirty (every week).
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        FishTankFilter filter = (FishTankFilter) getAppliance();
        if (!filter.isBroken() && !filter.isClean()) {
            filter.cleanTank(time);
            this.getHouse().getReports().add(new EventReport("cleaned the fish tank", time, "Fish tank filter", this.getAppliance().getRoom(), 0));
            filter.addToConsumption();
        }
        if (time - filter.getLastCleaned() >= Times.WEEK){
            filter.tankFilthy();
        }
    }

    /**
     * Overridden method for solving events.
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return event message
     */
    @Override
    public String solveEvent(int extra, String name) {
        getAppliance().setBroken(false);
        return "fixed the fish tank filter";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.019);
        float price = (float) (energyUsage*13.31);
        String consumptionMessage = getAppliance().getName() + " used " + energyUsage + " kWh, thus costing " + price + "$.\n";
        return consumptionMessage;
    }

    /**
     * Overridden method for calculating who used it and how many times.
     * @return message describing usage
     */
    @Override
    public String calculateUsage() {
        if (getAppliance().getUsage().size() == 0){
            return "";
        }
        String usageMessage = "Fish tank filter was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
