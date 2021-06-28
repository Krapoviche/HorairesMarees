package modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe qui permet d'instancier des objet MareesUnJour
 */
public class MareesUnJour implements Serializable {
	private static final long serialVersionUID = 7062286444439820135L;
	private ArrayList<Maree> mareeList;
	private ArrayList<Maree> bassesMers = new ArrayList<Maree>();
	private ArrayList<Maree> hautesMers = new ArrayList<Maree>();	
	private Date date;
	private String nomPort;

	/**
	 * Constructeur des objets MareeUnJour � partir d'une liste de mar�es ainsi qu'un port, une date correspondant
	 * @param list La liste de mar�es
	 * @param nomPort Le nom du port correspondant
	 * @param date La date � laquelle correspondent toutes les mar�es
	 */
	public MareesUnJour(ArrayList<Maree> list,String nomPort,Date date) {
		mareeList = list;
		//Pour chaque mar�e
		for(Maree maree : mareeList) {
			//Si c'est une basse mer
			if(maree.getType().compareTo("basse mer") == 0) {
				//On ajoute � la liste locale correspondante
				bassesMers.add(maree);
			}
			//Si C'est une haute mer
			else {
				//On ajoute � la liste locale correspondante
				hautesMers.add(maree);
			}
		}
		
		date = mareeList.get(0).getDate();
		this.nomPort = nomPort;
		this.date = date;
	}

	
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
	
	//Getters sur les diff�rents champs de l'objet
	
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
