package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of heating.
 */
public class HeatingAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - heating of this API
     * @param house - in which it is
     */
    public HeatingAPI(Heating appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = adding to consumption if it's on, turning on/off according to people in room.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        if(getAppliance().isOn()){
            getAppliance().addToConsumption();
        }
        if (getAppliance().getRoom().getPeople().isEmpty()){
            getAppliance().setOn(false);
        } else {
            getAppliance().setOn(true);
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
        return "fixed the heating";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.22);
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
        String usageMessage = "Heating was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
