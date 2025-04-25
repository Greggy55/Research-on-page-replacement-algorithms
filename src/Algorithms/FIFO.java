package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO extends Algorithm {

    private Queue<Frame> history = new LinkedList<>();

    public FIFO(boolean print, PhysicalMemory memory) {
        super(print, memory);
        name = "FIFO";
    }

    @Override
    public void replacePage() {

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
                //System.out.print("(%s) " + history + "\n", name);
                System.out.printf("(%s) Current page: " + currentPage + "\n", name);
            }

            if(pageFault()){
                if(print){
                    System.out.printf("(%s) Page fault\n", name);
                }
                pageFaultCount++;

                int index = memory.findEmptyFrame();

                if(index == -1){
                    Frame replacementFrame = history.poll();
                    if(print){
                        System.out.printf("(%s) Replacement frame: " + replacementFrame + "\n", name);
                    }

                    assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
                    replacementFrame.setPage(currentPage);
                    history.add(replacementFrame);
                }
                else{
                    memory.set(index, currentPage);
                    history.add(memory.getFrame(index));
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
