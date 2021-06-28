package vue;

import java.awt.CardLayout;
import modele.Date;

import javax.swing.JPanel;

import modele.ConstantesCalendrier;

/**
 * Classe qui permet d'instancier des PanelCentreCalendrier qui correspondent au panneau qui supperpose tous les panels qui contiennent tous les boutonDate.
 * Cette classe à été en partie développée par l'équipe pédagogique de l'IUT de Vélizy - Département Informatique.
 *
 */
public class PanelCentreCalendrier extends JPanel{
	
	//Instanciation du layout
	CardLayout cardManager = new CardLayout(0,0);
	
	//Instantiation du tableau contenant les 24 paneaux mois qui contiennent les boutonsDate
	PanelMois[] panelsMois = new PanelMois[24];
	
	
	/**
	 * Constructeur qui peremet d'instancier des PanelCentreCalendrier sans paramètre
	 */
	public PanelCentreCalendrier(){
		
		//Définition du layout
		setLayout(cardManager);
		
		//Pour chaque panelMois
		for(int indice  = 0 ; indice < panelsMois.length; indice++) {
			//Si on dépasse le maximum d'indice de mois (11)
			if(indice > 11) {
				//On instancie les panelMois adéquats
				panelsMois[indice] = new PanelMois(indice-12,new Date().getAnnee());
				add(panelsMois[indice],ConstantesCalendrier.MOIS[indice-12]);
			}
			//Sinon
			else {
				//On instancie les panelMois adéquats
				panelsMois[indice] = new PanelMois(indice,new Date().getAnnee()-1);
				add(panelsMois[indice],ConstantesCalendrier.MOIS[indice]);
			}
		}
		//On définit le premier calendrier affiché comme le calendrier courant
		getCardManager().show(this,ConstantesCalendrier.MOIS[new Date().getMois()-1]);
	}
	

	public PanelMois[] getPanelsMois() {
		return panelsMois;
	}

	public CardLayout getCardManager() {
		return cardManager;
	}
}
