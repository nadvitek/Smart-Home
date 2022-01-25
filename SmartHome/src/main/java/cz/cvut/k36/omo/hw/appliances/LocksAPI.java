package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.livings.Person;
import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class for representing API of locks.
 */
public class LocksAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - locks of this API
     * @param house - in which it is
     */
    public LocksAPI(Appliance appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = locking house for the night and when nobody's inside, adding to consumption.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Locks locks = (Locks) getAppliance();
        if (locks.isOn()){
            locks.addToConsumption();
        }
        if (time % Times.DAY == 36){
            locks.setOn(false);
            this.getHouse().getReports().add(new EventReport("was unlocked\n", time, "The house", this.getAppliance().getRoom(), 666));
        }
        if (time % Times.DAY == Times.NIGHT_TIME) {
            locks.setOn(true);
            this.getHouse().getReports().add(new EventReport("was locked\n", time, "The house", this.getAppliance().getRoom(), 666));
        }
        boolean someoneInHouse = false;
        for (Person person : locks.getPeople()) {
            if (!person.isGone()){
                someoneInHouse = true;
            }
        }
        if (!someoneInHouse && !locks.isOn()){
            locks.setOn(true);
            this.getHouse().getReports().add(new EventReport("was locked\n", time, "The house", this.getAppliance().getRoom(), 666));
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
        return "fixed the locks";
    }

    /**
     * Overridden method for calculating consumption of energy, according to usage.
     * @return message describing consumption
     */
    @Override
    public String calculateConsumption() {
        float usage = (float) ((getAppliance().getConsumption()*0.025)/60);
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
        String usageMessage = "Locks were used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
