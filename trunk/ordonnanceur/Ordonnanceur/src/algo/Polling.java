package algo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class Polling implements Algorithme{
	
	//private int[] ordonnancement;
	private LinkedList<UniteTemps> ordonnancement;
	//private boolean[][] periodes;
	private ListeTaches periodiques;
	private ListeTaches aperiodiques;
	private int ppcm;
	private HashMap<Tache,Integer> mapTacheUnitesRestantes;
	private int capaciteServer;
	private Tache tachePS;
	public Polling(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
		this.periodiques = null;
		this.aperiodiques = null;
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
	}
	
	/**
	 * Initialise la liste des d�buts de p�riode des t�ches
	 */
	private void calculePeriodes()
	{
		
		int periodeTemp, periodeTache;
		Tache tacheTemp = null;
		for(Tache t : this.periodiques)
		
		{
			periodeTemp = 0;
			tacheTemp = t;
			periodeTache = ((Periodique)tacheTemp).getP();
			while(periodeTemp < this.ppcm)
			{
				this.ordonnancement.get(this.ordonnancement.indexOf(new UniteTemps(periodeTemp))).ajouterPeriode(tacheTemp);
				//this.periodes[periodeTemp-1][tacheTemp.getId()] = true;
				periodeTemp += periodeTache;
			}
		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
		}
	}
	public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques)
	{
		
		
		this.periodiques = tachesPeriodiques;
		this.aperiodiques = tachesAperiodiques;
		this.tachePS = this.periodiques.getLast();
		Periodique tacheEnCours = null;
		int unitesRestantes = 0; //pour la tache en cours
		PriorityQueue<PrioRM> enAttente = new PriorityQueue<PrioRM>(); //taches en attentes
		LinkedList<Tache> aperiodiquesEnAttente = new LinkedList<Tache>(); //taches ap�riodiques en attentes
		this.calculePeriodes();
		
		for(UniteTemps uniteCourante : this.ordonnancement)
		{
			//si aucune tache ap�riodique en attente, on remet a 0 la capacit� du serveur
			if(aperiodiquesEnAttente.size() == 0)
				this.capaciteServer = 0;
			else
				this.capaciteServer--;
			//on regarde si une demande de t�che ap�riodique intervient pour cette unit� courante
			for(Tache tApe: this.aperiodiques ) {
				
				//si c'est le cas on l'ajoute dans la liste d'attente
				if(((Aperiodique)tApe).getR() == uniteCourante.getIdUnite()) {
					aperiodiquesEnAttente.addLast(tApe);
				}
			}
			
			//on parcours la liste des d�buts de p�riodes
			for(Tache t :uniteCourante.getPeriodes()){
				//si la tache t est la tache PS, on charge le serveur
				if(t.equals(this.tachePS)) {
					this.capaciteServer = this.tachePS.getC();
				}
				enAttente.add(new PrioRM(((Periodique)t)));
				
				if(this.mapTacheUnitesRestantes.get(t)>0) System.out.println("erreur impossible de finir la tache : "+t.getId());
				else mapTacheUnitesRestantes.put(t,t.getC());
			}
			
			// Si une tache en cours doit etre stopp� car r�veil d'une tache prio
			if(tacheEnCours != null){
				Periodique tPrio = enAttente.peek().getTache();
				if(tPrio != null){
					
					if(tacheEnCours.getP() > tPrio.getP()){
						tacheEnCours = tPrio;
					}
				}
			}
			
			//si aucune tache est en cours d'execution on en selectionne une dans la file d'attente
			if(tacheEnCours == null) {
				if(enAttente.peek() != null) {// si des taches dans la file d'attente
					
					//S'il s'agit de la tache PS
					if(enAttente.peek().getTache().equals(this.tachePS)) {
						//si serveur charg�
						if(this.capaciteServer > 0) {
							//si tache ap� en attente
							if(aperiodiquesEnAttente.size() > 0) {
								tacheEnCours = (Periodique)this.tachePS;
								this.mapTacheUnitesRestantes.put(this.tachePS,aperiodiquesEnAttente.getFirst().getC());
								System.out.println("C ap�rio : "+aperiodiquesEnAttente.getFirst().getC()+"unit� courante:"+uniteCourante);
									
							}
						}
						else
						{
							enAttente.remove();
						}
					}
					else {
						tacheEnCours = enAttente.peek().getTache();		
					}
				} else {
					uniteCourante.setIdTache(0);
					
				}
			}
			
			
			
			if(tacheEnCours != null) { //si une tache est en cours d'exe
				uniteCourante.setIdTache(tacheEnCours.getId());
				
				
				
				unitesRestantes = this.mapTacheUnitesRestantes.get(tacheEnCours)-1;
				this.mapTacheUnitesRestantes.put(tacheEnCours,unitesRestantes);
				if(unitesRestantes == 0) {//si c'�tait la derni�re unit� de temps
					if(tacheEnCours.equals(this.tachePS)) {
						
						System.out.println("suppression de : "+aperiodiquesEnAttente.getFirst().getId()+"unit� courante:"+uniteCourante);
						aperiodiquesEnAttente.removeFirst();
					}
						
					enAttente.remove(new PrioRM(tacheEnCours));
					tacheEnCours = null;
					
				}
			}
			
		}
		
		return this.ordonnancement;


	}
	

}
