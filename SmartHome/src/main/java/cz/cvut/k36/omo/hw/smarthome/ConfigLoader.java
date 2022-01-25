package cz.cvut.k36.omo.hw.smarthome;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for loading configurations.
 */
public class ConfigLoader {
    private final House house;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor.
     * @param house - the house
     * @param numberOfConfiguration - the number of configuration
     * @throws Exception
     */
    public ConfigLoader(House house, int numberOfConfiguration) throws Exception {
        this.house = house;
        loadFile(numberOfConfiguration);
        
    }
    
    private void loadFile(int configuration) throws Exception{
        File file = new File("src/main/configs/configuration" + configuration + ".txt");
        try {
            Scanner sc = new Scanner(file);
            int numberOfFloors = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < numberOfFloors; i++) {
                String[] line = sc.nextLine().split(" ");
                int[] rooms = new int[4];
                for (int j = 0; j < 4; j++) {
                    rooms[j] = Integer.parseInt(line[j]);
                }
                this.house.setFloor(i, rooms);
            }
            this.house.setAreaOfGarden(Integer.parseInt(sc.nextLine()));
            this.house.addHumans(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
            this.house.addAnimals(Integer.parseInt(sc.nextLine()),
                                  Integer.parseInt(sc.nextLine()),
                                  Integer.parseInt(sc.nextLine()));
            this.house.addAppliances();
        } catch (FileNotFoundException ex){
            Logger.getLogger(ConfigLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method that returns the filled house.
     * @return the filled house
     */
    public House getHouse() {
        return this.house;
    }
    
    
}
