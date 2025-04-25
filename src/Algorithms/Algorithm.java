package Algorithms;

import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.Arrays;

public abstract class Algorithm {
    protected int pageFaultCount = 0;
    protected int thrashingCount = 0;
    protected String name;
    protected boolean print;

    //protected Page[] referenceString;
    protected PhysicalMemory memory;

    protected Page currentPage;

    public Algorithm(boolean print, PhysicalMemory memory){
        this.print = print;
        this.memory = memory;
    }

    public abstract void replacePage();

    public void run(Page[] referenceString){
        memory.clear();
        if(print){
            System.out.printf("(%s) Run\n", name);
            System.out.printf("(%s) Reference string: " + Arrays.toString(referenceString) + "\n", name);
        }
    }

    public void printStatistics() {
        final int dashes = 15;
        System.out.println();
        System.out.printf("%s %s %s\n", "-".repeat(dashes), name, "-".repeat(dashes - name.length() + dashes/3));
        System.out.printf("Page fault count: %d\n", pageFaultCount);
        System.out.printf("Trashing count: %d\n", thrashingCount);
    }

    public boolean pageFault() {
        return memory.indexOfPage(currentPage) == -1;
    }

    public void endRun(){
        if(print){
            System.out.println();
            System.out.printf("(%s) End\n", name);
            System.out.printf("(%s) " + memory + "\n", name);

            System.out.println();
            System.out.println("-".repeat(100));
            System.out.println();
        }
    }
}
