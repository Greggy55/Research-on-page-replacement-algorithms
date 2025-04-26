package Simulation;

import Algorithms.*;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.VirtualMemory;

public class Simulation {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[38;5;228m";

    private final int numberOfFrames;
    private final int totalNumberOfPages;
    private final int referenceStringLength;

    private final int localityLevel;
    private final double localityFactor;

    private VirtualMemory virtualMemory;
    private PhysicalMemory physicalMemory;

    private FIFO fifo;
    private RAND rand;
    private OPT opt;
    private LRU lru;
    private ALRU aLru;

    public Simulation(
            int numberOfFrames,
            int totalNumberOfPages,
            int referenceStringLength,

            int localityLevel,
            double localityFactor,

            boolean printFIFO,
            boolean printRAND,
            boolean printOPT,
            boolean printLRU,
            boolean printALRU,

            boolean printDetails
    ) {
        this.numberOfFrames = numberOfFrames;
        this.totalNumberOfPages = totalNumberOfPages;
        this.referenceStringLength = referenceStringLength;

        this.localityLevel = localityLevel;
        this.localityFactor = localityFactor;

        virtualMemory = new VirtualMemory(totalNumberOfPages);
        physicalMemory = new PhysicalMemory(numberOfFrames);

        fifo = new FIFO(printFIFO, printDetails, physicalMemory);
        rand = new RAND(printRAND, printDetails, physicalMemory);
        opt = new OPT(printOPT, printDetails, physicalMemory);
        lru = new LRU(printLRU, printDetails, physicalMemory);
        aLru = new ALRU(printALRU, printDetails, physicalMemory);
    }

    public void start(){
        //generateReferenceString();
        virtualMemory.generateExampleReferenceString();

        fifo.run(virtualMemory.getReferenceString());
        rand.run(virtualMemory.getReferenceString());
        opt.run(virtualMemory.getReferenceString());
        lru.run(virtualMemory.getReferenceString());
        aLru.run(virtualMemory.getReferenceString());
    }

    public void generateReferenceString(){
        if(localityLevel > 0){
            virtualMemory.generateReferenceStringWithLocality(referenceStringLength, localityLevel, localityFactor);
        }
        else{
            virtualMemory.generateRandomReferenceString(referenceStringLength);
        }
    }

    public void printParameters(){
        System.out.println("Pages: " + ANSI_YELLOW + virtualMemory.pagesToString() + ANSI_RESET);
        System.out.println("Reference string: " + ANSI_YELLOW + virtualMemory.referenceStringToString() + ANSI_RESET);
        System.out.println("Number of frames: " + ANSI_YELLOW + numberOfFrames + ANSI_RESET);
        System.out.println("Total number of pages: " + ANSI_YELLOW + totalNumberOfPages + ANSI_RESET);
        System.out.println("Reference string length: " + ANSI_YELLOW + referenceStringLength + ANSI_RESET);
    }

    public void printStatistics() {
        fifo.printStatistics();
        rand.printStatistics();
        opt.printStatistics();
        lru.printStatistics();
        aLru.printStatistics();
    }
}
