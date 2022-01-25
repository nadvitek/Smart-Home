package cz.cvut.k36.omo.hw.reports;

/**
 * Report class.
 */
public abstract class Report {
    private String message = "";

    /**
     * Method that returns the message that the report contains.
     * @return the message that the report contains
     */
    public String getMessage() {
        this.message += "\n";
        return message;
    }

    /**
     * Method that updates the message.
     * @param update - update to the message
     */
    public void updateMessage(String update) {
        message += update;
    }
}
