package Memory.VirtualMemory;

import Memory.Process;

public class VirtualMemory {
    // size
    private final int totalNumberOfPages;

    private final Page[] pageArray;

    private final int numberOfProcesses;
    private final Process[] processArray;

    public VirtualMemory(int totalNumberOfPages, int numberOfProcesses) {
        this.totalNumberOfPages = totalNumberOfPages;
        this.numberOfProcesses = numberOfProcesses;
        processArray = new Process[numberOfProcesses];

        assert numberOfProcesses == 1;
        generateSingleProcess(totalNumberOfPages);

        pageArray = new Page[totalNumberOfPages];
        int[] memoryReferences = processArray[0].getMemoryReferences();
        for(int i = 0; i < memoryReferences.length; i++) {
            pageArray[i] = new Page(memoryReferences[i]);
        }
    }

    public VirtualMemory(int totalNumberOfPages) {
        this(totalNumberOfPages,1);
    }

    public void generateSingleProcess(int totalNumberOfPages) {
        processArray[0] = new Process(totalNumberOfPages);
        processArray[0].generateRandomReferences(0,totalNumberOfPages);
    }

    public void generateProcesses() {
        throw new UnsupportedOperationException();
//        if(numberOfProcesses == 1) {
//            generateSingleProcess();
//        }
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public Process[] getProcessArray() {
        return processArray;
    }
}
