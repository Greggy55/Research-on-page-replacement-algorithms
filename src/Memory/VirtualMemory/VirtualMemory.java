package Memory.VirtualMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class VirtualMemory {
    private static final ArrayList<Character> used = new ArrayList<>();
    private static final char emptyChar = ' ';

    private final int totalNumberOfPages;
    private final Page[] pageArray;

    private Page[] referenceString;

    private final Random rand = new Random();

    public VirtualMemory(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
        pageArray = new Page[totalNumberOfPages];
        used.add(emptyChar);

        generatePages();
    }

    private void generatePages() {
        for(int i = 0; i < totalNumberOfPages; i++){
            char ch;
            do{
                ch = (char)(rand.nextInt(33,127));
                //ch = (char)(rand.nextInt(Character.MAX_VALUE));
                //ch = (char)(rand.nextInt(totalNumberOfPages) + '0');
            } while(used.contains(ch) || isInvalid(ch));

            pageArray[i] = new Page(ch);
            used.add(ch);
        }
        System.out.println("Pages: " + Arrays.toString(pageArray));
        System.out.println();
    }

    private static boolean isInvalid(char ch) {
        return !Character.isDefined(ch) || Character.isISOControl(ch) || Character.isSurrogate(ch) || ch == '⯔';
    }

    public void generateRandomReferenceString(int stringLength){
        referenceString = new Page[stringLength];

        for(int i = 0; i < referenceString.length; i++){
            referenceString[i] = pageArray[rand.nextInt(pageArray.length)];
        }

        //referenceString = new Page[]{new Page('1'), new Page('2'), new Page('3'), new Page('4'), new Page('1'), new Page('2'), new Page('5'), new Page('1'), new Page('2'), new Page('3'), new Page('4'), new Page('5'),};
    }

    public int size() {
        return totalNumberOfPages;
    }

    public Page[] getPageArray() {
        return pageArray;
    }

    public Page[] getReferenceString() {
        return referenceString;
    }
}
