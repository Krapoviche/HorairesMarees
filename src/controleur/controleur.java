package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import modele.ConstantesCalendrier;
import modele.Date;
import modele.Maree;
import modele.MareesUnJour;
import outils.LectureFichierSer;
import vue.BoutonDate;
import vue.FenetreMere;
import vue.PanelAffichage;
import vue.PanelCalendrier;
import vue.PanelChoix;
import vue.PanelMois;
import vue.PanelPrincipal;

public class controleur implements ActionListener{
	FenetreMere fenetreMere;
	PanelPrincipal panelPrincipal;
	PanelAffichage panelAffichage;
	PanelChoix panelChoix;
	PanelCalendrier panelCalendrier;
	
	Date dateCourante = new Date();
	File serFolder = new File("data_ports/datas_ser/");
	File[] serFiles = serFolder.listFiles();
	String port = serFiles[0].getName().split(".ser")[0];
	
	public controleur(FenetreMere fenetreMere) {
		this.fenetreMere =  fenetreMere;
		panelPrincipal = fenetreMere.getPanelPrincipal();
		panelChoix = fenetreMere.getPanelPrincipal().getPanelChoix();
		panelAffichage= fenetreMere.getPanelPrincipal().getPanelAffichage();
		panelCalendrier= fenetreMere.getPanelPrincipal().getPanelCalendrier();
		panelChoix.recordListener(this);
		panelCalendrier.recordListener(this);
		panelAffichage.recordListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[0]) {
			panelCalendrier.getPanelCentre().getCardManager().first(panelCalendrier.getPanelCentre());
			panelCalendrier.getPanelSud().setN(0);
			panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
		}
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[1]) {
			panelCalendrier.getPanelCentre().getCardManager().previous(panelCalendrier.getPanelCentre());
			if(panelCalendrier.getPanelSud().getN() != 0) {
				panelCalendrier.getPanelSud().setN(panelCalendrier.getPanelSud().getN()-1);
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
			else {
				panelCalendrier.getPanelSud().setN(11);
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
		}
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[2]) {
			panelCalendrier.getPanelCentre().getCardManager().next(panelCalendrier.getPanelCentre());
			if(panelCalendrier.getPanelSud().getN() != 11) {
				panelCalendrier.getPanelSud().setN(panelCalendrier.getPanelSud().getN()+1);
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
			else {
				panelCalendrier.getPanelSud().setN(0);
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
		}
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[3]) {
			panelCalendrier.getPanelCentre().getCardManager().last(panelCalendrier.getPanelCentre());
			panelCalendrier.getPanelSud().setN(ConstantesCalendrier.MOIS.length-1);
			panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
		}
		
		
		for(PanelMois panelMois : panelCalendrier.getPanelCentre().getPanelsMois()) {
			for(BoutonDate bouton : panelMois.getBoutonsDate()) {
				if(e.getSource() == bouton) {
					if(bouton.getDate().compareTo(new Date(01,06,2021)) >= 0 && bouton.getDate().compareTo(new Date(30,9,2021)) <= 0 ) {
						dateCourante = bouton.getDate();
						panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
						
						
						MareesUnJour mareesCourantes = LectureFichierSer.lecture(new File("data_ports//datas_ser//" + port.replaceAll(" ","_") + ".ser")).getMareesUnJourDuPort().get(new Date(dateCourante.getJour(),dateCourante.getMois(),dateCourante.getAnnee()));
						ArrayList<Maree> mareeList = new ArrayList<Maree>();
	
						mareeList.clear();
						for(Maree maree : mareesCourantes.getHautesMers()) {
							mareeList.add(maree);
						}
						for(Maree maree : mareesCourantes.getBassesMers()) {
							mareeList.add(maree);
						}
						int compteur = 0;
						for(Maree maree : mareeList){
							if(maree.getHauteur() == -1) {
								panelAffichage.getCards().setVisible(true);
								panelAffichage.getLabelsCoefficient()[compteur].setText("--");
								panelAffichage.getLabelsHauteur()[compteur].setText("---");
								panelAffichage.getLabelsHoraire()[compteur].setText("--:--");
							}
							else {
								panelAffichage.getCards().setVisible(true);
								panelAffichage.getLabelsCoefficient()[compteur].setText(Integer.toString(maree.getCoef()));
								panelAffichage.getLabelsHauteur()[compteur].setText(Double.toString(maree.getHauteur()));
								panelAffichage.getLabelsHoraire()[compteur].setText(maree.getHoraire());
							}
							
							compteur++;
						}
					}

					else {
						dateCourante = bouton.getDate();
						panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
						for(int compteur = 0 ; compteur < 4 ; compteur ++) {
							panelAffichage.getCards().setVisible(true);
							panelAffichage.getLabelsCoefficient()[compteur].setText("Pas d'informations");
							panelAffichage.getLabelsHauteur()[compteur].setText("Pas d'informations");
							panelAffichage.getLabelsHoraire()[compteur].setText("Pas d'informations");
						}
					}
				}
			}
		}
		for(int i = 0; i <  panelChoix.getBoutonsPort().length ; i++) {
			if(e.getSource() == panelChoix.getBoutonsPort()[i]) {
				if(dateCourante.compareTo(new Date(01,06,2021)) >= 0 && dateCourante.compareTo(new Date(30,9,2021)) <= 0 ) {
					port = panelChoix.getBoutonsPort()[i].getPort();
					panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
					MareesUnJour mareesCourantes = LectureFichierSer.lecture(new File("data_ports//datas_ser//" + port.replaceAll(" ","_") + ".ser")).getMareesUnJourDuPort().get(new Date(dateCourante.getJour(),dateCourante.getMois(),dateCourante.getAnnee()));
					ArrayList<Maree> mareeList = new ArrayList<Maree>();
	
					mareeList.clear();
					for(Maree maree : mareesCourantes.getHautesMers()) {
						mareeList.add(maree);
					}
					for(Maree maree : mareesCourantes.getBassesMers()) {
						mareeList.add(maree);
					}
					int compteur = 0;
					for(Maree maree : mareeList){
						if(maree.getHauteur() == -1) {
							panelAffichage.getCards().setVisible(true);
							panelAffichage.getLabelsCoefficient()[compteur].setText("--");
							panelAffichage.getLabelsHauteur()[compteur].setText("---");
							panelAffichage.getLabelsHoraire()[compteur].setText("--:--");
						}
						else {
							panelAffichage.getCards().setVisible(true);
							panelAffichage.getLabelsCoefficient()[compteur].setText(Integer.toString(maree.getCoef()));
							panelAffichage.getLabelsHauteur()[compteur].setText(Double.toString(maree.getHauteur()));
							panelAffichage.getLabelsHoraire()[compteur].setText(maree.getHoraire());
						}
						
						compteur++;
					}
				}			
				else {
					panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
					for(int compteur = 0 ; compteur < 4 ; compteur ++) {
						panelAffichage.getCards().setVisible(true);
						panelAffichage.getLabelsCoefficient()[compteur].setText("Pas d'informations");
						panelAffichage.getLabelsHauteur()[compteur].setText("Pas d'informations");
						panelAffichage.getLabelsHoraire()[compteur].setText("Pas d'informations");
					}
				}
			}
		}
		
		
		if(e.getSource() == panelAffichage.getBoutonMaree()) {
			panelAffichage.getCardLayout().first(panelAffichage.getCards());
		}		
		if(e.getSource() == panelAffichage.getBoutonTable()) {
			panelAffichage.getCardLayout().last(panelAffichage.getCards());
		}
	}
	
	
	
	
}
