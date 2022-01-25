package cz.cvut.k36.omo.hw.events;

import cz.cvut.k36.omo.hw.appliances.ApplianceAPI;
import cz.cvut.k36.omo.hw.smarthome.Room;
/**
 * Class of an event.
 */
public class Event {
    private final int extra;
    private final ApplianceAPI api;
    private final int timeToFinish;

    /**
     * Constructor which creates the Event.
     * @param extra - tells which operation should the person do on the appliance
     * @param api - gives API of the Appliance that need to do something with
     * @param timeToFinish - number of time for person to solve the event
     */
    public Event(int extra, ApplianceAPI api, int timeToFinish) {
        this.extra = extra;
        this.api = api;
        this.timeToFinish = timeToFinish;
    }

    /**
     * This message is called when the Event is solved.
     * @param name - name of the person that solved this event
     * @return - returns the message for creating the EventReport
     */
    public String eventSolved(String name) {
        String message = api.solveEvent(this.extra, name);
        return message;
    }

    /**
     *  Method that returns time to finish the event.
     * @return time to finish the event
     */
    public int getTimeToFinish() {
        return this.timeToFinish;
    }

    /**
     * Method that returns the room in which the event should be solved.
     * @return the room in which the event should be solved
     */
    public Room getRoom() {
        return this.api.getAppliance().getRoom();
    }

    /**
     * Method that returns the API of the appliance that need to solve the Event on.
     * @return the API of the appliance that need to solve the Event on
     */
    public ApplianceAPI getApi() {
        return api;
    }

    /**
     * Method that returns an extra information that tells which operation was solved on the appliance.
     * @return an extra information that tells which operation was solved on the appliance
     */
    public int getExtra() {
        return extra;
    }
}
