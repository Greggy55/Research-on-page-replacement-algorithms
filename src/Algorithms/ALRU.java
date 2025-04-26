package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;

public class ALRU extends LRU {
    public ALRU(boolean print, PhysicalMemory memory) {
        super(print, memory);
        name = ANSI_GRAY + "aLRU" + ANSI_RESET;
    }

    @Override
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
}
