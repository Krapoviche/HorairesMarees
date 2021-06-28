package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import modele.ConstantesCalendrier;
import modele.Date;
import modele.HauteurDeMer;
import modele.HauteursDeMerUnJour;
import modele.HauteursDeMerUnPort;
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

/**
 * Classe du controleur de l'IHM, il re�oit les actions et les traite selon leur origine
 *
 */
public class controleur implements ActionListener{
	
	//On instancie les diff�rents panels de l'IHM
	FenetreMere fenetreMere;
	PanelPrincipal panelPrincipal;
	PanelAffichage panelAffichage;
	PanelChoix panelChoix;
	PanelCalendrier panelCalendrier;
	
	//On d�finit les variables qui vont servir dans l'environnement
	Date dateCourante = new Date();
	File serFolder = new File("data_ports/datas_ser/");
	File[] serFiles = serFolder.listFiles();
	String port = serFiles[0].getName().split(".ser")[0];
	
	/**
	 * Constructeur du controleur
	 * @param fenetreMere objet de la classe FenetreMere qui h�rite de la classe JFrame (fenetre graphique)
	 */
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

		
		//Si on demande le premier mois du panelCalendrier
		if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[0]) {
			//Affichage du premier panel du cardLayout
			panelCalendrier.getPanelCentre().getCardManager().first(panelCalendrier.getPanelCentre());
			//Modification du num�ro de mois
			panelCalendrier.getPanelSud().setN(0);
			//Modification du label qui affiche le mois affich� dans le panelCalendrier
			panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
		}
		
		//Si on demande le mois pr�c�dent du panelCalendrier
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[1]) {
			//On affiche le panel pr�c�dent
			panelCalendrier.getPanelCentre().getCardManager().previous(panelCalendrier.getPanelCentre());			
			//Si on passe d'un mois "normal" � un autre (ex Mars -> F�vrier)

			if(panelCalendrier.getPanelSud().getN() != 0) {
				//Modification du num�ro de mois
				panelCalendrier.getPanelSud().setN(panelCalendrier.getPanelSud().getN()-1);
				//Modification du label qui affiche le mois affich� dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}

			//Sinon (le cas o� on passe de Janvier � D�cembre)
			else {
				//Modification du num�ro de mois
				panelCalendrier.getPanelSud().setN(11);
				//Modification du label qui affiche le mois affich� dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
		}
		
		//Si on demande le mois suivant du panelCalendrier		
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[2]) {
			//On affiche le panel suivant
			panelCalendrier.getPanelCentre().getCardManager().next(panelCalendrier.getPanelCentre());
			//Si on passe d'un mois "normal" � un autre (ex F�vrier -> Mars)
			if(panelCalendrier.getPanelSud().getN() != 11) {
				//Modification du num�ro de mois
				panelCalendrier.getPanelSud().setN(panelCalendrier.getPanelSud().getN()+1);
				//Modification du label qui affiche le mois affich� dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}

			//Sinon (le cas o� on passe de D�cembre � Janvier)
			else {
				//Modification du num�ro de mois
				panelCalendrier.getPanelSud().setN(0);
				//Modification du label qui affiche le mois affich� dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
		}
		
		//Si on demande le dernier mois du panelCalendrier
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[3]) {
			//On affiche le dernier panel
			panelCalendrier.getPanelCentre().getCardManager().last(panelCalendrier.getPanelCentre());
			//Modification du num�ro de mois
			panelCalendrier.getPanelSud().setN(ConstantesCalendrier.MOIS.length-1);
			//Modification du label qui affiche le mois affich� dans le panelCalendrier
			panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
		}
		
		
		//Pour chaque panelMois
		for(PanelMois panelMois : panelCalendrier.getPanelCentre().getPanelsMois()) {
			//Pour chaque BoutonMois du panelMois
			for(BoutonDate bouton : panelMois.getBoutonsDate()) {
				//Si le bouton courant est � l'origine de l'�v�nement
				if(e.getSource() == bouton) {
					//La date courante change pour la date de l'�v�nement en cours
					dateCourante = bouton.getDate();
					//Si la date est dans la p�riode pour laquelle on traite les donn�es des mar�es
					if(bouton.getDate().compareTo(new Date(01,06,2021)) >= 0 && bouton.getDate().compareTo(new Date(30,9,2021)) <= 0 ) {
						//Si la date est dans la p�riode pour laquelle on traite les donn�es heure par heure
						if(dateCourante.compareTo(new Date(27,06,2021)) < 0) {
							//Si on a des donn�es heure par heure pour le port s�l�ctionn�
							if(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")) != null){
								//On change le mod�le du tableau heure par heure pour le mod�le correspondant
								panelAffichage.setTableModel(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")).getHauteursDeMerUnPort().get(dateCourante));
							}
							//Si on a pas de donn�es heure par heure pour le port s�l�ctionn�
							else {
								//On change le mod�le du tableau pour un mod�le nul, qui affichera les horaires et l'absence d'informations pour la p�riode
								panelAffichage.setTableModel(null);
							}
						}
						//Si la date n'est pas dans la p�riode pour laquelle on traite les donn�es heure par heure
						else {
							//On change le mod�le du tableau pour un mod�le nul, qui affichera les horaires et l'absence d'informations pour la p�riode
							panelAffichage.setTableModel(null);
						}
						
						//On met � jour l'affichage haut-central avec le nom du port s�lectionn�, la date s�l�ctionn�e
						panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
						
						//On d�finit les mar�es du jour en les lisant dans le fichier s�rialis� correspondant.
						MareesUnJour mareesCourantes = LectureFichierSer.lecture(new File("data_ports//datas_ser//" + port.replaceAll(" ","_") + ".ser")).getMareesUnJourDuPort().get(new Date(dateCourante.getJour(),dateCourante.getMois(),dateCourante.getAnnee()));
						
						//ArrayList de mar�es dans laquelle on va mettre les mar�es dans l'ordre pleines mers puis basses mers
						ArrayList<Maree> mareeList = new ArrayList<Maree>();
	
						//Vide de la liste de mar�es, par s�curit�
						mareeList.clear();
						
						//Pour chaque pleine mer lue dans le fichier s�rialis�
						for(Maree maree : mareesCourantes.getHautesMers()) {
							//On l'ajoute � la suite de l'ArrayList cr��e
							mareeList.add(maree);
						}
						
						//Pour chaque basse mer lue dans le fichier s�rialis�
						for(Maree maree : mareesCourantes.getBassesMers()) {
							//On l'ajoute � la suite de l'ArrayList cr��e
							mareeList.add(maree);
						}
						
						//D�finition d'une variable de boucle
						int compteur = 0;
						
						//Pour chaque mar�e lue
						for(Maree maree : mareeList){
							//Une mar�e aura une hauteur n�gative si elle n'existe pas dans les fichiers txt 
							//Pour le traitement plus simple dans les objets mar�es
							//On transcrit litt�ralement � l'�criture "---" pour 999 puis le constructeur de la mar�e s'occupe de transcrire le 999 en -1 (raccourcissement possible)
							if(maree.getHauteur() == -1) {
								//On affiche le panel d'affichage
								panelAffichage.getCards().setVisible(true);
								//On modifie ses informations pour qu'il soit lisible dans l'interface que les donn�es ne sont pas disponibles pour cette mar�e
								panelAffichage.getLabelsCoefficient()[compteur].setText("--");
								panelAffichage.getLabelsHauteur()[compteur].setText("---");
								panelAffichage.getLabelsHoraire()[compteur].setText("--:--");
							}
							//Cependant si la mar�e existe bien (dans les fichiers txt)
							else {
								//On affiche le panel d'affichage
								panelAffichage.getCards().setVisible(true);
								//On affiche les informations correspondantes � la mar�e en question
								panelAffichage.getLabelsCoefficient()[compteur].setText(Integer.toString(maree.getCoef()));
								panelAffichage.getLabelsHauteur()[compteur].setText(Double.toString(maree.getHauteur()));
								panelAffichage.getLabelsHoraire()[compteur].setText(maree.getHoraire());
							}
							//On incr�mente la variable de boucle
							compteur++;
						}
					}
					//Si la date courante se situe en dehors des bornes des dates trait�es pour les mar�es
					else {
						//On d�finit la date courante comme date selectionn�e
						dateCourante = bouton.getDate();
						//S'il existe des donn�es heure par heure pour le port s�lectionn�
						if(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser"))!=null)
							//On les affiche dans le mod�le de la table pr�vu � cet effet
							panelAffichage.setTableModel(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")).getHauteursDeMerUnPort().get(dateCourante));
					    //Sinon
						else
							//On affiche une table nulle (qui contient les informations d'absence d'informations
							panelAffichage.setTableModel(null);
							
						//On met � jour le label qui affiche la date courante et le port courant
						panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
						
						//Pour chacune des mar�es On affiche qu'il n'y a pas d'informations
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
		
		//Cette boucle se d�compose de la m�me fa�on que la pr�c�dente
		//On v�rifie les dates puis on traite les donn�es heure par heure si elles existent, pareils pour les donn�es relatives aux mar�es
		for(int i = 0; i <  panelChoix.getBoutonsPort().length ; i++) {
			if(e.getSource() == panelChoix.getBoutonsPort()[i]) {
				port = panelChoix.getBoutonsPort()[i].getPort();
				if(dateCourante.compareTo(new Date(01,06,2021)) >= 0 && dateCourante.compareTo(new Date(30,9,2021)) <= 0 ) {
					if(dateCourante.compareTo(new Date(27,06,2021)) < 0) {
						if(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")) != null){
							panelAffichage.setTableModel(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")).getHauteursDeMerUnPort().get(dateCourante));
						}
						else {
							panelAffichage.setTableModel(null);
						}
					}
					else {
						panelAffichage.setTableModel(null);
					}
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
					if(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser"))!=null)
						panelAffichage.setTableModel(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")).getHauteursDeMerUnPort().get(dateCourante));

					else
						panelAffichage.setTableModel(null);
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
		
		//Si jamais l'action provient du bouton qui doit afficher les mar�es
		if(e.getSource() == panelAffichage.getBoutonMaree()) {
			//On affiche les mar�es
			panelAffichage.getCardLayout().first(panelAffichage.getCards());
		}	
		//Si jamais l'action provient du bouton qui doit afficher les hauteurs de mer heure par heure
		if(e.getSource() == panelAffichage.getBoutonTable()) {
			//On affiche les hauteurs de mer heure par heure
			panelAffichage.getCardLayout().show(panelAffichage.getCards(),"1");
		}
	}
	
	
	
	
}
