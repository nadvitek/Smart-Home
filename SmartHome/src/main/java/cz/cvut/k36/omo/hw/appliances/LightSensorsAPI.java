package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class for representing API of light sensors.
 */
public class LightSensorsAPI extends ApplianceAPI {
    private LightsAPI lights;

    /**
     * Constructor.
     * @param appliance - light sensors of this API
     * @param house - in which it is
     */
    public LightSensorsAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Method for when sensors catch movement (turns lights on).
     */
    public void sensorsCatchMovement() {
        LightSensors sensors = (LightSensors) getAppliance();
        sensors.addToConsumption();
        sensors.setMovement(true);
        lights.turnOn();
        sensors.setMovement(false);
    }

    /**
     * Overridden method for using time = adding to consumption when it's on and turns off when there's lights out.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        LightSensors sensors = (LightSensors) getAppliance();
        if(sensors.isOn()){
            sensors.addToConsumption();
        }
        if (time / Times.DAY == Times.SUNRISE) {
            sensors.setOn(false);
        }
        if (time / Times.DAY == Times.SUNFALL) {
            sensors.setOn(true);
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
        return "fixed the light sensors";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float energyUsage = (float) ((getAppliance().getConsumption()/(6*24))*0.003);
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
        String usageMessage = "Light sensors were used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }

    /**
     * Method for setting the lights of these sensors.
     * @param lights
     */
    public void setLights(LightsAPI lights) {
        this.lights = lights;
    }
    
}
