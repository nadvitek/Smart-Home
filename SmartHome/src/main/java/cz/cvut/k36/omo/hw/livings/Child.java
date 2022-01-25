package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.HouseAPI;
import cz.cvut.k36.omo.hw.smarthome.Times;
import java.util.Random;
/**
 * Class for all children in the house.
 */
public class Child extends Person {
    /**
     * Constructor.
     * @param typeOfPerson - name of the person
     * @param house - the house
     * @param wakeUpTime - time when the child wakes up
     */
    public Child(String typeOfPerson, HouseAPI house, int wakeUpTime) {
        super(typeOfPerson, house, wakeUpTime);
    }

    /**
     * Method for using time, children go to school at 7:00 and return after some time, they also wake up 2:00 later than normal on weekends.
     *
     * @param time what time is it
     * @throws Exception
     */
    @Override
    public void useTime(int time) throws Exception {
        int daytime = (time % Times.WEEK) % Times.DAY;
        int day = (time % Times.WEEK) / Times.DAY;
        if (day < 5 && daytime == 42) {
            Random rand = new Random();
            this.leaveHouse(rand.nextInt(24) + 30);
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
