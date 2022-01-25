package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class for representing API of lights.
 */
public class LightsAPI extends ApplianceAPI {
    private LightSensorsAPI sensors;

    /**
     * Constructor.
     * @param appliance - lights of this API
     * @param sensors - sensors connected to the lights
     * @param house - in which it is
     */
    public LightsAPI(Appliance appliance, LightSensorsAPI sensors, House house) {
        super(appliance, house);
        this.sensors = sensors;
    }

    /**
     * Overridden method for using time = turning lights on/off, adding to consumption.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Lights lights = (Lights) getAppliance();
        if (lights.isOn()){
            lights.addToConsumption();
        }
        if (lights.isOn() && time / 144 == Times.SUNRISE) {
            lights.setOn(false);
        }
        if (time / Times.DAY > Times.SUNFALL || time / Times.DAY < Times.SUNRISE) {
            if (lights.getRoom().howManyPplInRoom() == 0) {
                lights.setOn(false);
            }
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
        return "fixed the lights";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) (getAppliance().getConsumption()*0.00333);
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
        String usageMessage = "Lights were used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
