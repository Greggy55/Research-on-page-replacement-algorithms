import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 10;
        int totalNumberOfPages = 2*numberOfFrames;
        int referenceStringLength = 100;

        boolean printFIFO = true;
        boolean printRAND = false;
        boolean printOPT = false;
        boolean printLRU = false;
        boolean printALRU = false;


        Simulation simulation = new Simulation(
                numberOfFrames,
                totalNumberOfPages,
                referenceStringLength,

                printFIFO,
                printRAND,
                printOPT,
                printLRU,
                printALRU
        );

        simulation.start();

        simulation.printStatistics();
    }
}
