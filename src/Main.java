import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 4;
        int totalNumberOfPages = 5;
        int referenceStringLength = 12;

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
