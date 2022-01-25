package cz.cvut.k36.omo.hw.appliances;


import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Class to represent API for blinds.
 */
public class BlindsAPI extends ApplianceAPI {

    /**
     * Constructor.
     * @param appliance - appliance for this API
     * @param house - house in which appliance is
     */
    public BlindsAPI(Blinds appliance, House house) {
        super(appliance, house);
    }

    /**
     * Overridden method for using time = creating events, pulling blinds up and down according to time.
     * @param time - current time
     */
    @Override
    public void useTime(int time) {
        super.useTime(time);
        Blinds blinds = (Blinds) getAppliance();
        if ((time % Times.WEEK) / Times.DAY == 5 || (time % Times.WEEK) / Times.DAY == 6) {
            if(time % Times.DAY == 51){
                blinds.setOn(false);
                this.getHouse().getReports().add(new EventReport("were pulled up", time, "Blinds", this.getAppliance().getRoom(), 0));
                blinds.addToConsumption();
            } else if(time % Times.DAY == 132){
                blinds.setOn(true);
                this.getHouse().getReports().add(new EventReport("were pulled down", time, "Blinds", this.getAppliance().getRoom(), 0));
                blinds.addToConsumption();
            }
        } else {
            if(time % Times.DAY == 39){
                blinds.setOn(false);
                this.getHouse().getReports().add(new EventReport("were pulled up", time, "Blinds", this.getAppliance().getRoom(), 0));
                blinds.addToConsumption();
            } else if(time % Times.DAY == 132){
                blinds.setOn(true);
                this.getHouse().getReports().add(new EventReport("were pulled down", time, "Blinds", this.getAppliance().getRoom(), 0));
                blinds.addToConsumption();
            }
        }

    }

    /**
     * Overridden method for solving events.
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return message that blinds were fixed
     */
    @Override
    public String solveEvent(int extra, String name) {
        getAppliance().setBroken(false);
        return "fixed the blinds";
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
        String usageMessage = "Blinds were used: \n";
        for (String name : getAppliance().getUsage().keySet()){
            usageMessage += "by " + name + " " + getAppliance().getUsage().get(name) + "x\n";
        }
        return usageMessage;
    }
}
