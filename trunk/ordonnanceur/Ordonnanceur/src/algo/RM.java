package algo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class RM implements Algorithme{
	
	//private int[] ordonnancement;
	private List<UniteTemps> ordonnancement;
	//private boolean[][] periodes;
	private ListeTaches liste;
	private int ppcm;
	public RM(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
		this.liste = null;
	}
	
	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes()
	{
		Iterator<Tache> it = this.liste.iterator();
		int periodeTemp, periodeTache;
		Tache tacheTemp = null;
		while(it.hasNext())
		{
			periodeTemp = 0;
			tacheTemp = it.next();
			periodeTache = ((Periodique)tacheTemp).getP();
			while(periodeTemp < this.ppcm)
			{
				this.ordonnancement.get(this.ordonnancement.indexOf(new UniteTemps(periodeTemp))).ajouterPeriode(tacheTemp);
				//this.periodes[periodeTemp-1][tacheTemp.getId()] = true;
				periodeTemp += periodeTache;
			}
		}
	}
	public LinkedList<UniteTemps> executer(ListeTaches liste)
	{
		this.liste = liste;
		UniteTemps uniteCourante = null;
		Tache tacheEnCours = null;
		int unitesRestantes = 0; //pour la tache en cours
		PriorityQueue<PrioRM> enAttente = new PriorityQueue(); //taches en attentes
		this.calculePeriodes();
		Iterator<UniteTemps> it = this.ordonnancement.iterator();
		while(it.hasNext())
		{
			uniteCourante = it.next();
			Iterator<Tache> it2 = uniteCourante.getPeriodes().iterator();
			//on regarde si de nouvelles périodes de tache débutes, auquel cas on remplie la file a priorité
			while(it2.hasNext()) {
				enAttente.add(new PrioRM(((Periodique)it2.next())));
			}
			//si aucune tache est en cours d'execution on en selectionne une dans la file d'attente
			if(tacheEnCours == null) {
				if(enAttente.peek() != null) {// si des taches dans la file d'attente
					tacheEnCours = enAttente.poll().getTache();
					unitesRestantes = tacheEnCours.getC();
				}
			}
			if(tacheEnCours != null) { //si une tache est en cours d'exe
				uniteCourante.setIdTache(tacheEnCours.getId());
				unitesRestantes--;
				if(unitesRestantes == 0) {//si c'était la dernière unité de temps
					tacheEnCours = null;
				}
			}
			System.out.println(uniteCourante);
		}
		//this.periodes = new boolean[ppcm][this.liste.size()];
		return null;


	}
}
