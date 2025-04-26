package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.ArrayList;

public class OPT extends Algorithm {

    private ArrayList<Page> future = new ArrayList<>();

    public OPT(boolean print, PhysicalMemory memory) {
        super(print, memory);
        name = ANSI_GRAY + "OPT" + ANSI_RESET;
    }

    @Override
    public void replacePage(Page[] referenceString, int iteration) {
        int index = memory.findEmptyFrame();

        if(index == -1){
            Frame replacementFrame = searchForLatestReference(referenceString, iteration);
            printReplacementFrame(replacementFrame);

            assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
            replacementFrame.setPage(currentPage);
        }
        else{
            memory.set(index, currentPage);

            printReplacementFrame(null);
        }
    }

    public Frame searchForLatestReference(Page[] referenceString, int start){
        Page faultPage = referenceString[start];
        start++;
        for(int i = start; i < referenceString.length; i++){
            if(!faultPage.sameIdAs(referenceString[i])){
                if(memory.containsPage(referenceString[i])){
                    int j;
                    for(j = 0; j < future.size(); j++){
                        if(!future.get(j).sameIdAs(referenceString[i])){
                            break;
                        }
                    }
                    if(j == future.size()){
                        future.add(referenceString[i]);
                    }
                }
            }
            if(memory.size() == future.size()){
                break;
            }
        }
        assert memory.getFrame(future.getLast()) != null: "(%s) Frame is null\n".formatted(name);
        return memory.getFrame(future.getLast());
    }
}
