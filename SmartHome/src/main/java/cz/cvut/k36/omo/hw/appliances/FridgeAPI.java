package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.events.EventCreator;
import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of fridge.
 */
public class FridgeAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - fridge of this API
     * @param house - in which it is
     */
    public FridgeAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = checking if fridge is full otherwise creating event for filling it, adding to consumption if it's on.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Fridge fridge = (Fridge)getAppliance();
        if(fridge.isOn()){
            fridge.addToConsumption();
        }
        if(fridge.getStacked() < 2 && fridge.getIsWorking() == 0) {
            this.getHouse().getEvents().add(EventCreator.getInstance().createEvent(2, 6, this, 2));
            fridge.setIsWorking(50);
        }
    }

    /**
     * Method to fill fridge.
     */
    public void fillFridge(){
        Fridge fridge = (Fridge)getAppliance();
        fridge.setStacked(30);
    }

    /**
     * Method for taking food out of fridge.
     * @param howMuch - how much food gets taken out (1-30)
     * @return how much food was taken out
     */
    public int takeFoodOut(int howMuch) {
        Fridge fridge = (Fridge)getAppliance();
        int currentlyInFridge = fridge.getStacked();
        if (currentlyInFridge >= howMuch) {
            fridge.setStacked(currentlyInFridge - howMuch);
            return howMuch;
        } else {
            fridge.setStacked(0);
            return currentlyInFridge;
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
        String s = "";
        Fridge fridge = (Fridge) getAppliance();
        switch (extra) {
            case 2:
                fillFridge();
                fridge.setIsWorking(0);
                s = "restocked the fridge";
                break;
            case 1:
                fridge.setBroken(false);
                s = "called a handyman to fix the fridge";
                break;
        };
        return s;
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.0125);
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
        String usageMessage = "Fridge was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
