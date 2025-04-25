package Memory.PhysicalMemory;

public class PhysicalMemory {
    // size
    private final int numberOfFrames;

    private final Frame[] frameArray;

    public PhysicalMemory(int numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
        frameArray = new Frame[numberOfFrames];
    }

    public int size() {
        return numberOfFrames;
    }

    public Frame[] getFrameArray() {
        return frameArray;
    }
}
