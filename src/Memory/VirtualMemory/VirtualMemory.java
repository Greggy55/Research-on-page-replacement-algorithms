package Memory.VirtualMemory;

import java.util.ArrayList;
import java.util.Random;

public class VirtualMemory {
    private static final ArrayList<Character> used = new ArrayList<>();

    private final int totalNumberOfPages;
    private final Page[] pageArray;

    private Page[] referenceString;

    private final Random rand = new Random();

    public VirtualMemory(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
        pageArray = new Page[totalNumberOfPages];

        generatePages();
    }

    private void generatePages() {
        for(int i = 0; i < totalNumberOfPages; i++){
            char ch;
            do{
                ch = (char)(rand.nextInt(Character.MAX_VALUE));
            } while(used.contains(ch));

            pageArray[i] = new Page(ch);
            used.add(ch);
        }
    }

    public void generateRandomReferenceString(int stringLength){
        referenceString = new Page[stringLength];

        for(int i = 0; i < referenceString.length; i++){
            referenceString[i] = pageArray[rand.nextInt(pageArray.length)];
        }
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public Page[] getPageArray() {
        return pageArray;
    }

    public Page[] getReferenceString() {
        return referenceString;
    }
}
