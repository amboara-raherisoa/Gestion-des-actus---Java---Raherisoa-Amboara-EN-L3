package mainprogram;

import controleur.CtrlActu;
import controleur.CtrlRedacteur;
import controleur.CtrlReset;
import controleur.CtrlRubrique;
import modele.ModeleActu;
import modele.ModeleRedacteur;
import modele.ModeleRubrique;
import vue.Vue;

public class GestionActu {

	public static void main(String[] args) {
		
		ModeleRubrique modeleRubrique = new ModeleRubrique();
		ModeleRedacteur modeleRedacteur = new ModeleRedacteur();
		ModeleActu modeleActu = new ModeleActu(modeleRubrique, modeleRedacteur);
		
		CtrlRedacteur ctrlRedacteur = new CtrlRedacteur(modeleRedacteur);
		CtrlRubrique ctrlRubrique = new CtrlRubrique(modeleRubrique);
		CtrlActu ctrlActu = new CtrlActu(modeleActu, modeleRubrique, modeleRedacteur);
		
		CtrlReset ctrlReset = new CtrlReset(modeleRubrique, modeleRedacteur, modeleActu);
		
		Vue fenetre = new Vue(ctrlRubrique, ctrlRedacteur, ctrlActu, ctrlReset);
		
		modeleRubrique.addObserver(fenetre.getVueRubrique());
		modeleRedacteur.addObserver(fenetre.getVueRedacteur());
		modeleActu.addObserver(fenetre.getVueActu());
		
		modeleRubrique.chargerDonnees();
		modeleRedacteur.chargerDonnees();
		modeleActu.chargerDonnees();
		
		fenetre.setVisible(true);

	}

}
