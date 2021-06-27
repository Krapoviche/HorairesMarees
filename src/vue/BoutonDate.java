package vue;

import java.awt.Color;

import javax.swing.JButton;

import modele.Date;


/**
 * Bouton qui correspond � une date du calendrier(date pass�e en param�tre du constructeur)
 *
 */
public class BoutonDate extends JButton {

	private Date date;
	public BoutonDate(Date date) {
		super (Integer.toString(date.getJour()));
		this.date= date;
		setBackground(new Color(255,200,120));
	}

	

	public Date getDate() {		 
		return date;
	}

}
