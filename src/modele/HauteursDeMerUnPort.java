package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

public class HauteursDeMerUnPort implements Serializable{
	TreeMap<Date,HauteursDeMerUnJour> hauteursDeMerUnPort;
	String nomPort;
	
	public HauteursDeMerUnPort(TreeMap<Date,HauteursDeMerUnJour> hauteursDeMerUnPort, String nomPort) {
		
		this.hauteursDeMerUnPort = hauteursDeMerUnPort;
		this.nomPort = nomPort;
	}
	
	public TreeMap<Date,HauteursDeMerUnJour> getHauteursDeMerUnPort(){
		return hauteursDeMerUnPort;
	}
	
	public String getPort() {
		return nomPort;
	}
	
	public String toString() {
		String str = nomPort + " :";
		Set<Date> keys = hauteursDeMerUnPort.keySet();
		
		for(Date date : keys) {
			str += "\n" + hauteursDeMerUnPort.get(date);
		}
		
		return str;
	}
	
}
