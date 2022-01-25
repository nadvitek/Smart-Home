package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.HouseAPI;
import cz.cvut.k36.omo.hw.smarthome.Times;
import java.util.Random;

/**
 * Class for mom.
 */
public class Mom extends Adult{

    /**
     * Constructor.
     * @param typeOfPerson - name of the person
     * @param house - the house
     * @param wakeUpTime - time when mom wakes up
     */
    public Mom(String typeOfPerson, HouseAPI house, int wakeUpTime) {
        super(typeOfPerson, house, wakeUpTime);
    }

    /**
     * Method for using time, mom goes to work at 7:00.
     * She returns at 17:00 on Monday and Wednesday 15:30 otherwise.
     * She also wakes up 2:00 later than normal on weekends.
     * @param time what time is it
     * @throws Exception
     */
    @Override
    public void useTime(int time) throws Exception {
        int daytime = (time % Times.WEEK) % Times.DAY;
        int day = (time % Times.WEEK) / Times.DAY;
        if (day < 5 && daytime == 42) {
            if (day == 0 || day == 2){
                this.leaveHouse(60);
            } else {
                this.leaveHouse(48);
            }
            if (day == 4) {
                this.setWeekendWakeUpTime(true);
            }
            return;
        }
        if (day == 6 && daytime == Times.NIGHT_TIME) {
            this.setWeekendWakeUpTime(false);
        }
        super.useTime(time);
    }
}
