package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.House;

/**
 * Class for representing API of toilette.
 */
public class ToiletteAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - toilette of this API
     * @param house - in which it is
     */
    public ToiletteAPI(Appliance appliance, House house) {
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
        return "fixed the toilette";
    }

    /**
     * Method for using toilette.
     * @param name - of person using it
     */
    public void useToilette(String name){
        Toilette toilette = (Toilette) getAppliance();
        toilette.addToConsumption();
        addUsage(name);
    }

    /**
     * Overridden method for calculating consumption of energy and water, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.00042);
        float waterUsage = (float) ((getAppliance().getConsumption()*4));
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
        String usageMessage = "Toilette was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
