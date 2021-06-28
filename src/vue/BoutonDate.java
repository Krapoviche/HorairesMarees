package vue;

import java.awt.Color;

import javax.swing.JButton;

import modele.Date;


/**
 * Classe qui permet d'instancier un bouton auquel est associé une date.
 * Cette classe à été développée par l'équipe pédagogique de l'IUT de Vélizy - Département Informatique
 */
public class BoutonDate extends JButton {

	private Date date;
	/**
	 * Constructeur d'un BoutonDate
	 * @param date La date à lier au bouton
	 */
	public BoutonDate(Date date) {
		super (Integer.toString(date.getJour()));
		this.date= date;
		setBackground(new Color(255,200,120));
	}

	
	/**
	 * Getter qui permet de récupérer la date liée à un bouton
	 * @return
	 */
	public Date getDate() {		 
		return date;
	}

}
