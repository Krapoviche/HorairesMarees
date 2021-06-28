package vue;

import java.awt.Color;

import javax.swing.JButton;

import modele.Date;


/**
 * Classe qui permet d'instancier un bouton auquel est associ� une date.
 * Cette classe � �t� d�velopp�e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique
 */
public class BoutonDate extends JButton {

	private Date date;
	/**
	 * Constructeur d'un BoutonDate
	 * @param date La date � lier au bouton
	 */
	public BoutonDate(Date date) {
		super (Integer.toString(date.getJour()));
		this.date= date;
		setBackground(new Color(255,200,120));
	}

	
	/**
	 * Getter qui permet de r�cup�rer la date li�e � un bouton
	 * @return
	 */
	public Date getDate() {		 
		return date;
	}

}
