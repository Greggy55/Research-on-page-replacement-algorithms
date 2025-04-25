package Memory.VirtualMemory;

import java.util.ArrayList;
import java.util.Random;

public class VirtualMemory {
    private static final ArrayList<Character> used = new ArrayList<>();

    private final int totalNumberOfPages;
    private final Page[] pageArray;

    private final Random rand = new Random();

    public VirtualMemory(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
        pageArray = new Page[totalNumberOfPages];

        for(int i = 0; i < totalNumberOfPages; i++){
            char ch;
            do{
                ch = (char)(rand.nextInt(Character.MAX_VALUE));
            } while(used.contains(ch));

            pageArray[i] = new Page(ch);
            used.add(ch);
        }
    }

    public Page[] generateRandomReferenceString(int stringLength){

        Page[] referenceString = new Page[stringLength];

        for(int i = 0; i < referenceString.length; i++){
            referenceString[i] = pageArray[rand.nextInt(pageArray.length)];
        }

        return referenceString;
    }

//    public void addProcess(Process process){
//        char[] memoryReferences = process.getMemoryReferences();
//        for(int i = 0; i < memoryReferences.length; i++){
//            pageArray[i] = new Page(memoryReferences[i]);
//        }
//    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public Page[] getPageArray() {
        return pageArray;
    }


}
