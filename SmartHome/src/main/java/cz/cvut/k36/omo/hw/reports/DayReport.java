package cz.cvut.k36.omo.hw.reports;

import cz.cvut.k36.omo.hw.smarthome.Times;

/**
 * Report that tells which day is now.
 */
public class DayReport extends Report{
    /**
     * Constructor.
     * @param time - which time is now from the start
     */
    public DayReport(int time) {
        String day = switch ((time % Times.WEEK) / Times.DAY) {
            case 0 -> "Monday";
            case 1 -> "Tuesday";
            case 2 -> "Wednesday";
            case 3 -> "Thursday";
            case 4 -> "Friday";
            case 5 -> "Saturday";
            case 6 -> "Sunday";
            default -> "No-day";
        };
        this.updateMessage("------- ");
        this.updateMessage("Day " + (time / Times.DAY + 1) + " - " + day);
        this.updateMessage(" -------\n");
    }
}
