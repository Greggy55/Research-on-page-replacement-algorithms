import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 10;
        int totalNumberOfPages = numberOfFrames * 2;
        //int totalNumberOfPages = numberOfFrames / 2;
        int referenceStringLength = 100;
        boolean locality = true;

        boolean printFIFO = true;
        boolean printRAND = true;
        boolean printOPT = false;
        boolean printLRU = false;
        boolean printALRU = false;


        Simulation simulation = new Simulation(
                numberOfFrames,
                totalNumberOfPages,
                referenceStringLength,
                locality,

                printFIFO,
                printRAND,
                printOPT,
                printLRU,
                printALRU
        );

        simulation.start();
        simulation.printParameters();
        simulation.printStatistics();
    }
}
