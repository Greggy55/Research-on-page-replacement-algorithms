package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO extends Algorithm {

    private Queue<Frame> history = new LinkedList<>();

    public FIFO(boolean print) {
        super(print);
    }

    @Override
    public void run(Page[] referenceString, PhysicalMemory memory) {
        memory.clear();
        if(print){
            System.out.println("(FIFO) Run");
            System.out.println("(FIFO) Reference string: " + Arrays.toString(referenceString));
        }

        for(int i = 0; i < referenceString.length; i++){
            Page currentPage = referenceString[i];

            if(print){
                System.out.println();
                System.out.println("(FIFO) Iteration: " + i);
                System.out.println("(FIFO) " + memory);
                //System.out.println("(FIFO) " + history);
                System.out.println("(FIFO) Current page: " + currentPage);
            }

            int index = memory.indexOfPage(currentPage);

            if(index == -1){
                if(print){
                    System.out.println("(FIFO) Page fault");
                }
                pageFaultCount++;

                index = memory.findEmptyFrame();
                //System.out.println("index: " + index);

                if(index == -1){
                    Frame replacementFrame = history.poll();
                    assert replacementFrame != null: "(FIFO) Replacement frame is null";
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
                    System.out.println("(FIFO) Page OK");
                }
            }

        }
    }

}
