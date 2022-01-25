package cz.cvut.k36.omo.hw.events;

import java.util.ArrayList;
import java.util.List;

/**
 *  Iterator for events.
 */
public class EventIterator implements Iterator{
    private List<Event> eventIterator = new ArrayList();
    private int index = 0;

    /**
     * Creates an iterator from events that are for the person, that wants to solve them.
     * @param events - all events that need to be solved in the house
     * @param eventCategory - tells if child or an adult wants the iterator
     */
    public EventIterator(List<Event> events, int eventCategory) {
        int numOfAddedEvents = 0;
        for (Event event : events) {
            if (eventCategory == 1 && event.getClass().getSimpleName().equals("ChildEvent")) {
                eventIterator.add(event);
                ++numOfAddedEvents;
            } else if (eventCategory == 2 && event.getClass().getSimpleName().equals("AdultEvent")) {
                eventIterator.add(event);
                ++numOfAddedEvents;
            } else if (event.getClass().getSimpleName().equals("Event")){
                eventIterator.add(event);
                ++numOfAddedEvents;
            }
            if (numOfAddedEvents == 5) {
                break;
            }
        }
        for (Event event : eventIterator) {
            events.remove(event);
        }
    }

    /**
     *  This method gives next event in the iterator.
     * @return next event in the iterator
     */
    public Event next() {
        return eventIterator.get(index++);
    }

    /**
     * This method tells if there is some event left in the iterator.
     * @return
     */
    public boolean hasNext(){
        return index < eventIterator.size();
    }
}
