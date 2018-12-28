package jv3;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class listThree extends TreeMap<String, car> {
	public void read(String filename){
		this.clear();
		        car c;
		try(Scanner scn =new Scanner(new FileInputStream(filename))){
		while(scn.hasNext()){
		                c = car.read(scn);
		                put(c.getMarka(),c);
		}
		}catch(FileNotFoundException e){
		            System.out.println("Файл не найден");
		            e.printStackTrace();
		}
		}

		public void print(){
		for(car c :this.values()){
		            System.out.println(c.toKeyValueString());
		}
		}

		public void print(String marka){
		        car c = get(marka);
		if(c !=null){
		            System.out.println(c.toKeyValueString());
		}else{
		            System.out.println("Нет такой марки");
		}
		}

}
