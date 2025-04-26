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
    public void replacePage() {
        int index = memory.findEmptyFrame();

        if(index == -1){
            Frame replacementFrame = searchForLatestReference();
            printReplacementFrame(replacementFrame);

            assert replacementFrame != null: "(%s) Replacement frame is null\n".formatted(name);
            replacementFrame.setPage(currentPage);
        }
        else{
            memory.set(index, currentPage);

            printReplacementFrame(null);
        }
    }

    public Frame searchForLatestReference(){
        future.clear();

        for(Frame frame : memory.getFrameArray()){
            if(!referenceIsInRemainingRefString(frame.getPage())){
                return frame;
            }
        }

        System.out.println("Fault page: " + currentPage.idToString());
        System.out.println("Start: " + (iter+1));
        for(int i = iter + 1; i < referenceString.length; i++){
            System.out.println();
            if(!currentPage.sameIdAs(referenceString[i])){
                System.out.println("Current reference: " + referenceString[i]);
                if(memory.containsPage(referenceString[i])){
                    System.out.println("Not empty");
                    int j;
                    for(j = 0; j < future.size(); j++){
                        System.out.println("\tFuture["+j+"] = "+ future.get(j));
                        if(future.get(j).sameIdAs(referenceString[i])){
                            System.out.println("BREAK: " + future.get(j) + " == " + referenceString[i]);
                            break;
                        }
                    }
                    System.out.println(j + " =?= " + future.size());
                    if(j == future.size()){
                        future.add(referenceString[i]);
                    }
                    System.out.println("Future: " + future);
                }
            }
            System.out.println(memory.size() + " =?= " + future.size());
            if(memory.size() == future.size()){
                break;
            }
        }
        if(memory.size() != future.size()){
            System.out.println("Add remaining pages");
//            for (Frame frame : memory.getFrameArray()) {
//                if(!referenceIsInTheFuture(frame.getPage())){
//                    return frame;
//                }
//            }
        }
        System.out.println("Future: " + future);
        System.out.println("null != " + memory.getFrame(future.getLast()));
        assert memory.getFrame(future.getLast()) != null: "(%s) Frame is null\n".formatted(name);
        return memory.getFrame(future.getLast());
    }

    public boolean referenceIsInTheFuture(){
        for(Frame frame : memory.getFrameArray()){
            if(referenceIsInRemainingRefString(frame.getPage())){
                return true;
            }
        }
        return false;
    }

    public boolean referenceIsInRemainingRefString(Page page){
        for(int i = iter; i < referenceString.length; i++){
            if(referenceString[i].sameIdAs(page)){
                return true;
            }
        }
        return false;
    }

    public boolean referenceIsInTheFuture(Page page){
        for(Page futurePage : future){
            if(page.sameIdAs(futurePage)){
                return true;
            }
        }
        return false;
    }
}
