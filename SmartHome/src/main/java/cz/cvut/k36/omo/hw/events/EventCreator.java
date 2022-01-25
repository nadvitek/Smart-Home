package cz.cvut.k36.omo.hw.events;

import cz.cvut.k36.omo.hw.appliances.ApplianceAPI;

/**
 * Creator of Events (Event Factory).
 */
public class EventCreator {
    private static EventCreator INSTANCE;

    /**
     * This is Singleton constructor.
     */
    private EventCreator(){
    }

    /**
     * This method returns the only one Instance, because it's Singleton.
     * @return the only Instance of this class
     */
    public synchronized static EventCreator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventCreator();
        }
        return INSTANCE;
    }

    /**
     * Method that creates an Event.
     * @param timeToFinish - number of time for person to solve the event
     * @param forWho - tells if the event is for children, adults or both
     * @param api  - gives API of the Appliance that need to do something with
     * @param extra - tells which operation should the person do on the appliance
     * @return the created Event
     */
    public Event createEvent(int timeToFinish, int forWho, ApplianceAPI api, int extra) {
        if (forWho % 6 == 0)
        {
            return new Event(extra, api, timeToFinish);
        } else if (forWho % 2 == 0) {
            return new ChildEvent(extra, api, timeToFinish);
        } else {
            return new AdultEvent(extra, api, timeToFinish);
        }
    }
    
    
}
