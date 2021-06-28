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
 * Classe du controleur de l'IHM, il reçoit les actions et les traite selon leur origine
 *
 */
public class controleur implements ActionListener{
	
	//On instancie les différents panels de l'IHM
	FenetreMere fenetreMere;
	PanelPrincipal panelPrincipal;
	PanelAffichage panelAffichage;
	PanelChoix panelChoix;
	PanelCalendrier panelCalendrier;
	
	//On définit les variables qui vont servir dans l'environnement
	Date dateCourante = new Date();
	File serFolder = new File("data_ports/datas_ser/");
	File[] serFiles = serFolder.listFiles();
	String port = serFiles[0].getName().split(".ser")[0];
	
	/**
	 * Constructeur du controleur
	 * @param fenetreMere objet de la classe FenetreMere qui hérite de la classe JFrame (fenetre graphique)
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
			//Modification du numéro de mois
			panelCalendrier.getPanelSud().setN(0);
			//Modification du label qui affiche le mois affiché dans le panelCalendrier
			panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
		}
		
		//Si on demande le mois précédent du panelCalendrier
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[1]) {
			//On affiche le panel précédent
			panelCalendrier.getPanelCentre().getCardManager().previous(panelCalendrier.getPanelCentre());			
			//Si on passe d'un mois "normal" à un autre (ex Mars -> Février)

			if(panelCalendrier.getPanelSud().getN() != 0) {
				//Modification du numéro de mois
				panelCalendrier.getPanelSud().setN(panelCalendrier.getPanelSud().getN()-1);
				//Modification du label qui affiche le mois affiché dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}

			//Sinon (le cas où on passe de Janvier à Décembre)
			else {
				//Modification du numéro de mois
				panelCalendrier.getPanelSud().setN(11);
				//Modification du label qui affiche le mois affiché dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
		}
		
		//Si on demande le mois suivant du panelCalendrier		
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[2]) {
			//On affiche le panel suivant
			panelCalendrier.getPanelCentre().getCardManager().next(panelCalendrier.getPanelCentre());
			//Si on passe d'un mois "normal" à un autre (ex Février -> Mars)
			if(panelCalendrier.getPanelSud().getN() != 11) {
				//Modification du numéro de mois
				panelCalendrier.getPanelSud().setN(panelCalendrier.getPanelSud().getN()+1);
				//Modification du label qui affiche le mois affiché dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}

			//Sinon (le cas où on passe de Décembre à Janvier)
			else {
				//Modification du numéro de mois
				panelCalendrier.getPanelSud().setN(0);
				//Modification du label qui affiche le mois affiché dans le panelCalendrier
				panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
			}
		}
		
		//Si on demande le dernier mois du panelCalendrier
		else if(e.getSource() == panelCalendrier.getPanelSud().getButtons()[3]) {
			//On affiche le dernier panel
			panelCalendrier.getPanelCentre().getCardManager().last(panelCalendrier.getPanelCentre());
			//Modification du numéro de mois
			panelCalendrier.getPanelSud().setN(ConstantesCalendrier.MOIS.length-1);
			//Modification du label qui affiche le mois affiché dans le panelCalendrier
			panelCalendrier.getPanelSud().getLabelMoisName().setText(ConstantesCalendrier.MOIS[panelCalendrier.getPanelSud().getN()]);
		}
		
		
		//Pour chaque panelMois
		for(PanelMois panelMois : panelCalendrier.getPanelCentre().getPanelsMois()) {
			//Pour chaque BoutonMois du panelMois
			for(BoutonDate bouton : panelMois.getBoutonsDate()) {
				//Si le bouton courant est à l'origine de l'événement
				if(e.getSource() == bouton) {
					//La date courante change pour la date de l'événement en cours
					dateCourante = bouton.getDate();
					//Si la date est dans la période pour laquelle on traite les données des marées
					if(bouton.getDate().compareTo(new Date(01,06,2021)) >= 0 && bouton.getDate().compareTo(new Date(30,9,2021)) <= 0 ) {
						//Si la date est dans la période pour laquelle on traite les données heure par heure
						if(dateCourante.compareTo(new Date(27,06,2021)) < 0) {
							//Si on a des données heure par heure pour le port séléctionné
							if(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")) != null){
								//On change le modèle du tableau heure par heure pour le modèle correspondant
								panelAffichage.setTableModel(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")).getHauteursDeMerUnPort().get(dateCourante));
							}
							//Si on a pas de données heure par heure pour le port séléctionné
							else {
								//On change le modèle du tableau pour un modèle nul, qui affichera les horaires et l'absence d'informations pour la période
								panelAffichage.setTableModel(null);
							}
						}
						//Si la date n'est pas dans la période pour laquelle on traite les données heure par heure
						else {
							//On change le modèle du tableau pour un modèle nul, qui affichera les horaires et l'absence d'informations pour la période
							panelAffichage.setTableModel(null);
						}
						
						//On met à jour l'affichage haut-central avec le nom du port sélectionné, la date séléctionnée
						panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
						
						//On définit les marées du jour en les lisant dans le fichier sérialisé correspondant.
						MareesUnJour mareesCourantes = LectureFichierSer.lecture(new File("data_ports//datas_ser//" + port.replaceAll(" ","_") + ".ser")).getMareesUnJourDuPort().get(new Date(dateCourante.getJour(),dateCourante.getMois(),dateCourante.getAnnee()));
						
						//ArrayList de marées dans laquelle on va mettre les marées dans l'ordre pleines mers puis basses mers
						ArrayList<Maree> mareeList = new ArrayList<Maree>();
	
						//Vide de la liste de marées, par sécurité
						mareeList.clear();
						
						//Pour chaque pleine mer lue dans le fichier sérialisé
						for(Maree maree : mareesCourantes.getHautesMers()) {
							//On l'ajoute à la suite de l'ArrayList créée
							mareeList.add(maree);
						}
						
						//Pour chaque basse mer lue dans le fichier sérialisé
						for(Maree maree : mareesCourantes.getBassesMers()) {
							//On l'ajoute à la suite de l'ArrayList créée
							mareeList.add(maree);
						}
						
						//Définition d'une variable de boucle
						int compteur = 0;
						
						//Pour chaque marée lue
						for(Maree maree : mareeList){
							//Une marée aura une hauteur négative si elle n'existe pas dans les fichiers txt 
							//Pour le traitement plus simple dans les objets marées
							//On transcrit littéralement à l'écriture "---" pour 999 puis le constructeur de la marée s'occupe de transcrire le 999 en -1 (raccourcissement possible)
							if(maree.getHauteur() == -1) {
								//On affiche le panel d'affichage
								panelAffichage.getCards().setVisible(true);
								//On modifie ses informations pour qu'il soit lisible dans l'interface que les données ne sont pas disponibles pour cette marée
								panelAffichage.getLabelsCoefficient()[compteur].setText("--");
								panelAffichage.getLabelsHauteur()[compteur].setText("---");
								panelAffichage.getLabelsHoraire()[compteur].setText("--:--");
							}
							//Cependant si la marée existe bien (dans les fichiers txt)
							else {
								//On affiche le panel d'affichage
								panelAffichage.getCards().setVisible(true);
								//On affiche les informations correspondantes à la marée en question
								panelAffichage.getLabelsCoefficient()[compteur].setText(Integer.toString(maree.getCoef()));
								panelAffichage.getLabelsHauteur()[compteur].setText(Double.toString(maree.getHauteur()));
								panelAffichage.getLabelsHoraire()[compteur].setText(maree.getHoraire());
							}
							//On incrémente la variable de boucle
							compteur++;
						}
					}
					//Si la date courante se situe en dehors des bornes des dates traitées pour les marées
					else {
						//On définit la date courante comme date selectionnée
						dateCourante = bouton.getDate();
						//S'il existe des données heure par heure pour le port sélectionné
						if(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser"))!=null)
							//On les affiche dans le modèle de la table prévu à cet effet
							panelAffichage.setTableModel(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//"+port+".ser")).getHauteursDeMerUnPort().get(dateCourante));
					    //Sinon
						else
							//On affiche une table nulle (qui contient les informations d'absence d'informations
							panelAffichage.setTableModel(null);
							
						//On met à jour le label qui affiche la date courante et le port courant
						panelAffichage.getAffichageLabel().setText(port + " le " + dateCourante.toString());
						
						//Pour chacune des marées On affiche qu'il n'y a pas d'informations
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
		
		//Cette boucle se décompose de la même façon que la précédente
		//On vérifie les dates puis on traite les données heure par heure si elles existent, pareils pour les données relatives aux marées
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
		
		//Si jamais l'action provient du bouton qui doit afficher les marées
		if(e.getSource() == panelAffichage.getBoutonMaree()) {
			//On affiche les marées
			panelAffichage.getCardLayout().first(panelAffichage.getCards());
		}	
		//Si jamais l'action provient du bouton qui doit afficher les hauteurs de mer heure par heure
		if(e.getSource() == panelAffichage.getBoutonTable()) {
			//On affiche les hauteurs de mer heure par heure
			panelAffichage.getCardLayout().show(panelAffichage.getCards(),"1");
		}
	}
	
	
	
	
}
