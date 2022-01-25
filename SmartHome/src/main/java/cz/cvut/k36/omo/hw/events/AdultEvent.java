package cz.cvut.k36.omo.hw.events;

import cz.cvut.k36.omo.hw.appliances.ApplianceAPI;
/**
 * Class that says that this event is only for adults.
 */
public class AdultEvent extends Event{
    
    /**
     * Constructor which creates the Event.
     * @param extra - tells which operation should the person do on the appliance
     * @param api - gives API of the Appliance that need to do something with
     * @param timeToFinish - number of time for person to solve the event
     */
    public AdultEvent(int extra, ApplianceAPI api, int timeToFinish) {
        super(extra, api, timeToFinish);
    }
}
