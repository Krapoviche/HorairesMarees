package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelPrincipal extends  JPanel{
	//Panel � trois r�gions : Calendrier / Selection de la date / Affichage des infos
	//On divise en trois Panels PanelAffichage, PanelCalendrier, PanelChoix
	
	PanelAffichage panelAffichage;
	PanelChoix panelChoix;
	PanelCalendrier panelCalendrier;

	
	public PanelPrincipal() {
		setLayout(new BorderLayout(0,0));
		
		panelAffichage = new PanelAffichage();
		panelChoix = new PanelChoix();
		panelCalendrier = new PanelCalendrier();
		
		
		add(BorderLayout.CENTER, panelAffichage);
		add(BorderLayout.EAST, panelChoix);
		add(BorderLayout.WEST,panelCalendrier);
	}
	
	
	public PanelAffichage getPanelAffichage() {
		return panelAffichage;
	}

	public PanelChoix getPanelChoix() {
		return panelChoix;
	}
	public PanelCalendrier getPanelCalendrier() {
		return panelCalendrier;
	}
}
