package Algorithms;

import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

public abstract class Algorithm {
    protected int pageFaultCount = 0;
    protected int thrashingCount = 0;
    protected String name;

    protected boolean print;

    public Algorithm(boolean print){
        this.print = print;
    }

    public abstract void run(Page[] referenceString, PhysicalMemory memory);

    public void printStatistics() {
        final int dashes = 15;
        System.out.println();
        System.out.printf("%s %s %s\n", "-".repeat(dashes), name, "-".repeat(dashes - name.length() + dashes/3));
        System.out.printf("Page fault count: %d\n", pageFaultCount);
        System.out.printf("Trashing count: %d\n", thrashingCount);
    }
}
