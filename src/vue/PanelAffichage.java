package vue;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import controleur.controleur;
import modele.Date;
import modele.HauteursDeMerUnJour;
import modele.ModeleMareeTable;
import outils.LectureFichierSer;

/**
 * Classe qui permet d'instancier le panneau qui se situe au milieu du programme, elle g�re l'affichage des mar�es et des donn�es heure par heure, on peut imaginer qu'elle aurait �galement g�r� un graphique potentiel s'il y en avait un.
 *
 */
public class PanelAffichage extends JPanel {

	//Instanciation des diff�rents composants de l'espace affich�
	
	//Message informatif : sera chang� plus tard pour afficher le port s�l�ctionn� ainsi que la date courante
	JLabel affichage = new JLabel("Choisissez un port");
	JPanel panelAffichage = new JPanel();//Ce panel n'est pas un PanelAffichage, c'est simplement un panel qui est l� pour contenir le label Affichage qu'on a instanci� pr�c�demment
	
	//D�claration de la JTable qui affiche les donn�es des hauteurs de mer heure par heure et de son mod�le
	JTable tableMaree;
	ModeleMareeTable modele;

	//Instanciation des Panels d'affichage des diff�rents �l�ments de cette partie de l'interface et mise en cardLayout
	CardLayout cl = new CardLayout();
	JPanel panelTable = new JPanel();
	JPanel panelMaree = new JPanel();
	JPanel cards = new JPanel(cl);
	
	//Instanciation du Panel qui contiendra les boutons qui permettent de changer de vues dans le cardLayout
	JPanel panelBoutons = new JPanel();

	//Instanciation des deux boutons pr�vus � cet effet
	JButton boutonTable = new JButton("Heure par heure");
	JButton boutonMaree = new JButton("Mar�es");

	//Instanciation des tableaux de labels qui contiendront les informations sur les mar�es et leurs conteneurs
	JPanel[] panelsMarees = new JPanel[4];
	JLabel[] labelsCoefficient = new JLabel[4];
	JLabel[] labelsHauteur = new JLabel[4];
	JLabel[] labelsHoraire = new JLabel[4];
	
	//Instanciation des labels informatifs
	JLabel basseMer = new JLabel("Basses mers");
	JLabel pleineMer = new JLabel("Pleines mers");

	
	
