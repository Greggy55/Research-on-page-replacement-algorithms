package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.Arrays;

public abstract class Algorithm {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

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

        for(int i = 0; i < referenceString.length; i++){
            currentPage = referenceString[i];

            if(print){
                System.out.println();
                System.out.printf("(%s) Iteration: " + i + "\n", name);
                System.out.printf("(%s) " + memory + "\n", name);
                System.out.printf("(%s) Current page: " + currentPage + "\n", name);
            }

            if(pageFault()){
                if(print){
                    System.out.printf("(%s)"+ANSI_RED+" Page fault\n"+ANSI_RESET, name);
                }
                pageFaultCount++;

                replacePage();
            }
            else{
                if(print){
                    System.out.printf("(%s) Page OK\n", name);
                }
            }

        }

        if(print){
            System.out.println();
            System.out.printf("(%s) End\n", name);
            System.out.printf("(%s) " + memory + "\n", name);

            System.out.println();
            System.out.println("-".repeat(100));
            System.out.println();
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

    public void printReplacementFrame(Frame replacementFrame) {
        if (print) {
            String msg = (replacementFrame != null) ? replacementFrame.toString() : "Empty frame";
            System.out.printf("(%s) Replacement frame: %s\n", name, msg);
        }
    }
}
