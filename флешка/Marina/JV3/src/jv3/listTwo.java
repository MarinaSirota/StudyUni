package jv3;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;


public class listTwo extends LinkedList<car> {
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

		public void write(String filename){
		try(PrintWriter wrt =new PrintWriter(new FileOutputStream(filename))){
		for(car c :this){
		                wrt.write(c.toRawString());
		                wrt.write("\n");
		}
		}catch(FileNotFoundException e){
		            System.out.println("Файл не найден");
		            e.printStackTrace();
		}
		}

}
