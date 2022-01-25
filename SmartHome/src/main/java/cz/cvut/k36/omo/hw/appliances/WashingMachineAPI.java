package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.events.EventCreator;
import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of washing machine.
 */
public class WashingMachineAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - washing machine of this API
     * @param house - in which it is
     */
    public WashingMachineAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = creating events to turn washing machine on if it's full.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        WashingMachine washingMachine = (WashingMachine) getAppliance();
        if(washingMachine.getFullOfClothes() >= 20) {
            this.getHouse().getEvents().add(EventCreator.getInstance().createEvent(1, 3, this, 3));
            washingMachine.setFullOfClothes(0);
        }
    }

    /**
     * Overridden method for turning washing machine on.
     * @return true if successfully turned on
     */
    @Override
    public boolean turnOn() {
        WashingMachine washingMachine = (WashingMachine) getAppliance();
        if (!washingMachine.isBroken()) {
            washingMachine.setIsWorking(6);
            washingMachine.addToConsumption();
            this.getHouse().getEvents().add(EventCreator.getInstance().createEvent(1, 2, this, 2));
        }
        return super.turnOn();
    }

    /**
     * Overridden method for solving events.
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return events message
     */
    @Override
    public String solveEvent(int extra, String name) {
        String s = "";
        WashingMachine washingMachine = (WashingMachine) getAppliance();
        switch (extra) {
            case 3:
                turnOn();
                addUsage(name);
                s = "turned on the washing machine";
                break;
            case 2:
                s = "took clean laundry out of the washing machine";
                break;
            case 1:
                washingMachine.setBroken(false);
                s = "fixed the washing machine";
                break;
        };
        return s;
    }

    /**
     * Method for filling washing machine.
     * @param numOfClothes - integer between 1-10
     */
    public void fillWashingMachine(int numOfClothes){
        WashingMachine washingMachine = (WashingMachine) getAppliance();
        washingMachine.setFullOfClothes(washingMachine.getFullOfClothes() + numOfClothes);
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.7);
        float waterUsage = (float) ((getAppliance().getConsumption()*72));
        float price = (float) (energyUsage*13.31);
        price += waterUsage * 0.0015;
        String consumptionMessage = getAppliance().getName() + " used " + energyUsage + " kWh and " + waterUsage + " liters of water" + ", thus costing " + price + "$.\n";
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
        String usageMessage = "Washing machine was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
