package algo;

import java.util.LinkedList;

import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class Background implements Algorithme{
	private ListeTaches aperiodique;
	private LinkedList<UniteTemps> ordonnancement;
	private int ppcm;
	public Background(int ppcm) {
		
		this.ppcm = ppcm;
		this.aperiodique = new ListeTaches();
	}
	public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques)
	{
		
		
		
		ListeTaches periodique = new ListeTaches();
		Tache aperiodiqueEnCours = null;
		int tempsRestant = 0; //pour la tache apériodique en cours
		for(Tache t : tachesPeriodiques) {
			if(t instanceof Periodique) {
				periodique.add(t);
			}
			else
				this.aperiodique.add(t);
		}
		RM rm = new RM(this.ppcm);
		this.ordonnancement = rm.executer(periodique, null);
		for(UniteTemps u : this.ordonnancement) {
			if(u.getIdTache() == 0) { // si un temps creux
				if(aperiodiqueEnCours == null) { //si aucune tache apériodique n'a été mise en standby
					if(this.aperiodique.peek() != null) { //et si des taches apériodique sont en attente
						aperiodiqueEnCours = this.aperiodique.poll();
						tempsRestant = aperiodiqueEnCours.getC();
					}
				}
				if(aperiodiqueEnCours != null) { // si une tache apériodique à déjà été en partie executée ou vient d'être lancée
					u.setIdTache(aperiodiqueEnCours.getId());
					if(--tempsRestant == 0) //si la tache est terminée on la retire
						aperiodiqueEnCours = null;
				}
				
			}
		}
		
		return this.ordonnancement;
	}
}
