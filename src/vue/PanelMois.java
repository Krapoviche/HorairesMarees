package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.Date;


/**
 * Panel qui instancie autant de BontonDate qu'il est necessaire d'en afficher pour le mois demand� et l'ann�e demand�e
 * Cette classe � �t� en partie d�velopp�e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
 */
public class PanelMois extends JPanel{
	
	//D�finition des variables n�cessaires
	int moisCal,anCal;
	CalendrierDuMois datesMois;
	Collection<Date> listeDates;
	BoutonDate[] boutonsDate; 
	
	/**
	 * Constructeur qui permet d'instancier un panelMois � partir d'un mois et d'une ann�e.
	 * @param parMois Le mois duquel on veut le panelMois
	 * @param parAn L'ann�e de laquelle on veut le panelMois
	 */
	public PanelMois(int parMois,int parAn) {
		
		//Instantiation des variables
		moisCal = parMois;
		anCal = parAn;
		listeDates = new CalendrierDuMois(moisCal+1,anCal).getDates();
		boutonsDate = new BoutonDate[listeDates.size()];
		int x = 0;
		
		//D�finition du layout pour le panelMois cr��
		setLayout(new GridBagLayout());	
		//D�finition des ses contraintes, de sa couleur de fond et de son inset
		GridBagConstraints contrainte = new GridBagConstraints();
		setBackground(new Color(3,34, 76));
		contrainte.insets = new Insets(10, 0, 10, 20);
		
		//Pour chaque jour de la semaine
		for(int i = 0 ; i < ConstantesCalendrier.JOURS_SEMAINE.length ; i++ ) {
			//Instanciation du label d'affichage du nom du jour abr�g�
			JLabel label = new JLabel(ConstantesCalendrier.JOURS_SEMAINE_ABR[i]);
			label.setForeground(new Color(255,200,120));
			add(label,contrainte);
		}
		
		contrainte.gridy += 1;
		
		//D�finition d'un tableau de dates � partir de la liste de dates d�finie gr�ce � la classe CalendrierDuMois
		Date[] datesArray = listeDates.toArray(new Date[0]);
		
		contrainte.gridy += 1;
		
		//Pour chaque boutonDate
		for(int i = 0; i < boutonsDate.length ; i++) {
			//Si on a chang� de semaine on passe � la ligne suivante
			if(i%7 == 0 && i != 0) {
				contrainte.gridy += 1;
			}
			//On d�finit le bouton courant sur la date courante
			boutonsDate[i] = new BoutonDate(datesArray[x]);
			
			x++;
			
			//Si la date est hors du mois qu'on traite, on en change la couleur pour que ce soit visible
			if(boutonsDate[i].getDate().getMois() != parMois +1) {
				boutonsDate[i].setForeground(Color.BLUE);
			}
			//On ajoute le boutonDate au panelMois
			add(boutonsDate[i],contrainte);
		}
	}

	public BoutonDate[] getBoutonsDate() {
		return boutonsDate;
	}

}
