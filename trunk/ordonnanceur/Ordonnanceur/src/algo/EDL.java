package algo;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class EDL implements Algorithme{
	
	private LinkedList<UniteTemps> ordonnancement;
	private int ppcm;
	private ListeTaches liste;
	private HashMap<Tache,Integer> mapTacheUnitesRestantes;
	private HashMap<Tache,Boolean> dejaExecutees;
	
	private ListeTaches periodiques;
	private ListeTaches aperiodiques;
	private PriorityQueue<PrioEDL> enAttente = null; //taches en attentes
	private UniteTemps uniteCourante;
	private Tache tacheEnCours = null;
	private Iterator<UniteTemps> iterateur;
	
	private int unitesRestantes;
	private int tempsPlusTard;
	private boolean dejaAffecte;
	
	public EDL(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = null;
		
		this.liste = null;
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
		this.dejaExecutees = new HashMap<Tache,Boolean>();
	}
	

	
	private void initMap()
	{				
		for(Tache t : this.periodiques)		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
		for(Tache t : this.aperiodiques)		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
	
	}
	
	public UniteTemps uniteSuivante() {
	
	
		this.uniteCourante = this.iterateur.next();		
	
		
		for(Tache ape : this.aperiodiques)
		{			
			
			if(uniteCourante.getIdUnite()==((Aperiodique) ape).getR()){
				System.out.println("u="+uniteCourante.getIdUnite()+" reveil aperio "+ape.getId());
				if(dejaAffecte) enAttente.add(new PrioEDL(((Aperiodique) ape)));
				else {
					System.out.println("dans else");
					int tempsMax = plusTard(uniteCourante, this.periodiques, this.aperiodiques);
					
					System.out.println("temps plus tard "+tempsMax);
					if(this.uniteCourante.getIdUnite()<tempsMax){
						dejaAffecte=true;
						this.tacheEnCours = ape;
						unitesRestantes = tacheEnCours.getC();
						
						
						this.mapTacheUnitesRestantes.put(ape,ape.getC());						
						
					}
						
				}
			}
		}
		
		
		if(this.enAttente.peek()!=null && this.tacheEnCours==null){
			//UniteTemps ut = plusTard(uniteCourante, this.periodiques, this.aperiodiques);
			this.tacheEnCours = enAttente.remove().getTache();
			this.mapTacheUnitesRestantes.put(this.tacheEnCours,this.tacheEnCours.getC());	
			dejaAffecte=true;
		}
		
		
		// Si aucune aperiodique dans cette unité on affecte une perio si besoin
		if(!this.dejaAffecte && this.tacheEnCours==null)			
		EDF();
		
		if(dejaAffecte)
			majEnAttente();
		
		if(this.tacheEnCours!=null){
			
			// Verif que y a un temps creux
			int tempsMax = plusTard(uniteCourante, this.periodiques, this.aperiodiques);
			System.out.println("temps plus tard "+tempsMax);
			if(this.uniteCourante.getIdUnite()<tempsMax){
			
					this.unitesRestantes = this.mapTacheUnitesRestantes.get(this.tacheEnCours)-1;
					System.out.println("tache en cours: "+tacheEnCours.getId()+"("+unitesRestantes+")");
					
					this.mapTacheUnitesRestantes.put(this.tacheEnCours,this.unitesRestantes);
					this.uniteCourante.setTache(this.tacheEnCours);
					
					// Si tache aperio est finie on verifie qu'une autre peut etre commencée
					if(this.unitesRestantes==0){
						this.dejaAffecte=false;
						this.tacheEnCours=null;				
						
					}else {
						this.dejaAffecte=true;
						this.uniteCourante.setTache(this.tacheEnCours);
						
					}
			} else {
				EDF();
			}
			
		}
		
		
		
			
		
		
	
	
	

	return this.uniteCourante;
		
	}
	
	
	private void EDF(){
		
		System.out.println("appel methode EDF à lunite "+uniteCourante.getIdUnite());
		LinkedList<UniteTemps> resultat= new LinkedList<UniteTemps>();
		for(UniteTemps ut : this.ordonnancement)
			resultat.add((UniteTemps) ut.clone());
		
		// On appel EDF pour continuer le travail
		EDF edf = new EDF(this.ppcm);
		edf.setContexte(resultat, this.periodiques, this.aperiodiques, this.mapTacheUnitesRestantes, this.uniteCourante);
		//edf.setOrdonnancement(resultat); // on affecte le contexte courant
		//edf.setMapTacheUnitesRestantes(mapTacheUnitesRestantes);
		
		
		UniteTemps u = edf.executerInstantT(uniteCourante);
		
		
		//System.out.println("methode EDF : u="+uniteCourante.getIdUnite()+" t="+u.getTache().getId());
		uniteCourante.setTache(u.getTache());
			
		
		
	}
	
	// Fonction permettant d'indiquer le temps max où les taches doivent être exécutées
	private int plusTard(UniteTemps actuel,ListeTaches tachesPeriodiques,ListeTaches tachesAperiodiques){
		
		System.out.println("dans plus tard pour unite "+actuel.getIdUnite());
		UniteTemps dernier=null;
		
		LinkedList<UniteTemps> resultat= new LinkedList<UniteTemps>();
		for(UniteTemps ut : this.ordonnancement)
			resultat.add((UniteTemps) ut.clone());
		
				
		
		PriorityQueue<PrioAuPlusTard> prioAuPlusTard = new PriorityQueue<PrioAuPlusTard>(); //taches en attentes	
		
		HashMap<Tache,Integer> deadlines = new HashMap<Tache,Integer>();
		
		for(Tache t : tachesPeriodiques)
			deadlines.put(t,new Integer(0));
		int tempsMax = this.ppcm;
		int ecart=0;
		
		for(Tache t : tachesPeriodiques){				
				deadlines.put(t, 0);	
				System.out.println("dead pr "+t.getId()+"="+((Periodique)t).getDi(this.ppcm-1));
		}
		
		
		for(int i=this.ppcm-1; i>=actuel.getIdUnite();i--){
			
			// A chaque nouvelle valeur d'unité de temps on vérifie que la deadline ne change pas
			// si elle change on rajoute alors la tache dans les taches en attentes
			
			System.out.println("\t\t i="+i);
			for(Tache t : tachesPeriodiques){
				if(deadlines.get(t)!=((Periodique)t).getDi(i)){
					System.out.println("nvelle dead pr "+t.getId()+"="+((Periodique)t).getDi(i));
					
					//System.out.println(deadlines.get(t)+"!="+((Periodique)t).getDi(i));
					prioAuPlusTard.add(new PrioAuPlusTard((Periodique)t,new UniteTemps(i)));
					deadlines.put(t, ((Periodique)t).getDi(i));
				}
			}
			
			if(prioAuPlusTard.peek()!=null){
				Periodique tEnCours = prioAuPlusTard.remove().getTache();
				int debutAvant=0;
				
					
				tempsMax = tempsMax - tEnCours.getC();
				System.out.println("\t\ttempsMax - tEnCours.getC() "+tempsMax);
				debutAvant = tempsMax;
				// On retire l'ensemble des executions possibles e partant de la fin
				while(prioAuPlusTard.peek()!=null && tempsMax>0){
					tEnCours = prioAuPlusTard.remove().getTache();	
					
					// Si il existe un ecart entre la deadline et le début de la tache au plus tard d'avant
					if(tEnCours.getDi(i)<debutAvant)
					ecart = debutAvant-tEnCours.getDi(i);
					else
					ecart=0;
					
					if(!dejaExecutee(tEnCours)){
					System.out.println("tache pas encore executee "+tEnCours.getId());
						if(this.mapTacheUnitesRestantes.get(tEnCours)>0 && tEnCours.getDi(i)==tEnCours.getDi(actuel.getIdUnite()))
							tempsMax = tempsMax-ecart-this.mapTacheUnitesRestantes.get(tEnCours);
						else
							tempsMax = tempsMax-ecart-tEnCours.getC();
							
						System.out.println("\t\t tempsMax-ecart-tEnCours.getC() "+tempsMax);
						debutAvant = tempsMax;
					}
				}
			}
		}
		
		System.out.println("Temps max :"+tempsMax);
		
		
		
		
		
		
	return tempsMax;
		
	}

	@Override
	public void initialiser(LinkedList<UniteTemps> ordonnancement,
			ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques) {
		this.ordonnancement = ordonnancement;
		this.periodiques = tachesPeriodiques;
		this.aperiodiques = tachesAperiodiques;
		this.iterateur = ordonnancement.iterator();
		this.enAttente = new PriorityQueue<PrioEDL>();		
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
		this.tacheEnCours=null;
		this.initMap();
		this.dejaExecutees = new HashMap<Tache,Boolean>();		
		this.unitesRestantes =0;
		this.tempsPlusTard=0;
		this.dejaAffecte=false;
	}

	
	public boolean dejaExecutee(Tache t){
		
		int tempsTotal = 0;
		for(int i=0;i<this.uniteCourante.getIdUnite();i++){
			
			Tache temp = this.ordonnancement.get(this.ordonnancement.indexOf(new UniteTemps(i))).getTache();
			if(temp.equals(t)){
				
				if(((Periodique)temp).getDi(i)==((Periodique)temp).getDi(this.uniteCourante.getIdUnite()))
				tempsTotal+=1;
			}
		}
		
		if((tempsTotal/t.getC())==1) return true;
		else return false;
		
	}
	
	// Methode permettant de mettre à jour la map lors d'un reveil d'une periodique
	// pendant l'execution d'EDL
	public void majEnAttente(){
		
		for(Tache t :uniteCourante.getPeriodes()){				
			
			if(this.mapTacheUnitesRestantes.get(t)>0) System.out.println("erreur impossible de finir la tache : "+t.getId()+" "+this.mapTacheUnitesRestantes.get(t));
			else {mapTacheUnitesRestantes.put(t,t.getC());
			}
		}
		
	}
	
}
