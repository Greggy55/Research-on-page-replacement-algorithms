package Simulation;

import Algorithms.FIFO;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.VirtualMemory;

public class Simulation {
    private final int numberOfFrames;
    private final int totalNumberOfPages;
    private final int referenceStringLength;

    private VirtualMemory virtualMemory;
    private PhysicalMemory physicalMemory;

    private FIFO fifo;

    public Simulation(
            int numberOfFrames,
            int totalNumberOfPages,
            int referenceStringLength,

            boolean printFIFO,
            boolean printRAND,
            boolean printOPT,
            boolean printLRU,
            boolean printALRU
    ) {
        this.numberOfFrames = numberOfFrames;
        this.totalNumberOfPages = totalNumberOfPages;
        this.referenceStringLength = referenceStringLength;

        virtualMemory = new VirtualMemory(totalNumberOfPages);
        physicalMemory = new PhysicalMemory(numberOfFrames);

        fifo = new FIFO(printFIFO);
    }

    public void start(){
        virtualMemory.generateRandomReferenceString(referenceStringLength);
        fifo.run(virtualMemory.getReferenceString(), physicalMemory);
    }

    public void printStatistics() {
        fifo.printStatistics();
    }
}
