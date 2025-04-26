package Memory.VirtualMemory;

public class Page {
    public static final String ANSI_RESET = "\u001B[0m";
    private static final int BEGIN_COLOR_CODE = 17;
    private static int globalColorCode = BEGIN_COLOR_CODE;
    private int colorCode;

    private char idChar;
    private int idInt;
    private final boolean isInt;

    public Page(char id) {
        this.idChar = id;
        this.isInt = false;
        updateColorCode();
    }

    public Page(int id) {
        this.idInt = id;
        this.isInt = true;
        updateColorCode();
    }

    @Override
    public String toString() {
        if(isInt){
            return ansi256(colorCode) + idInt + ANSI_RESET;
        }
        return ansi256(colorCode) + idChar + ANSI_RESET;
    }

    public String idToString() {
        if(isInt){
            return String.valueOf(idInt);
        }
        return String.valueOf(idChar);
    }

    public boolean sameIdAs(Page p){
        assert this.isInt == p.isInt;
        if(this.isInt){
            return idInt == p.idInt;
        }
        return this.idChar == p.idChar;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public void updateColorCode() {
        colorCode = BEGIN_COLOR_CODE + (globalColorCode / 6);
        if((colorCode-BEGIN_COLOR_CODE+1)%6==0){
            colorCode++;
        }
        globalColorCode++;
    }

    public static String ansi256(int code) {
        return "\u001B[38;5;" + code + "m";
    }
}
