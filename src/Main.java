import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames =5;
        int totalNumberOfPages = 10;
        int referenceStringLength = 40;

        int localityLevel = 0;
        double localityFactor = 1.0;

        boolean printFIFO = false;
        boolean printRAND = false;
        boolean printOPT = true;
        boolean printLRU = false;
        boolean printALRU = false;

        Simulation simulation = new Simulation(
                numberOfFrames,
                totalNumberOfPages,
                referenceStringLength,

                localityLevel,
                localityFactor,

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
