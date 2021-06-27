package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

public class HauteursDeMerUnJour implements Serializable{
	TreeMap<Integer,HauteurDeMer> hauteursUnJour;
	Date date;
	
	public HauteursDeMerUnJour(TreeMap<Integer,HauteurDeMer> hauteursUnJour,Date date) {
		this.hauteursUnJour = hauteursUnJour;
		this.date = date;
	}
	
	public TreeMap<Integer,HauteurDeMer> getHauteursUnJour(){
		return hauteursUnJour;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String toString() {
		String str = date.toString() + " :";
		Set<Integer> keys = hauteursUnJour.keySet();
		
		for(Integer i : keys) {
			str += "\n" + hauteursUnJour.get(i).toString();
		}
		
		return str;
	}
	
}
