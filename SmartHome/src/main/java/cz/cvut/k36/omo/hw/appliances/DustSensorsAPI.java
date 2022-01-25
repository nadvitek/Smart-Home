package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class for representing API of dust sensors.
 */
public class DustSensorsAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - dust sensors of this API
     * @param house - in which it is
     */
    public DustSensorsAPI(Appliance appliance, House house) {
        super(appliance, house);
        DustSensors dustSensors = (DustSensors) getAppliance();
        dustSensors.setOn(true);
    }

    /**
     * Overridden method for using time = adding to consumption if sensors are on.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        DustSensors dustSensors = (DustSensors) getAppliance();
        if(dustSensors.isOn()){
            dustSensors.addToConsumption();
        }
        if (time % Times.DAY == 0) {
            dustSensors.setDustInRoom(dustSensors.getDustInRoom() + 1);
        }
    }

    /**
     * Method to get how much dust there is in room.
     * @return integer of how much dust
     */
    public int getDustInRoom(){
        DustSensors dustSensors = (DustSensors) getAppliance();
        return dustSensors.getDustInRoom();
    }

    /**
     * Method to set how much dust there is in room.
     * @param dustInRoom - integer between 1-20
     */
    public void setDustInRoom(int dustInRoom){
        DustSensors dustSensors = (DustSensors) getAppliance();
        dustSensors.setDustInRoom(dustInRoom);
    }

    /**
     * Overridden method to solve event.
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return event message
     */
    @Override
    public String solveEvent(int extra, String name) {
        getAppliance().setBroken(false);
        return  "fixed the dust sensors";
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
        String usageMessage = "Dust sensors were used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
