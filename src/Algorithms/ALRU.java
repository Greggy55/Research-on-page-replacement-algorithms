package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.LinkedList;
import java.util.Queue;

public class ALRU extends Algorithm {

    Queue<Page> queue = new LinkedList<>();

    public ALRU(boolean print, boolean printDetails, PhysicalMemory memory) {
        super(print, printDetails, memory);
        name = ANSI_GRAY + "aLRU" + ANSI_RESET;
    }

    @Override
    public void replacePage() {
        int index = memory.findEmptyFrame();

        if(index == -1){
            Frame replacementFrame = searchForFrameWithZeroReferenceBit();
            printReplacementFrame(replacementFrame);

            assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
            replacementFrame.setPage(currentPage);

            lastReference.put(currentPage, 1);
        }
        else{
            memory.set(index, currentPage);
            lastReference.put(currentPage, 1);

            printReplacementFrame(null);
        }

        queue.add(currentPage);

        if(print && printDetails) {
            System.out.printf("%s FIFO queue after:\t" + queue + "\n", name);
            System.out.printf("%s Reference bits:\t" + lastReference + "\n", name);
        }
    }

    private Frame searchForFrameWithZeroReferenceBit() {
        while(true){
            for(Page key : queue){
                if(lastReference.get(key) == 1){
                    lastReference.put(key, 0);
                }
                else if(lastReference.get(key) == 0){
                    Frame frame = memory.getFrame(key);
                    lastReference.remove(key);
                    queue.poll();
                    return frame;
                }
                else{
                    throw new IllegalStateException("Reference bit is not 0 or 1");
                }
            }
        }
    }
}
