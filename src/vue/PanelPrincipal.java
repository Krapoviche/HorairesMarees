package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PanelPrincipal extends  JPanel{
	//Panel à trois régions : Calendrier / Selection de la date / Affichage des infos
	//On divise en trois Panels PanelAffichage, PanelCalendrier, PanelChoix
	
	PanelAffichage panelAffichage;
	PanelChoix panelChoix;
	PanelCalendrier panelCalendrier;

	
	public PanelPrincipal() {
		setLayout(new BorderLayout(15,15));
		
		panelAffichage = new PanelAffichage();
		panelChoix = new PanelChoix();
		panelCalendrier = new PanelCalendrier();
		setBackground(new Color(3,34, 76));		
		
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
