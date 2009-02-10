package algo;

import java.util.LinkedList;
import java.util.PriorityQueue;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class Background implements Algorithme{
	private PriorityQueue<PrioBackground> aperiodique;
	private LinkedList<UniteTemps> ordonnancement;
	private int ppcm;
	public Background(int ppcm) {
		this.ppcm = ppcm;
		this.aperiodique = new PriorityQueue<PrioBackground>();
	}
	public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques,
			ListeTaches tachesAperiodiques) {
		//PriorityQueue<PrioBackground> fileAttente = new PriorityQueue<PrioBackground>();
		ListeTaches periodique = new ListeTaches();
		Tache aperiodiqueEnCours = null;
		int tempsRestant = 0; //pour la tache ap�riodique en cours
		for(Tache t : tachesAperiodiques) {
			this.aperiodique.add(new PrioBackground((Aperiodique) t));
		}
		RM rm = new RM(this.ppcm);
		this.ordonnancement = rm.executer(tachesPeriodiques,null);
		for(UniteTemps u : this.ordonnancement) {
			if(u.getIdTache() == 0) { // si un temps creux
				if(aperiodiqueEnCours == null) { //si aucune tache ap�riodique n'a �t� mise en standby
					if(this.aperiodique.peek() != null && this.aperiodique.peek().getTache().getR() <= u.getIdUnite()) { //et si des taches ap�riodique sont en attente
						aperiodiqueEnCours = this.aperiodique.poll().getTache();
						tempsRestant = aperiodiqueEnCours.getC();
					}
				}
				if(aperiodiqueEnCours != null) { // si une tache ap�riodique � d�j� �t� en partie execut�e ou vient d'�tre lanc�e
					u.setIdTache(aperiodiqueEnCours.getId());
					if(--tempsRestant == 0) //si la tache est termin�e on la retire
						aperiodiqueEnCours = null;
				}
				
			}
		}
		
		return this.ordonnancement;
	}

}
