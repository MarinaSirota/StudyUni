package ud;

public class record {


	    protected String secondNameCustomer;
	    protected String nameCustomer;
	    protected String fatherNameCustomer;
	    protected String phoneCustomer;
	    
	   	protected String secondNameWorker;
		protected String nameWorker;
		protected String fatherNameWorker;
		protected String phoneWorker;
			    
		protected String nameService; 
		protected String dateService;
	    protected int hourService;
	    protected int statusService;
	    

	    public record (String secondNameCustomer_, String nameCustomer_,String fatherNameCustomer_, String phoneCustomer_,
	    		String secondNameWorker_, String nameWorker_, String fatherNameWorker_, String phoneWorker_, 
				String nameService_, String dateService_, int hourService_, int statusService_) {
	        
	        secondNameCustomer=secondNameCustomer_;
	        nameCustomer=nameCustomer_;
	        fatherNameCustomer=fatherNameCustomer_;
	        phoneCustomer=phoneCustomer_;
	        
			secondNameWorker = secondNameWorker_;
			nameWorker = nameWorker_;
			fatherNameWorker = fatherNameWorker_;
			phoneWorker = phoneWorker_;

		    nameService=nameService_;
		    dateService=dateService_;
		    hourService=hourService_;
		    statusService=statusService_;

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

	 
		public String getSecondNameWorker() {
			return secondNameWorker;
		}

		public String getNameWorker() {
			return nameWorker;
		}

		public String getFatherNameWorker() {
			return fatherNameWorker;
		}

		public String getPhoneWorker() {
			return phoneWorker;
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
		
		public void setPhoneWorker(String phoneWorker) {
			this.phoneWorker = phoneWorker;
		}
		
	    
	    public String getNameService() {
	        return nameService;
	    }
	    
	    public int getHourService() {
	        return hourService;
	    }

	    public String getDateService() {
	        return dateService;
	    }
	    public int getStatusService() {
	        return statusService;
	    }


	    public void setNameService(String nameService) {
	        this.nameService = nameService;
	    }
	    public void setHourService(int hourService) {
	        this.hourService = hourService;
	    }

	    public void setDateService(String dateService) {
	        this.dateService = dateService;
	    }
	    public void setStatusService(int statusService) {
	        this.statusService = statusService;
	    }

	
}
