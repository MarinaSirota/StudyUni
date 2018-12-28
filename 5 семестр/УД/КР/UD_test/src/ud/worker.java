package ud;

public class worker implements Comparable<worker> {

	protected int id;
	protected String secondName;
	protected String name;
	protected String fatherName;
	protected String phone;
	protected float prossent;
	protected String date;

	public worker(int id_, String secondName_, String name_, String fatherName_, String phone_, float prossent_, 
			String date_) {
		id = id_;
		secondName = secondName_;
		name = name_;
		fatherName = fatherName_;
		phone = phone_;
		prossent = prossent_;
		date=date_;
	}

	public int getID() {
		return id;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getName() {
		return name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getPhone() {
		return phone;
	}

	public float getProssent() {
		return prossent;
	}
	public String getDate() {
		return date;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setProssent(float prossent) {
		this.prossent = prossent;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public int compareTo(worker c) {
		return this.secondName.compareTo(c.secondName);
	}
	@Override 
	public String toString() { 
	return secondName +" "+name; 
	}

}
