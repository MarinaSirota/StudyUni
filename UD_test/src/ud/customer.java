package ud;

public class customer implements Comparable<customer> {

    protected int id;
    protected String secondName;
    protected String name;
    protected String fatherName;
    protected String phone;
    protected int visit;
    public customer(int id_, String secondName_, String name_,String fatherName_, String phone_, int visit_) {
        id=id_;
        secondName=secondName_;
        name=name_;
        fatherName=fatherName_;
        phone=phone_;
        visit=visit_;
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

    public int getVisit() {
        return visit;
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

    public void setVisit(int visit) {
        this.visit = visit;
    }
    
	@Override
    public int compareTo(customer c) {
    	return this.secondName.compareTo(c.secondName);
    	}
   
    }

   

