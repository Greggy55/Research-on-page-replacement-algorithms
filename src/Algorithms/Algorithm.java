package Algorithms;

import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

public abstract class Algorithm {
    protected int pageFaultCount = 0;
    protected int thrashingCount = 0;

    protected boolean print;

    public Algorithm(boolean print){
        this.print = print;
    }

    public abstract void run(Page[] referenceString, PhysicalMemory memory);
}
