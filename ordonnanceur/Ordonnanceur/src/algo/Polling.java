package algo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;
import noyau.TachePs;

public class Polling implements Algorithme{
	
	private Iterator<UniteTemps> iterateur;
	private LinkedList<UniteTemps> ordonnancement;
	
	private ListeTaches periodiques;
	private ListeTaches aperiodiques;
	private int ppcm;
	private HashMap<Tache,Integer> mapTacheUnitesRestantes;
	private int capaciteServer;
	private TachePs tachePS;
	
	private Periodique tacheEnCours = null;
	private int unitesRestantes = 0; //pour la tache en cours
	private PriorityQueue<PrioRM> enAttente; //taches en attentes
	private LinkedList<Tache> aperiodiquesEnAttente;
	
	private Algorithme algo;
	private UniteTemps uniteCourante;
	
	
	public Polling(int ppcm, Algorithme algo)
	{
		this.ppcm = ppcm;
		this.ordonnancement = new LinkedList<UniteTemps>();
		
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
		this.periodiques = null;
		this.aperiodiques = null;
		this.mapTacheUnitesRestantes = null;
		this.algo=algo;
		this.uniteCourante=null;
		
	
	}
	
	/**
	 * Initialise la map ac les taches
	 */
	private void initMap()
	{				
		for(Tache t : this.periodiques)		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
		for(Tache t : this.aperiodiques)		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
	
	}
	public UniteTemps uniteSuivante() 
	{
		
		this.uniteCourante = this.iterateur.next();
		
			
			//on regarde si une demande de tâche apériodique intervient pour cette unité courante
			for(Tache tApe: this.aperiodiques ) {
				
				//si c'est le cas on l'ajoute dans la liste d'attente
				if(((Aperiodique)tApe).getR() == uniteCourante.getIdUnite()) {
					aperiodiquesEnAttente.addLast(tApe);
					this.mapTacheUnitesRestantes.put(tApe, tApe.getC());
				}
			}
			
			// On recupere l'unite suiavnte de la periodique
			UniteTemps uniteAlgo = this.algo.uniteSuivante();
			Tache tacheAlgo = uniteAlgo.getTache();
			// Si la tache est une tahce ps
			if(tacheAlgo instanceof TachePs){
			
				if(aperiodiquesEnAttente.peek()!=null){
					this.capaciteServer = tacheAlgo.getC();
					this.capaciteServer--;
					//System.out.println("capacite :"+this.capaciteServer);
					// On recup une tache aperio
					Tache ape = aperiodiquesEnAttente.getFirst();
					
					int unitesRestantes = this.mapTacheUnitesRestantes.get(ape)-1;
					
					if(unitesRestantes==0) aperiodiquesEnAttente.remove(ape);
					this.mapTacheUnitesRestantes.put(ape,unitesRestantes);
					this.uniteCourante.setTache(ape);
				}
			} else {
				//System.out.println("dedansqq");
				
				this.capaciteServer = this.tachePS.getCapacite();
				
				if(tacheAlgo!=null){
					this.uniteCourante.setTache(tacheAlgo);
				}
				
				
				
			}
			
			/*
			
			//on parcours la liste des débuts de périodes
			for(Tache t :uniteCourante.getPeriodes()){
				//si la tache t est la tache PS, on charge le serveur
				enAttente.add(new PrioRM(((Periodique)t)));
				
				if(this.mapTacheUnitesRestantes.get(t)>0 && !t.equals(this.tachePS)) System.out.println("erreur impossible de finir la tache : "+t.getId() + " "+uniteCourante);
				else this.mapTacheUnitesRestantes.put(t,t.getC());
				if(t instanceof TachePs) {
					this.capaciteServer = this.tachePS.getC();
					this.mapTacheUnitesRestantes.put(t,0);
				}
			}
			
			// Si une tache en cours doit etre stoppé car réveil d'une tache prio
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
					if(enAttente.peek().getTache() instanceof TachePs) {
						//System.out.println("uc: "+uniteCourante);
						//si serveur chargé
						if(this.capaciteServer > 0) {
							//si tache apé en attente
							if(aperiodiquesEnAttente.size() > 0) {
								tacheEnCours = (Periodique)this.tachePS;
								//pour ne pas perdre le temps restant de la tache en suspend
								if(this.mapTacheUnitesRestantes.get(this.tachePS) == 0)
									this.mapTacheUnitesRestantes.put(this.tachePS,aperiodiquesEnAttente.getFirst().getC());
								this.capaciteServer--;
								//System.out.println("C apério : "+aperiodiquesEnAttente.getFirst().getC()+"unité courante:"+uniteCourante);
									
							}
							else
							{
								enAttente.remove();
								tacheEnCours = enAttente.peek().getTache();
							}
						}
						else
						{
							enAttente.remove();
							tacheEnCours = enAttente.peek().getTache();
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
				
				//s'il s'agit de la tache PS, c'est le numéro de la tache apériodique que l'on prend
				if(tacheEnCours.equals(this.tachePS)) {
					uniteCourante.setIdTache(aperiodiquesEnAttente.getFirst().getId());
				}
				else {
					uniteCourante.setIdTache(tacheEnCours.getId());
				}
				
				
				unitesRestantes = this.mapTacheUnitesRestantes.get(tacheEnCours)-1;
				//System.out.println("u restantes: "+unitesRestantes+"unite courante: "+uniteCourante);
				this.mapTacheUnitesRestantes.put(tacheEnCours,unitesRestantes);
				if(unitesRestantes == 0) {//si c'était la dernière unité de temps
					if(tacheEnCours.equals(this.tachePS)) {
						
						//System.out.println("suppression de : "+aperiodiquesEnAttente.getFirst().getId()+"unité courante:"+uniteCourante);
						aperiodiquesEnAttente.removeFirst();
					}
						
					enAttente.remove(new PrioRM(tacheEnCours));
					tacheEnCours = null;
					
				}
			}
			//si aucune tache apériodique en attente, on remet a 0 la capacité du serveur
			if(aperiodiquesEnAttente.size() == 0)
				this.capaciteServer = 0;
			//System.out.println("capa: "+this.capaciteServer+"unite courante: "+uniteCourante);
			*/
		
		
		return uniteCourante;


	}

	public int getCapaciteServer() {
		return capaciteServer;
	}

	@Override
	public void initialiser(LinkedList<UniteTemps> ordonnancement,
			ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques) {
	
		this.ordonnancement = ordonnancement;
		this.periodiques = tachesPeriodiques;
		this.aperiodiques = tachesAperiodiques;
		this.iterateur = ordonnancement.iterator();
		this.enAttente = new PriorityQueue<PrioRM>();
		this.aperiodiquesEnAttente = new LinkedList<Tache>();
		this.unitesRestantes=0;
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
		this.algo.initialiser(ordonnancement, tachesPeriodiques, tachesAperiodiques);
		
		this.initTachePs();
		this.initMap();
		
	}
	public void setAlgo(Algorithme algo){
		this.algo = algo;
	}
	
	// retourne le temps d'execution a avoir pour les aperiodiques pendant l'unité courante
	public int tpsExecAperiodique(){
		int result=0;
		for(Tache t : aperiodiquesEnAttente)
			result += t.getC();
		
		return result;
	}

	public void initTachePs(){
		
		for(Tache t :this.periodiques)
			if(t instanceof TachePs){
				this.tachePS = (TachePs) t;
				return;
			}
	}
	
	

}
