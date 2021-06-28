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
	JPanel panelNord = new JPanel();
	JLabel labelInfosNord1 = new JLabel("Les infos marées concernent la période du 01/06/2021 au 30/09/2021.");
	JLabel labelInfosNord2 = new JLabel("Les hauteurs de mer son compilées pour les périodes du 01/01/2020 au 27/06/2021.");
	JLabel labelInfosNord3 = new JLabel("Seuls certains des ports ont des hauteurs de mer heures par heures.");
	
	PanelCentreCalendrier panelCentre;
	PanelSudCalendrier panelSud;
	public PanelCalendrier() {
		setLayout(new BorderLayout(0,0));
		panelCentre = new PanelCentreCalendrier();
		panelSud = new PanelSudCalendrier();
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud,BorderLayout.SOUTH);
		
		labelInfosNord1.setForeground(new Color(255,200,120));
		labelInfosNord2.setForeground(new Color(255,200,120));
		labelInfosNord3.setForeground(new Color(255,200,120));
		
		panelNord.setBackground(new Color(3,34, 76));
		panelNord.setLayout(new BorderLayout());
		panelNord.add(labelInfosNord1,BorderLayout.NORTH);
		panelNord.add(labelInfosNord2,BorderLayout.CENTER);
		panelNord.add(labelInfosNord3,BorderLayout.SOUTH);
		add(panelNord,BorderLayout.NORTH);
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