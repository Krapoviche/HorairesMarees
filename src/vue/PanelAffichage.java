package vue;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import controleur.controleur;
import modele.ModeleMareeTable;


public class PanelAffichage extends JPanel {

	JLabel affichage = new JLabel("Choisissez un port");
	JPanel panelAffichage = new JPanel();
	JTable tableMaree;
	CardLayout cl = new CardLayout();
	JPanel panelTable = new JPanel();
	JPanel panelMaree = new JPanel();
	JPanel panelGraphe = new JPanel();
	JPanel panelBoutons = new JPanel();
	JPanel cards = new JPanel(cl);

	JButton boutonTable = new JButton("Heure par heure");
	JButton boutonMaree = new JButton("Marées");
	JButton boutonGraphe = new JButton("Graphique");

	JLabel[] labelsCoefficient = new JLabel[4];
	JLabel[] labelsHauteur = new JLabel[4];
	JLabel[] labelsHoraire = new JLabel[4];
	JLabel basseMer = new JLabel("Basses mers");
	JLabel pleineMer = new JLabel("Pleines mers");
	JPanel[] panelsMarees = new JPanel[4];
	
	ModeleMareeTable modele;

	public PanelAffichage() {
		setLayout(new BorderLayout(0, 0));

		panelMaree.setLayout(new GridBagLayout());

		this.setBackground(new Color(135, 206, 250));
		panelTable.setBackground(new Color(135, 206, 250));
		panelMaree.setBackground(new Color(135, 206, 250));
		panelGraphe.setBackground(new Color(135, 206, 250));
		panelAffichage.setBackground(new Color(3, 34, 76));
		affichage.setForeground(new Color(255, 200, 120));
		panelBoutons.setBackground(new Color(3, 34, 76));
		boutonTable.setBackground(new Color(255, 200, 120));
		boutonMaree.setBackground(new Color(255, 200, 120));
		boutonGraphe.setBackground(new Color(255, 200, 120));
		basseMer.setFont(new Font("Arial", Font.BOLD, 20));
		pleineMer.setFont(new Font("Arial", Font.BOLD, 20));

		GridBagConstraints contraintesMarees = new GridBagConstraints();
		GridBagConstraints contraintesPanelsMarees = new GridBagConstraints();

		contraintesMarees.fill = GridBagConstraints.BOTH;

		contraintesMarees.gridx = 2;

		contraintesMarees.gridy = 0;
		panelMaree.add(pleineMer, contraintesMarees);

		contraintesMarees.gridx = 1;
		contraintesMarees.gridy += 2;

		for (int i = 0; i < 2; i++) {
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

			panelsMarees[i].setFont(new Font("Arial", Font.BOLD, 20));
			labelsCoefficient[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHauteur[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHoraire[i].setFont(new Font("Arial", Font.BOLD, 16));
		}

		contraintesPanelsMarees.gridx = 0;
		contraintesPanelsMarees.gridy = 1;
		contraintesMarees.gridy += 1;

		contraintesMarees.gridx = 2;
		panelMaree.add(basseMer, contraintesMarees);
		contraintesMarees.gridx = 1;
		contraintesMarees.gridy += 2;

		for (int i = 2; i < 4; i++) {
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

			panelsMarees[i].setFont(new Font("Arial", Font.BOLD, 20));
			labelsCoefficient[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHauteur[i].setFont(new Font("Arial", Font.BOLD, 16));
			labelsHoraire[i].setFont(new Font("Arial", Font.BOLD, 16));
		}

		
		 tableMaree = new JTable();
		 modele = new ModeleMareeTable();
		 tableMaree.setModel(modele);
		 panelTable.add(tableMaree);
		 
		 
		

		panelAffichage.setLayout(new GridBagLayout());
		panelAffichage.add(affichage);
		add(panelAffichage, BorderLayout.NORTH);
		boutonMaree.setMnemonic('M');
		boutonTable.setMnemonic('H');
		boutonGraphe.setMnemonic('G');
		panelBoutons.add(boutonMaree);
		panelBoutons.add(boutonTable);
		panelBoutons.add(boutonGraphe);
		cards.add(panelMaree, "0");
		cards.add(panelTable, "1");
		cards.add(panelGraphe, "2");
		cards.setVisible(false);
		add(cards, BorderLayout.CENTER);
		add(panelBoutons, BorderLayout.SOUTH);
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
	
	public JButton getBoutonGraphique() {
		return boutonGraphe;
	}

	public CardLayout getCardLayout() {
		return cl;
	}

	public JPanel getCards() {
		return cards;
	}

	public void recordListener(controleur c) {
		boutonTable.addActionListener(c);
		boutonMaree.addActionListener(c);
		boutonGraphe.addActionListener(c);
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
