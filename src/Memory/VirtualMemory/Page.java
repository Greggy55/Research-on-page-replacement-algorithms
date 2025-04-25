package Memory.VirtualMemory;

public class Page {
    public final char id;

    public Page(char id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}