	/**
	 * Constructeur d'un PanelAffichage, prend en charge la g�n�ration de tous les effets � l'�cran.
	 */
	public PanelAffichage() {
		//D�finition de la strat�gie de layout
		setLayout(new BorderLayout(0, 0));


		//D�finition de la strat�gie de layout pour le panel qui affiche les mar�es
		panelMaree.setLayout(new GridBagLayout());

		//D�finition de la couleur de fond pour l'objet en lui m�me, le panel de la table (heure par heure), le panel des mar�es et le panel qui affiche les informations (en haut au milieu)
		this.setBackground(new Color(135, 206, 250));
		panelTable.setBackground(new Color(135, 206, 250));
		panelMaree.setBackground(new Color(135, 206, 250));
		panelAffichage.setBackground(new Color(3, 34, 76));
		
		//D�finition de la couleur de la police pour l'affichage du haut
		affichage.setForeground(new Color(255, 200, 120));
		
		//D�finition de la couleur de fond pour le panel qui contient les boutons et pour les deux boutons
		panelBoutons.setBackground(new Color(3, 34, 76));
		boutonTable.setBackground(new Color(255, 200, 120));
		boutonMaree.setBackground(new Color(255, 200, 120));
		
		//D�finition des polices d'�criture pour les labels d'information (au centre)
		basseMer.setFont(new Font("Arial", Font.BOLD, 20));
		pleineMer.setFont(new Font("Arial", Font.BOLD, 20));

		//D�finition des contraintes gridBag (pour les conteneurs d'affichage de marees et pour le conteneur d'affichage des conteneurs de marees)
		GridBagConstraints contraintesMarees = new GridBagConstraints();
		GridBagConstraints contraintesPanelsMarees = new GridBagConstraints();
		contraintesMarees.fill = GridBagConstraints.BOTH;
		contraintesMarees.gridx = 2;
		contraintesMarees.gridy = 0;
		
		//Ajout du label "pleine mer" au panel maree (premi�re ligne de ce dernier)
		panelMaree.add(pleineMer, contraintesMarees);

		contraintesMarees.gridx = 1;
		contraintesMarees.gridy += 2;

		//Pour les deux pleines mer
		for (int i = 0; i < 2; i++) {
		
			//On va ajouter les �l�ments graphiques dans l'ordre, en les d�finissants au pr�alable
			
			contraintesPanelsMarees.gridy = 1;
			panelsMarees[i] = new JPanel();
			panelsMarees[i].setBackground(new Color(135, 206, 250));
			panelsMarees[i].setLayout(new GridBagLayout());
			labelsCoefficient[i] = new JLabel();
			labelsHauteur[i] = new JLabel();
			labelsHoraire[i] = new JLabel();
			panelsMarees[i].add(new JLabel("Coefficient : "), contraintesPanelsMarees);
			contraintesPanelsMarees.gridx += 2;
			panelsMarees[i].add(labelsCoefficient[i], contraintesPanelsMarees);
			contraintesPanelsMarees.gridy += 1;
			contraintesPanelsMarees.gridx -= 2;
			panelsMarees[i].add(new JLabel("Heure : "), contraintesPanelsMarees);
			contraintesPanelsMarees.gridx += 2;
			panelsMarees[i].add(labelsHoraire[i], contraintesPanelsMarees);
			contraintesPanelsMarees.gridy += 1;
			contraintesPanelsMarees.gridx -= 2;
			panelsMarees[i].add(new JLabel("Hauteur : "), contraintesPanelsMarees);
			contraintesPanelsMarees.gridx += 2;
			panelsMarees[i].add(labelsHauteur[i], contraintesPanelsMarees);

			panelMaree.add(panelsMarees[i], contraintesMarees);
			contraintesMarees.gridx += 2;

			
			//On d�finit les polices d'�criture
			panelsMarees[i].setFont(new Font("Arial", Font.BOLD, 20));
			labelsCoefficient[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHauteur[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHoraire[i].setFont(new Font("Arial", Font.BOLD, 16));
		}

		
		contraintesPanelsMarees.gridx = 0;
		contraintesPanelsMarees.gridy = 1;
		
		contraintesMarees.gridy += 1;
		contraintesMarees.gridx = 2;
		
		//Ajout du label "basse mer" au panel maree 
		panelMaree.add(basseMer, contraintesMarees);
		
		contraintesMarees.gridx = 1;
		contraintesMarees.gridy += 2;

		//Pour chaque basse mer
		for (int i = 2; i < 4; i++) {
		
			//On va ajouter les �l�ments graphiques dans l'ordre, en les d�finissants au pr�alable
			
			contraintesPanelsMarees.gridy = 1;
			panelsMarees[i] = new JPanel();
			panelsMarees[i].setLayout(new GridBagLayout());
			panelsMarees[i].setBackground(new Color(135, 206, 250));
			labelsCoefficient[i] = new JLabel();
			labelsHauteur[i] = new JLabel();
			labelsHoraire[i] = new JLabel();
			panelsMarees[i].add(new JLabel("Heure : "), contraintesPanelsMarees);
			contraintesPanelsMarees.gridx += 2;
			panelsMarees[i].add(labelsHoraire[i], contraintesPanelsMarees);
			contraintesPanelsMarees.gridy += 1;
			contraintesPanelsMarees.gridx -= 2;
			panelsMarees[i].add(new JLabel("Hauteur : "), contraintesPanelsMarees);
			contraintesPanelsMarees.gridx += 2;
			panelsMarees[i].add(labelsHauteur[i], contraintesPanelsMarees);

			panelMaree.add(panelsMarees[i], contraintesMarees);
			contraintesMarees.gridx += 2;


			//On d�finit les polices d'�criture
			panelsMarees[i].setFont(new Font("Arial", Font.BOLD, 20));
			labelsCoefficient[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHauteur[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHoraire[i].setFont(new Font("Arial", Font.BOLD, 16));
		}

		//On d�finit le layout pour le panneau qui contient la table heure par heure
		panelTable.setLayout(new BorderLayout(0,0));

		//On instancie la table
		tableMaree = new JTable();
		 
		//On istancie son mod�le puis le d�finit comme tel
		modele = new ModeleMareeTable(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//DUNKERQUE.ser")).getHauteursDeMerUnPort().get(new Date(1,1,2020)));
		tableMaree.setModel(modele);
		 
		//Param�trage de la table et notamment d�finition de son renderer
		tableMaree.setRowHeight(30);
		panelTable.add(new JScrollPane(tableMaree,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED) ,BorderLayout.CENTER);
		tableMaree.setDefaultRenderer(String.class, new CelluleRenderer()) ;
		tableMaree.setDefaultRenderer(Double.class, new CelluleRenderer()) ;
		 
		
		//On d�finit le layout pour le panel du haut puis on lui ajoute le label d'information
		panelAffichage.setLayout(new GridBagLayout());
		panelAffichage.add(affichage);
		
		//On ajoute le panel au conteneur global
		add(panelAffichage, BorderLayout.NORTH);
		
		//D�finition des Mnemoniques pour les deux boutons qui permettent de changer de vue
		boutonMaree.setMnemonic('M');
		boutonTable.setMnemonic('H');

		//Ajout des deux boutons au panel qui les contient
		panelBoutons.add(boutonMaree);
		panelBoutons.add(boutonTable);
		
		//Ajout des deux paneaux correspondant aux deux vues diff�rentes � leur conteneur en CardLayout
		cards.add(panelMaree, "0");
		cards.add(panelTable, "1");
		cards.setVisible(false);
		
		//On ajoute ces deux conteneurs globaux au paneau principal
		add(cards, BorderLayout.CENTER);
		add(panelBoutons, BorderLayout.SOUTH);
	}
	
	/**
	 * M�thode qui permet d'�couter les actions et de les envoyer au controleur
	 * @param c Le controleur
	 */
	public void recordListener(controleur c) {
		boutonTable.addActionListener(c);
		boutonMaree.addActionListener(c);
	}
	
	/**
	 * M�thode qui permet de mettre � jour le mod�le de la table heure par heure avec un objet HauteursDeMerUnJour
	 * @param hauteursDeMerUnJour L'objet compilant les donn�es � afficher.
	 */
	public void setTableModel(HauteursDeMerUnJour hauteursDeMerUnJour) {
		modele = new ModeleMareeTable(hauteursDeMerUnJour);
		tableMaree.setModel(modele);
	}

	public JLabel getAffichageLabel() {
		return affichage;
	}

	public JPanel getPanelMaree() {
		return panelMaree;
	}

	public JPanel getPanelTable() {
		return panelTable;
	}

	public JButton getBoutonTable() {
		return boutonTable;
	}

	public JButton getBoutonMaree() {
		return boutonMaree;
	}
	

	public CardLayout getCardLayout() {
		return cl;
	}

	public JPanel getCards() {
		return cards;
	}


	public JPanel[] getPanelsMaree() {
		return panelsMarees;
	}

	public JLabel[] getLabelsCoefficient() {
		return labelsCoefficient;
	}

	public JLabel[] getLabelsHauteur() {
		return labelsHauteur;
	}

	public JLabel[] getLabelsHoraire() {
		return labelsHoraire;
	}
	


}
