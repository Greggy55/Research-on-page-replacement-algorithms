package Memory.VirtualMemory;

public class Page {
    private char idChar;
    private int idInt;
    private final boolean isInt;

    public Page(char id) {
        this.idChar = id;
        this.isInt = false;
    }

    public Page(int id) {
        this.idInt = id;
        this.isInt = true;
    }

    @Override
    public String toString() {
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
}
