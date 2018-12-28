package jv3;

import java.util.Comparator;

public class carComparator implements Comparator<car> {

	@Override
	public int compare(car c1, car c2) {
		return c1.getYear() - c2.getYear();
	}
}
