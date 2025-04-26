package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;

public class LRU extends Algorithm {

    public LRU(boolean print, PhysicalMemory memory) {
        super(print, memory);
        name = ANSI_GRAY + "LRU" + ANSI_RESET;
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
        Frame[] frames = memory.getFrameArray();
        Frame resultFrame = frames[0];
        int resultFrameLastReference = lastReference.get(resultFrame.getPage());

        for(Frame frame : frames){
            int currentFrameLastReference = lastReference.get(frame.getPage());

            if(currentFrameLastReference > resultFrameLastReference){
                resultFrame = frame;
                resultFrameLastReference = currentFrameLastReference;
            }
        }

        return resultFrame;
    }

    public void updateLastReference(){
        lastReference.replaceAll((k, v) -> v + 1);
        lastReference.put(currentPage, 0);
    }
}
