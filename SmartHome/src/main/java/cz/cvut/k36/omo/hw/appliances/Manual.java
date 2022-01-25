package cz.cvut.k36.omo.hw.appliances;

/**
 * Class for a manual for all appliances.
 */
public class Manual {
    /**
     * Enum for choosing difficulty of repair.
     */
    public enum Difficulty {
        EASY_FIX,
        NOT_SO_EASY_BUT_CAN_BE_DONE,
        CALL_A_HANDYMAN
    }
    private Difficulty difficulty;
    private Appliance appliance;
    private int purchaseYear;

    /**
     * Constructor.
     * @param difficulty - difficulty of repair
     * @param appliance - the appliance manual belongs to
     * @param purchaseDate - year the appliance was purchased
     */
    public Manual(Difficulty difficulty, Appliance appliance, int purchaseDate) {
        this.difficulty = difficulty;
        this.appliance = appliance;
        this.purchaseYear = purchaseDate;
    }

    /**
     *  Method for returning appliance's difficulty of repair.
     * @return the difficulty of appliance's repair
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }
}
