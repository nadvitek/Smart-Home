package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of oven.
 */
public class OvenAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - oven of this API
     * @param house - in which it is
     */
    public OvenAPI(Appliance appliance, House house) {
        super(appliance, house);
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
        return  "called a handyman to fix the oven";
    }

    /**
     * Method for using oven and creating report about it.
     * @param name - of person using it
     * @return true, if successfully used
     */
    public boolean useOven(String name){
        if (getAppliance().isBroken()){
            return false;
        } else {
            getAppliance().addToConsumption();
            this.getHouse().getReports().add(new EventReport("was used", getCurrentTime(), "Oven", this.getAppliance().getRoom(), 0));
            addUsage(name);
            return true;
        }
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float usage = (float) (getAppliance().getConsumption()*1.65);
        float price = (float) (usage*13.31);
        String consumptionMessage = getAppliance().getName() + " used " + usage + " kWh, thus costing " + price + "$.\n";
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
        String usageMessage = "Oven was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
