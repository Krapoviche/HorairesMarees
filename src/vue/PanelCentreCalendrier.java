package vue;

import java.awt.CardLayout;
import modele.Date;

import javax.swing.JPanel;

import modele.ConstantesCalendrier;

public class PanelCentreCalendrier extends JPanel{
	CardLayout cardManager = new CardLayout(0,0);
	PanelMois[] panelsMois = new PanelMois[24];
	
	
	
	public PanelCentreCalendrier(){
		setLayout(getCardManager());
		for(int indice  = 0 ; indice < panelsMois.length; indice++) {
			if(indice > 11) {
				panelsMois[indice] = new PanelMois(indice-12,new Date().getAnnee());
				add(panelsMois[indice],ConstantesCalendrier.MOIS[indice-12]);
			}
			else {
				panelsMois[indice] = new PanelMois(indice,new Date().getAnnee()-1);
				add(panelsMois[indice],ConstantesCalendrier.MOIS[indice]);
			}
		}
		getCardManager().show(this,ConstantesCalendrier.MOIS[new Date().getMois()-1]);
	}
	

	public PanelMois[] getPanelsMois() {
		return panelsMois;
	}

	public CardLayout getCardManager() {
		return cardManager;
	}
}
