package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing API of vacuum.
 */
public class VacuumAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - vacuum of this API
     * @param house - in which it is
     */
    public VacuumAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = cleaning dirty rooms, creating reports about it.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Vacuum vacuum = (Vacuum) getAppliance();
        for (Room room : vacuum.getFloor().getRooms()) {
            if (room.isRoomDirty() && vacuum.getIsWorking() == 0){
                turnOn();
                vacuum.setIsWorking(2);
                vacuum.addToConsumption();
                this.getHouse().getReports().add(new EventReport("is cleaning", time + 2, "Vacuum", room, 0));
                room.cleanRoom();
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
        Vacuum vacuum = (Vacuum) getAppliance();
        vacuum.setBroken(false);
        return "fixed the vacuum";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float usage = (float) (getAppliance().getConsumption()*0.25);
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
        String usageMessage = "Vacuum was used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
