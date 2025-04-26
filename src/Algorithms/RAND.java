package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.Random;

public class RAND extends Algorithm {
    private final Random rnd = new Random();

    public RAND(boolean print, PhysicalMemory memory) {
        super(print, memory);
        name = ANSI_GRAY + "RAND" + ANSI_RESET;
    }

    @Override
    public void replacePage() {
        int index = memory.findEmptyFrame();

        if(index == -1){
            Frame replacementFrame = memory.getFrame(rnd.nextInt(memory.size()));
            printReplacementFrame(replacementFrame);

            assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
            replacementFrame.setPage(currentPage);
        }
        else{
            memory.set(index, currentPage);

            printReplacementFrame(null);
        }
    }
}
