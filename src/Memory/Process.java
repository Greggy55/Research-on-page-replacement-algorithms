package Memory;

import java.util.Random;

public class Process {
    private final int numberOfMemoryReferences;
    private int[] memoryReferences;

    public Process(int num) {
        numberOfMemoryReferences = num;
        memoryReferences = new int[numberOfMemoryReferences];
    }

    public void generateRandomReferences(int min, int max) {
        Random rand = new Random();
        for(int i = 0; i < numberOfMemoryReferences; i++) {
            memoryReferences[i] = rand.nextInt(min, max);
        }
    }

    public int getNumberOfMemoryReferences() {
        return numberOfMemoryReferences;
    }

    public int[] getMemoryReferences() {
        return memoryReferences;
    }
}
