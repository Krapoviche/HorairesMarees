package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

public class MareesUnPort implements Serializable{
	
	TreeMap<Date,MareesUnJour> mareesUnJourDuPort;
	String nomPort;
	
	public MareesUnPort(TreeMap<Date,MareesUnJour> mareesUnJourDuPort) {
		this.mareesUnJourDuPort = mareesUnJourDuPort;
		this.nomPort = mareesUnJourDuPort.get(new Date(01,06,2021)).getNomPort();
	}
	
	public String toString() {
		String str = nomPort+"\n";
		Set<Date> entrees = mareesUnJourDuPort.keySet();
		for(Date date: entrees) {
			str += mareesUnJourDuPort.get(date).toString();
		}
		return str;
	}
	
	public String getNomPort() {
		return nomPort;
	}
	
	public TreeMap<Date,MareesUnJour> getMareesUnJourDuPort(){
		return mareesUnJourDuPort;
	}
}
