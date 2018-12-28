package jv3;

import java.util.TreeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class listOne extends TreeSet<car> {
	
	public void read(String filename){
		this.clear();
		try(Scanner scn =new Scanner(new FileInputStream(filename))){
		while(scn.hasNext()){
		                add(car.read(scn));
		}
		}catch(FileNotFoundException e){
		            System.out.println("Файл не найден");
		            e.printStackTrace();
		}
		}
	public void print(){
		for(car c :this){
		            System.out.println(c.toString());
		}
		}
	public boolean contains(String marka){
		for(car c :this){
		if(c.getMarka().equalsIgnoreCase(marka)){
		return true;
		}
		}
		return false;
		}
}
