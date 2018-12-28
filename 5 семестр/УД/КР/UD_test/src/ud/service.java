package ud;

import java.util.Comparator;

public class service implements Comparator<service> {

	    protected int id;
	    protected String name;
	    protected int cost;
	    protected int time;
	    public service(int id_, String name_, int cost_, int time_) {
	        id=id_;
	        name=name_;
	        cost=cost_;
	        time=time_;
	    }

	    public int getID() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }
	    
	    public int getCost() {
	        return cost;
	    }

	    public int getTime() {
	        return time;
	    }

	    public void setID(int id) {
	        this.id = id;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public void setCost(int cost) {
	        this.cost = cost;
	    }

	    public void setTime(int time) {
	        this.time = time;
	    }
	    
	    
		@Override
	    public int compare(service c1, service c2) {
			return c1.getID() - c2.getID();
	    }
	   
	    }


