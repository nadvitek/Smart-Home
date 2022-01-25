package cz.cvut.k36.omo.hw.smarthome;

import java.util.Scanner;

/**
 * Main class that starts the application.
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        Scanner sc;
        sc = new Scanner(System.in);
        new HouseAPI(sc.nextInt());
    }
}
