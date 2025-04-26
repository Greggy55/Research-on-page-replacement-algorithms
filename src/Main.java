import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 40;
        int totalNumberOfPages = numberOfFrames * 2;
        //int totalNumberOfPages = numberOfFrames / 2;
        int referenceStringLength = 100;

        int localityLevel = 10;
        double localityFactor = 0.8;

        boolean printFIFO = false;
        boolean printRAND = false;
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
