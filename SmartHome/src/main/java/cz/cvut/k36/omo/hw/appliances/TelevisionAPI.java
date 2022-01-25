package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of .
 */
public class TelevisionAPI extends ApplianceAPI {

    /**
     * Constructor
     * @param appliance - television of this API
     * @param house - in which it is
     */
    public TelevisionAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Method for watching tv and creating report about it
     * @param name - of person watching it
     */
    public void watchTV(String name){
        Television television = (Television) getAppliance();
        if (!television.isBroken()) {
            television.setOn(true);
            this.getHouse().getReports().add(new EventReport("is playing", getCurrentTime(), "TV", this.getAppliance().getRoom(), 0));
            television.setIsWorking(6);
            television.addToConsumption();
            addUsage(name);
        }
    }

    /**
     * Overridden method for solving events
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return event message
     */
    @Override
    public String solveEvent(int extra, String name) {
        getAppliance().setBroken(false);
        return "fixed the TV";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float usage = (float) (getAppliance().getConsumption()*0.14);
        float price = (float) (usage*13.31);
        String consumptionMessage = getAppliance().getName() + " used " + usage + " kWh, thus costing " + price + "$.\n";
        return consumptionMessage;
    }

    /**
     * Overridden method for calculating who used it and how many times
     * @return message describing usage
     */
    @Override
    public String calculateUsage() {
        if (getAppliance().getUsage().size() == 0){
            return "";
        }
        String usageMessage = "TV was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
