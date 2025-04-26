import Memory.PhysicalMemory.Frame;
import Memory.VirtualMemory.Page;
import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        Frame.COLOR = true;
        Page.COLOR = true;

        int numberOfFrames = 20;
        int totalNumberOfPages = 40;
        int referenceStringLength = 100;
        // 4 5 12

        int localityLevel = 15;
        double localityFactor = 1.0;

        boolean printFIFO = true;
        boolean printRAND = true;
        boolean printOPT = true;
        boolean printLRU = true;
        boolean printALRU = true;

        boolean printDetails = false;

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
                printALRU,

                printDetails
        );

        simulation.start();
        simulation.printParameters();
        simulation.printStatistics();
    }
}
