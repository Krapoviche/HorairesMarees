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
 * Panel qui instancie autant de BontonDate qu'il est necessaire d'en afficher pour le mois demandé et l'année demandée
 *
 */
public class PanelMois extends JPanel{
	int moisCal,anCal;
	CalendrierDuMois datesMois;
	Collection<Date> listeDates;
	BoutonDate[] boutonsDate; 
	
	public PanelMois(int parMois,int parAn) {
		moisCal = parMois;
		anCal = parAn;
		listeDates = new CalendrierDuMois(moisCal+1,anCal).getDates();
		boutonsDate = new BoutonDate[listeDates.size()];
		int x = 0;
		setLayout(new GridBagLayout());	
		GridBagConstraints contrainte = new GridBagConstraints();
		setBackground(new Color(3,34, 76));
		contrainte.insets = new Insets(10, 0, 10, 20);
		
		for(int i = 0 ; i < ConstantesCalendrier.JOURS_SEMAINE.length ; i++ ) {
			add(new JLabel(ConstantesCalendrier.JOURS_SEMAINE_ABR[i]),contrainte);
		}
		contrainte.gridy += 1;
		Date[] datesArray = listeDates.toArray(new Date[0]);
		
		contrainte.gridy += 1;
		for(int i = 0; i < boutonsDate.length ; i++) {
			if(i%7 == 0 && i != 0) {
				contrainte.gridy += 1;
			}
			boutonsDate[i] = new BoutonDate(datesArray[x]);
			x++;
			if(boutonsDate[i].getDate().getMois() != parMois +1) {
				boutonsDate[i].setForeground(Color.BLUE);
			}
			add(boutonsDate[i],contrainte);
		}
	}

	public BoutonDate[] getBoutonsDate() {
		return boutonsDate;
	}

}
