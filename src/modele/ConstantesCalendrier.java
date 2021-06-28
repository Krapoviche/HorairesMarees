package modele;

import java.awt.Color;


/**
 * Interface qui contient les diff�rentes variables (String) qui servent � l'instanciation des objets que contient un panelCalendrier
 * Cette interface � �t� cr��e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
 *
 */

public interface ConstantesCalendrier {
	
	final String [] JOURS_SEMAINE = {"lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"} ; 
	
	final String [] JOURS_SEMAINE_ABR = {"lu","ma","me","je","ve","sa","di"} ;  
	
	final  String [] MOIS = {"janvier", "f�vrier","mars","avril","mai","juin","juillet", "ao�t","septembre","octobre","novembre","d�cembre"};
	
	final String INTIT_PREMIER = "<<";
	final String INTIT_PRECEDENT = "<";
	final String INTIT_SUIVANT = ">";
	final String INTIT_DERNIER = ">>";
	final  String [] INTITULES_BOUTONS = {INTIT_PREMIER,INTIT_PRECEDENT, INTIT_SUIVANT, INTIT_DERNIER};
	
	final String INTITULE_BOUTON_AJOUT ="+";
	
	public final  String [] HEURES = {"00", "01", "02", "03", "04", "05", "06",
		"07", "08", "09", "10", "11", "12", "13",
		"14", "15", "16", "17", "18", "19", "20",
		"21", "22", "23"};
	public final  String [] MINUTES = {"00", "15", "30", "45"};
	
	 
	
}
