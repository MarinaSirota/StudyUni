package ud;

public class recordAllDate {
	protected int idCustomer;
    protected String secondNameCustomer;
    protected String nameCustomer;
    protected String fatherNameCustomer;
    protected String phoneCustomer;
    protected int visitCustomer;
    protected int idWorker;
	protected String secondNameWorker;
	protected String nameWorker;
	protected String fatherNameWorker;
	protected int phoneWorker;
	protected float prossentWorker;
	protected String dateWorker;
	protected int idService;
    protected String nameService;
    protected int costService;
    protected int timeService;
    

    public recordAllDate (int idCustomer_, String secondNameCustomer_, String nameCustomer_,String fatherNameCustomer_, String phoneCustomer_, int visitCustomer_,
    		int idWorker_, String secondNameWorker_, String nameWorker_, String fatherNameWorker_, int phoneWorker_, float prossentWorker_, 
			String dateWorker_, int idService_, String nameService_, int costService_, int timeService_) {
        idCustomer=idCustomer_;
        secondNameCustomer=secondNameCustomer_;
        nameCustomer=nameCustomer_;
        fatherNameCustomer=fatherNameCustomer_;
        phoneCustomer=phoneCustomer_;
        visitCustomer=visitCustomer_;
        idWorker = idWorker_;
		secondNameWorker = secondNameWorker_;
		nameWorker = nameWorker_;
		fatherNameWorker = fatherNameWorker_;
		phoneWorker = phoneWorker_;
		prossentWorker = prossentWorker_;
		dateWorker=dateWorker_;
		idService = idService_;
	    nameService=nameService_;
	    costService=costService_;
	    timeService=timeService_;

    }

    public int getIDCustomer() {
        return idCustomer;
    }
    public String getSecondNameCustomer() {
        return secondNameCustomer;
    }
    public String getNameCustomer() {
        return nameCustomer;
    }
    public String getFatherNameCustomer() {
        return fatherNameCustomer;
    }
    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public int getVisitCustomer() {
        return visitCustomer;
    }

    public void setIDCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setSecondNameCustomer(String secondNameCustomer) {
        this.secondNameCustomer = secondNameCustomer;
    }
    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }
    public void setFatherNameCustomer(String fatherNameCustomer) {
        this.fatherNameCustomer = fatherNameCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public void setVisit(int visitCustomer) {
        this.visitCustomer = visitCustomer;
    }

    public int getIDWorker() {
		return idWorker;
	}

	public String getSecondNameWorker() {
		return secondNameWorker;
	}

	public String getNameWorker() {
		return nameWorker;
	}

	public String getFatherNameWorker() {
		return fatherNameWorker;
	}

	public int getPhoneWorker() {
		return phoneWorker;
	}
	
	public float getProssentWorker() {
		return prossentWorker;
	}
	public String getDateWorker() {
		return dateWorker;
	}

	public void  setIDWORKER(int idWorker) {
		this.idWorker = idWorker;
	}

	public void  setSecondNameWorker(String secondNameWorker) {
		this.secondNameWorker = secondNameWorker;
	}

	public void setName(String nameWorker) {
		this.nameWorker = nameWorker;
	}

	public void setFatherNameWorker(String fatherNameWorker) {
		this.fatherNameWorker = fatherNameWorker;
	}
	
	public void setPhoneWorker(int phoneWorker) {
		this.phoneWorker = phoneWorker;
	}
	
	public void setProssentWorker(float prossentWorker) {
		this.prossentWorker = prossentWorker;
	}
	public void setDateWorker(String dateWorker) {
		this.dateWorker = dateWorker;
	}
    

    public int getIDService() {
        return idService;
    }

    public String getNameService() {
        return nameService;
    }
    
    public int getCostService() {
        return costService;
    }

    public int getTimeService() {
        return timeService;
    }

    public void setID(int idService) {
        this. idService = idService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }
    public void setCostService(int costService) {
        this.costService = costService;
    }

    public void setTimeService(int timeService) {
        this.timeService = timeService;
    }


}
