package Algorithms;

import Memory.PhysicalMemory.Frame;
import Memory.PhysicalMemory.PhysicalMemory;
import Memory.VirtualMemory.Page;

import java.util.ArrayList;

public class OPT extends Algorithm {

    private ArrayList<Page> future = new ArrayList<>();

    public OPT(boolean print, boolean printDetails, PhysicalMemory memory) {
        super(print, printDetails, memory);
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

        if(print && printDetails){
            System.out.printf("%s Future:\t" + future + "\n", name);
        }
    }

    public Frame searchForLatestReference(){
        future.clear();

        for(Frame frame : memory.getFrameArray()){
            if(!referenceIsInRemainingRefString(frame.getPage())){
                return frame;
            }
        }

        for(int i = iter + 1; i < referenceString.length; i++){
            Page futureReference = referenceString[i];

            if(futureReferenceIsInPhysicalMemory(futureReference)){
                if (futureDoesNotContain(futureReference)) {
                    future.add(futureReference);
                }
            }

            if(allFutureReferencesHaveBeenChecked()){
                break;
            }
        }

        assert memory.getFrame(future.getLast()) != null: "(%s) Frame is null\n".formatted(name);
        return memory.getFrame(future.getLast());
    }

    private boolean allFutureReferencesHaveBeenChecked() {
        return memory.size() == future.size();
    }

    private boolean futureDoesNotContain(Page futureReference) {
        return future.stream().noneMatch(p -> p.sameIdAs(futureReference));
    }

    private boolean futureReferenceIsInPhysicalMemory(Page futureReference) {
        return memory.containsPage(futureReference);
    }

    private boolean futureReferenceIsDifferentFromCurrentPage(Page futureReference) {
        return !currentPage.sameIdAs(futureReference);
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
