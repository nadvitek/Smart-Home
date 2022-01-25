package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.events.EventCreator;
import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of dishwasher.
 */
public class DishwasherAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - dishwasher of this API
     * @param house - in which it is
     */
    public DishwasherAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = creating events.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Dishwasher dishwasher = (Dishwasher) getAppliance();
        if(dishwasher.getFullOfDishes() == 10) {
            this.getHouse().getEvents().add(EventCreator.getInstance().createEvent(4, 3, this, 3));
            dishwasher.setFullOfDishes(0);
        }
    }

    /**
     * Overridden method to turn dishwasher on.
     * @return true if dishwasher was successfully turned on
     */
    @Override
    public boolean turnOn() {
        Dishwasher dishwasher = (Dishwasher) getAppliance();
        if (!dishwasher.isBroken()) {
            dishwasher.addToConsumption();
            dishwasher.setIsWorking(6);
            this.getHouse().getEvents().add(EventCreator.getInstance().createEvent(1, 2, this, 2));
        }
        return super.turnOn();
    }

    /**
     * Method to fill dishwasher.
     * @param numOfDishes - integer how much to fill the dishwasher
     */
    public void fillDishwasher(int numOfDishes){
        Dishwasher dishwasher = (Dishwasher) getAppliance();
        dishwasher.setFullOfDishes(dishwasher.getFullOfDishes() + numOfDishes);
    }

    /**
     * Overridden method for solving events.
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return event message (String)
     */
    @Override
    public String solveEvent(int extra, String name) {
        String s = "";
        switch (extra) {
            case 3:
                turnOn();
                addUsage(name);
               s = "turned on the dishwasher";
               break;
            case 2:
               s = "took clean dishes out of the dishwasher";
               break;
            case 1:
                getAppliance().setBroken(false);
               s = "called a handyman to fix the dishwasher";
               break;
        };
        return s;
    }

    /**
     * Overridden method for calculating consumption of energy and water, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) ((getAppliance().getConsumption()*1.8));
        float waterUsage = (float) ((getAppliance().getConsumption()*11));
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
        String usageMessage = "Dishwasher was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
