package Simulation;

import Memory.VirtualMemory.VirtualMemory;

import java.util.Arrays;

public class Simulation {
    private final int numberOfFrames;
    private final int totalNumberOfPages;
    private final int referenceStringLength;

    private VirtualMemory virtualMemory;

    public Simulation(
            int numberOfFrames,
            int totalNumberOfPages,
            int referenceStringLength
    ) {
        this.numberOfFrames = numberOfFrames;
        this.totalNumberOfPages = totalNumberOfPages;
        this.referenceStringLength = referenceStringLength;

        virtualMemory = new VirtualMemory(totalNumberOfPages);
    }

    public void start(){
        System.out.println(Arrays.toString(virtualMemory.generateRandomReferenceString(referenceStringLength)));
    }
}
