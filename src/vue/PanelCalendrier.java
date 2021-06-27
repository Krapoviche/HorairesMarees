package vue;

import javax.swing.*;

import controleur.controleur;

//import Controleur.Controleur;

import java.awt.*;

/**
 * Panel qui instancie un PanelCentreCalendrier et un PanelSudCalendrier
 *
 */

public class PanelCalendrier extends JPanel{
	PanelCentreCalendrier panelCentre;
	PanelSudCalendrier panelSud;
	public PanelCalendrier() {
		setLayout(new BorderLayout(0,0));
		panelCentre = new PanelCentreCalendrier();
		panelSud = new PanelSudCalendrier();
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud,BorderLayout.SOUTH);
	}
	public PanelSudCalendrier getPanelSud() {
		return panelSud;
	}
	public PanelCentreCalendrier getPanelCentre() {
		return panelCentre;
	}
	public void recordListener(controleur parControleur) {
		for(PanelMois panelMois : panelCentre.getPanelsMois()) {
			for(BoutonDate bouton : panelMois.boutonsDate)
				bouton.addActionListener(parControleur);
		}
		for(JButton bouton : panelSud.getButtons()) {
			bouton.addActionListener(parControleur);
		}
	}
}