package modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe qui permet d'instancier des objet MareeUnJour
 */
public class MareesUnJour implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7062286444439820135L;
	private ArrayList<Maree> mareeList;
	private ArrayList<Maree> bassesMers = new ArrayList<Maree>();
	private ArrayList<Maree> hautesMers = new ArrayList<Maree>();	
	private Date date;
	private String nomPort;
	/**
	 * constructeur des objets MareeUnJour
	 * @param ArrayList des marre
	 */
	public MareesUnJour(ArrayList<Maree> list,String nomPort,Date date) {
		mareeList = list;
		for(Maree maree : mareeList) {
			if(maree.getType().compareTo("basse mer") == 0) {
				bassesMers.add(maree);
			}
			else {
				hautesMers.add(maree);
			}
		}
		date = mareeList.get(0).getDate();
		this.nomPort = nomPort;
		this.date = date;
	}
	/**
	 * affichage d'un objet maree 
	 * @return date "retour a la ligne" maree
	 */
	public String toString() {
		String str = "Le " + date.toString() + "\nSes pleines mers : \n";
		for(Maree maree : hautesMers) {
			str += "	" + maree.toString() + "\n";
		}
		str += "Ses basses mers : \n";
		for(Maree maree : bassesMers) {
			str += "	" + maree.toString() + "\n";
		}
		return str;
	}
	
	public String getNomPort() {
		return nomPort;
	}
	public Date getDate() {
		return date;
	}
	public ArrayList<Maree> getMareeList() {
		return mareeList;
	}
	public ArrayList<Maree> getBassesMers() {
		return bassesMers;
	}
	public ArrayList<Maree> getHautesMers() {
		return hautesMers;
	}
	
}
