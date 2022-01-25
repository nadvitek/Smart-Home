package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class for representing API of lawnmower.
 */
public class LawnmowerAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - lawnmower of this API
     * @param house - in which it is
     */
    public LawnmowerAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overriden method for using time = mowing the garden if necessary, creating report about it, adding to consumption.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Lawnmower mower = (Lawnmower) getAppliance();
        if (mower.getIsWorking() > 0) {
            mower.setIsWorking(mower.getIsWorking() - 1);
            mower.addToConsumption();
        }
        if(time - mower.getLastMowedTime() >= Times.WEEK) {
            mower.setIsWorking(mower.getGardenArea()/60);
            this.getHouse().getReports().add(new EventReport("mowed the garden.\n", time + mower.getGardenArea()/60, "Lawnmower ", this.getAppliance().getRoom(), 666));
            mower.setLastMowedTime(time);
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
        return  "fixed the lawnmower";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.2);
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
        String usageMessage = "Lawnmower was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
