package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of hub.
 */
public class HubAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - hub of this API
     * @param house - in which it is
     */
    public HubAPI(Appliance appliance, House house) {
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
        return "fixed the Hub";
    }

    /**
     * Method for listening to music = turning hub on.
     * @param name - of person listening
     * @return true if hub was successfully turned on
     */
    public boolean listenToMusic(String name){
        if(getAppliance().isBroken()){
            return false;
        } else {
            getAppliance().addToConsumption();
            this.getHouse().getReports().add(new EventReport("is playing music", getCurrentTime(), "Hub", this.getAppliance().getRoom(), 0));
            getAppliance().setIsWorking(6);
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
        float energyUsage = (float) (getAppliance().getConsumption()*0.05);
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
        String usageMessage = "Hub was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
