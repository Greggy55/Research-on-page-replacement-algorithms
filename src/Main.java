import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 4;
        int totalNumberOfPages = 5;
        int referenceStringLength = 12;

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
