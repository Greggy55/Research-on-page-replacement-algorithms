import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 4;
        int totalNumberOfPages = 5;
        int referenceStringLength = 12;

//        int numberOfFrames = 5;
//        int totalNumberOfPages = 10;
//        int referenceStringLength = 40;

        int localityLevel = 0;
        double localityFactor = 1.0;

        boolean printFIFO = false;
        boolean printRAND = false;
        boolean printOPT = false;
        boolean printLRU = false;
        boolean printALRU = true;

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
