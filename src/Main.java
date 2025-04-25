import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 20;
        int totalNumberOfPages = 50;
        int referenceStringLength = 120;

        boolean printFIFO = true;
        boolean printRAND = true;
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
