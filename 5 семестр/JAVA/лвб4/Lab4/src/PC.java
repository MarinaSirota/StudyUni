
public class PC implements Comparable<PC> {

    protected int id;
    protected String makerCPU;
    protected int frequency;
    protected int RAM;

    public PC(int id_, String makerCPU_, int frequency_, int RAM_) {
        id=id_;
        makerCPU=makerCPU_;
        frequency=frequency_;
        RAM=RAM_;
    }

    public int getID() {
        return id;
    }

    public String getMakerCPU() {
        return makerCPU;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRAM() {
        return RAM;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setMakerCPU(String makerCPU) {
        this.makerCPU = makerCPU;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }
    
	@Override
    public int compareTo(PC pc) {
    	return this.makerCPU.compareTo(pc.makerCPU);
    	}
   
    }

   
