package vue;

import java.awt.CardLayout;
import modele.Date;

import javax.swing.JPanel;

import modele.ConstantesCalendrier;

/**
 * Classe qui permet d'instancier des PanelCentreCalendrier qui correspondent au panneau qui supperpose tous les panels qui contiennent tous les boutonDate.
 * Cette classe � �t� en partie d�velopp�e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
 *
 */
public class PanelCentreCalendrier extends JPanel{
	
	//Instanciation du layout
	CardLayout cardManager = new CardLayout(0,0);
	
	//Instantiation du tableau contenant les 24 paneaux mois qui contiennent les boutonsDate
	PanelMois[] panelsMois = new PanelMois[24];
	
	
	/**
	 * Constructeur qui peremet d'instancier des PanelCentreCalendrier sans param�tre
	 */
	public PanelCentreCalendrier(){
		
		//D�finition du layout
		setLayout(cardManager);
		
		//Pour chaque panelMois
		for(int indice  = 0 ; indice < panelsMois.length; indice++) {
			//Si on d�passe le maximum d'indice de mois (11)
			if(indice > 11) {
				//On instancie les panelMois ad�quats
				panelsMois[indice] = new PanelMois(indice-12,new Date().getAnnee());
				add(panelsMois[indice],ConstantesCalendrier.MOIS[indice-12]);
			}
			//Sinon
			else {
				//On instancie les panelMois ad�quats
				panelsMois[indice] = new PanelMois(indice,new Date().getAnnee()-1);
				add(panelsMois[indice],ConstantesCalendrier.MOIS[indice]);
			}
		}
		//On d�finit le premier calendrier affich� comme le calendrier courant
		getCardManager().show(this,ConstantesCalendrier.MOIS[new Date().getMois()-1]);
	}
	

	public PanelMois[] getPanelsMois() {
		return panelsMois;
	}

	public CardLayout getCardManager() {
		return cardManager;
	}
}
