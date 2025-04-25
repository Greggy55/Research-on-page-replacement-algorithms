package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.Random;

public class RAND extends Algorithm {
    private final Random rnd = new Random();

    public RAND(boolean print, PhysicalMemory memory) {
        super(print, memory);
        name = "RAND";
    }

    @Override
    public void run(Page[] referenceString) {
        super.run(referenceString);

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
                    System.out.printf("(%s) Page fault\n", name);
                }
                pageFaultCount++;

                int index = memory.findEmptyFrame();

                if(index == -1){
                    Frame replacementFrame = memory.getFrame(rnd.nextInt(memory.size()));
                    assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
                    replacementFrame.setPage(currentPage);
                }
                else{
                    memory.set(index, currentPage);
                }
            }
            else{
                if(print){
                    System.out.printf("(%s) Page OK\n", name);
                }
            }

        }

        endRun();
    }
}
