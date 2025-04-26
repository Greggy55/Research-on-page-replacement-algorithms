package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO extends Algorithm {

    private Queue<Frame> history = new LinkedList<>();

    public FIFO(boolean print, boolean printDetails, PhysicalMemory memory) {
        super(print, printDetails, memory);
        name = ANSI_GRAY + "FIFO" + ANSI_RESET;
    }

    @Override
    public void replacePage() {
        int index = memory.findEmptyFrame();

        if(index == -1){
            Frame replacementFrame = history.poll();
            printReplacementFrame(replacementFrame);

            assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
            replacementFrame.setPage(currentPage);
            history.add(replacementFrame);
        }
        else{
            memory.set(index, currentPage);
            history.add(memory.getFrame(index));

            printReplacementFrame(null);
        }

        if(print && printDetails) {
            System.out.printf("%s History:\t" + history + "\n", name);
        }
    }
}
