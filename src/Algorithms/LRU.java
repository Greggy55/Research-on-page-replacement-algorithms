package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;

public class LRU extends Algorithm {

    public LRU(boolean print, PhysicalMemory memory) {
        super(print, memory);
    }

    @Override
    public void replacePage() {
        int index = memory.findEmptyFrame();

        if(index == -1){
            Frame replacementFrame = searchForFrameWithLeastRecentlyUsedPage();
            printReplacementFrame(replacementFrame);

            assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
            replacementFrame.setPage(currentPage);
        }
        else{
            memory.set(index, currentPage);

            printReplacementFrame(null);
        }
    }

    public Frame searchForFrameWithLeastRecentlyUsedPage() {
        return new Frame();
    }
}
