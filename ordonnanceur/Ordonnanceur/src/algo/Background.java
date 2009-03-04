package algo;

import java.util.LinkedList;
import java.util.PriorityQueue;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class Background implements Algorithme{
	private Algorithme algo;
	private PriorityQueue<PrioBackground> aperiodique;
	private LinkedList<UniteTemps> ordonnancement;
	private Tache aperiodiqueEnCours;
	private int tempsRestant; //pour la tache apériodique en cours
	//private int ppcm;
	public Background(Algorithme algo) {
		//this.ppcm = ppcm;
		this.aperiodique = new PriorityQueue<PrioBackground>();
		this.algo =algo;
	}
	/*public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques,
			ListeTaches tachesAperiodiques) {
		//PriorityQueue<PrioBackground> fileAttente = new PriorityQueue<PrioBackground>();
		ListeTaches periodique = new ListeTaches();
		Tache aperiodiqueEnCours = null;
		int tempsRestant = 0; //pour la tache apériodique en cours
		for(Tache t : tachesAperiodiques) {
			this.aperiodique.add(new PrioBackground((Aperiodique) t));
		}
		RM rm = new RM(this.ppcm);
		this.ordonnancement = rm.executer(tachesPeriodiques,null);
		for(UniteTemps u : this.ordonnancement) {
			if(u.getIdTache() == 0) { // si un temps creux
				if(aperiodiqueEnCours == null) { //si aucune tache apériodique n'a été mise en standby
					if(this.aperiodique.peek() != null && this.aperiodique.peek().getTache().getR() <= u.getIdUnite()) { //et si des taches apériodique sont en attente
						aperiodiqueEnCours = this.aperiodique.poll().getTache();
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
	}*/
	@Override
	public void initialiser(LinkedList<UniteTemps> ordonnancement,
			ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques) {
		this.aperiodiqueEnCours = null;
		this.tempsRestant = 0;
		
		this.algo.initialiser(ordonnancement, tachesPeriodiques, tachesAperiodiques);
		for(Tache t : tachesAperiodiques) {
			this.aperiodique.add(new PrioBackground((Aperiodique) t));
		}
		
	}
	@Override
	public UniteTemps uniteSuivante() {
		UniteTemps uniteCourante = this.algo.uniteSuivante();
		if(uniteCourante.getTache() == null) { // si un temps creux
			if(aperiodiqueEnCours == null) { //si aucune tache apériodique n'a été mise en standby
				if(this.aperiodique.peek() != null && this.aperiodique.peek().getTache().getR() <= uniteCourante.getIdUnite()) { //et si des taches apériodique sont en attente
					aperiodiqueEnCours = this.aperiodique.poll().getTache();
					tempsRestant = aperiodiqueEnCours.getC();
				}
			}
			if(aperiodiqueEnCours != null) { // si une tache apériodique à déjà été en partie executée ou vient d'être lancée
				uniteCourante.setTache(aperiodiqueEnCours);
				if(--tempsRestant == 0) //si la tache est terminée on la retire
					aperiodiqueEnCours = null;
			}
			
		}
		return uniteCourante;
	}
	public boolean ordonnancable(double up, double us,int n) {

		return this.algo.ordonnancable(up, us, n);
	}

}
