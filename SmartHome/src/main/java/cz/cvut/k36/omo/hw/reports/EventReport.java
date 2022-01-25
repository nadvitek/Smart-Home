package cz.cvut.k36.omo.hw.reports;

import cz.cvut.k36.omo.hw.smarthome.Room;
import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Report for the Events that were solved.
 */
public class EventReport extends Report {
    /**
     * Constructor.
     * @param eventMessage - message of the event (what was done)
     * @param finishTime - time when the event was finished
     * @param name - name of the person/appliance that solved this events
     * @param room - room where the event was solved
     */
    public EventReport(String eventMessage, int finishTime, String name, Room room, int extra) {
        int hour = (finishTime % Times.DAY) / 6;
        int minute = (finishTime % 6) * 10;
        String myHour = Integer.toString(hour);
        if (hour < 10) {
            myHour = " " + myHour;
        }
        String myMinute = Integer.toString(minute);
        if (minute == 0) {
            myMinute = "00";
        }
        this.updateMessage(myHour + ":" + myMinute + "\n");
        String roomName = "";
        String floor = "";
        if (room != null) {
            roomName = " in " + room.getTypeOfRoom();
            floor = " in floor number " + Integer.toString(room.getNumberOfFloor());
        }
        if (extra == 666) {
            this.updateMessage(name + " " + eventMessage);
        } else {
            this.updateMessage(name + " " + eventMessage + roomName + floor + ".\n");
        }
    } 
}
