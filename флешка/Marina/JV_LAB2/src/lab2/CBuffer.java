package lab2;

public abstract class CBuffer {
	protected static int bufCount = 0;
    protected int bufID;
    protected int bufSize;

    public CBuffer(int count) {
        bufID = ++bufCount;
        bufSize = count;
    }

    public abstract void generate();

    public int getBufCount() {
        return bufCount;
    }

    public int getBufID() {
        return bufID;
    }
    public int getBufSize() {
        return bufSize;
    }
}

