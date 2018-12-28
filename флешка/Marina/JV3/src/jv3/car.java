package jv3;

import java.util.Scanner;

public class car implements Comparable<car> {

	protected final String marka;
	protected final int year;
	protected final float capacity;
	protected final int maxSpeed;

	public car(String marka, int year, float capacity, int maxSpeed) {
		this.marka = marka;
		this.year = year;
		this.capacity = capacity;
		this.maxSpeed = maxSpeed;
	}

	public String getMarka() {
		return marka;
	}

	public int getYear() {
		return year;
	}

	public float getPageCount() {
		return capacity;
	}

	public int getPublisher() {
		return maxSpeed;
	}

	public static car read(Scanner scn) {
		return new car(scn.next(), scn.nextInt(), scn.nextFloat(), scn.nextInt());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("car marka: ");
		sb.append(marka);
		sb.append("; year: ");
		sb.append(year);
		sb.append("; pages: ");
		sb.append(capacity);
		sb.append("; maxSpeed: ");
		sb.append(maxSpeed);
		sb.append(";");
		return sb.toString();
	}

	public String toKeyValueString() {
		StringBuilder sb = new StringBuilder();
		sb.append(marka);
		sb.append(" -> ");
		sb.append(year);
		sb.append("; ");
		sb.append(capacity);
		sb.append("; ");
		sb.append(maxSpeed);
		sb.append(";");
		return sb.toString();
	}

	public String toRawString() {
		StringBuilder sb = new StringBuilder();
		sb.append(marka);
		sb.append(" ");
		sb.append(year);
		sb.append(" ");
		sb.append(capacity);
		sb.append(" ");
		sb.append(maxSpeed);
		sb.append(" ");
		return sb.toString();
	}

	@Override
	public int compareTo(car o) {
		return marka.compareTo(o.marka);
	}
}
