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
        name = "FIFO";
    }

    @Override
    public void run(Page[] referenceString, PhysicalMemory memory) {
        memory.clear();
        if(print){
            System.out.printf("(%s) Run\n", name);
            System.out.printf("(%s) Reference string: " + Arrays.toString(referenceString) + "\n", name);
        }

        for(int i = 0; i < referenceString.length; i++){
            Page currentPage = referenceString[i];

            if(print){
                System.out.println();
                System.out.printf("(%s) Iteration: " + i + "\n", name);
                System.out.printf("(%s) " + memory + "\n", name);
                //System.out.print("(%s) " + history + "\n", name);
                System.out.printf("(%s) Current page: " + currentPage + "\n", name);
            }

            int index = memory.indexOfPage(currentPage);

            if(index == -1){
                if(print){
                    System.out.printf("(%s) Page fault\n", name);
                }
                pageFaultCount++;

                index = memory.findEmptyFrame();
                //System.out.println("index: " + index);

                if(index == -1){
                    Frame replacementFrame = history.poll();
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
    }

}
