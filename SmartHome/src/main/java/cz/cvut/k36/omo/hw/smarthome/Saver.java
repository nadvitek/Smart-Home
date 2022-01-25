package cz.cvut.k36.omo.hw.smarthome;

import cz.cvut.k36.omo.hw.reports.Report;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for saving smart home simulation.
 */
public class Saver {

    /**
     * Constructor.
     * @param reports - list of reports from the simulation
     */
    public Saver(List<Report> reports) {
        try {
            FileWriter savedFile = new FileWriter("src/main/output/output.txt");
            for (Report report : reports) {
                savedFile.write(report.getMessage());
            }
            savedFile.close();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
