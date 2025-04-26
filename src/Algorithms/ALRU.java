package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

public class ALRU extends Algorithm {
    public ALRU(boolean print, PhysicalMemory memory) {
        super(print, memory);
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
    }

    private Frame searchForFrameWithZeroReferenceBit() {
        while(true){
            for(Page key : lastReference.keySet()){
                if(lastReference.get(key) == 1){
                    lastReference.put(key, 0);
                }
                else if(lastReference.get(key) == 0){
                    Frame frame = memory.getFrame(key);
                    lastReference.remove(key);
                    return frame;
                }
                else{
                    throw new IllegalStateException("Reference bit is not 0 or 1");
                }
            }
        }
    }
}
