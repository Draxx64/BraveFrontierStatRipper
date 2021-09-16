package src;

import java.util.*;
import java.net.*;
import java.io.*;

public class Main{
	
	public static ArrayList<Unit> Units = new ArrayList<>();
	
	public static Scanner getin = new Scanner(System.in);
	
	public static void main(String[] args){
		
		System.out.println("Enter the name of a unit");
		
		while(true){
			
			System.out.print("\nName: ");
			String find = getin.nextLine();
			Unit read = findUnit(find);
			printUnit(read);
			
			saveInfo();
			
		}
		
		//String name = "Pirate Goddess Eve";
		//Unit u = makeUnit(name, isolateStatsFromWebpage(name));
		
	}
	
	public static void saveInfo(){
		try{
			File save = new File("data.txt");
			FileWriter writer = new FileWriter(save);
			for(int i = 0; i < Units.size(); i++){
				writer.write(">>> NEW UNIT >>>");
				writer.write("FART");
				writer.write("<<< END UNIT <<<");
			}
			
			writer.write("!!! END OF FILE !!!");
			
			writer.close();
			save.close();
		}
		catch(Exception e){
			System.out.println("Problem saving info: ");
			System.out.println(e);
		}
		
		
	}
	
	public static Unit findUnit(String name){
		// search for existing unit
		for(int i = 0; i < Units.size(); i++){
			System.out.println(Units.get(i).name);
			if(Units.get(i).name.equals(name)){
				return Units.get(i);
			}
		}
		// if unit not in data, make a new one and add it to the list
		System.out.println("loading new info...");
		Unit u = makeUnit(name, isolateStatsFromWebpage(name));
		addUnit(u);
		return u;
	}
	
	public static void printUnit(Unit u){
		System.out.println("NAME: " + u.name);
		System.out.println("hp:  " + u.hp);
		System.out.println("atk: " + u.atk);
		System.out.println("def: " + u.def);
		System.out.println("rec: " + u.rec);
	}
	
	public static void addUnit(Unit u){
		if(u.hp != 0 && u.atk != 0 && u.def != 0 && u.rec != 0){
			Units.add(u);
		}
	}

	public static Unit makeUnit(String name, Integer[] stats){
		return new Unit(name, stats[0], stats[1], stats[2], stats[3]);
	}
	
	public static Integer[] isolateStatsFromWebpage(String name){
		try{
			String link = "https://bravefrontierglobal.fandom.com/wiki/" + 
				name.replace(' ', '_') +
				"?action=edit";
			URL page = new URL(link);
			URLConnection connection = page.openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			
			scanner.useDelimiter("\\Z");
			String content = scanner.next();
			
			String statcontent = findSubstring(content, "| hp_base", "| hp_lord");
			
			return(getStats(statcontent));
		}
		catch(Exception e){
			System.out.println("Uh oh, a fucky wucky occurred:");
			System.out.println(e);
		}
		System.out.println("ERROR OCCURRED");
		return new Integer[]{0, 0, 0, 0};
	}
	
	public static String findSubstring(String content, String start, String end){
		int startindex = content.indexOf(start);
		int endindex = content.indexOf(end);
		return content.substring(startindex, endindex);
	}
	
	public static Integer[] getStats(String stats){
		Integer[] statlist = new Integer[4];
		String[] prestat = new String[4];
		
		prestat = stats.split("\n");
		
		for(int i = 0; i < 4; i++){
			prestat[i] = prestat[i].substring(21);
			statlist[i] = Integer.parseInt(prestat[i]);
		}
		
		return statlist;
	}
	
	public static class Unit{
		public String name;
		public int hp;
		public int atk;
		public int def;
		public int rec;
		
		public Unit(String name, int hp, int atk, int def, int rec){
			this.name = name;
			this.hp = hp;
			this.atk = atk;
			this.def = def;
			this.rec = rec;
		}
		
	}
	
	public static <T> void printList(T[] list){
		for(T i : list){
			System.out.print(i +", ");
		}
		System.out.println("\b");
	}
	
}