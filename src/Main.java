import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 20;
        int totalNumberOfPages = numberOfFrames * 2;
        int referenceStringLength = 100;

        int localityLevel = 0;
        double localityFactor = 1.0;

        boolean printFIFO = true;
        boolean printRAND = true;
        boolean printOPT = false;
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
