package algo;


import java.util.HashMap;
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
	
	public EDL(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = null;
		
		this.liste = null;
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
		this.dejaExecutees = new HashMap<Tache,Boolean>();
	}
	
	private void initOrdonnancement(){	
		System.out.println("init de l'ordo EDL");
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
	}
	
	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes(ListeTaches liste)
	{
		initOrdonnancement();	
		int periodeTemp, periodeTache;
		Tache tacheTemp = null;
		for(Tache t : liste)
		
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
		
		
		}
	}
	
	public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques)
	{
	
	this.calculePeriodes(tachesPeriodiques);
	
	
	
	/*PriorityQueue<PrioEDL> enAttente = new PriorityQueue<PrioEDL>(); //taches en attentes	
	
	boolean dejaAffecte=false;
	int unitesRestantes =0;
	Tache tacheEnCours=null;
	// On associe toutes les taches aperiodiques aux unites de temps
	for(UniteTemps uniteCourante : this.ordonnancement)
	{
		
		if(unitesRestantes>0){
			dejaAffecte=true;
			uniteCourante.setIdTache(tacheEnCours.getId());
			unitesRestantes--;
		}
		
		// On verifie qu'il n'y a pas de tache dans la lsite d'attente
		if(enAttente.peek()!=null && unitesRestantes==0){
			dejaAffecte=true;
			
			// On affecte la tache en attente a l'unite en cours
			tacheEnCours = enAttente.remove().getTache();
			uniteCourante.setIdTache(tacheEnCours.getId());
			unitesRestantes = tacheEnCours.getC();
		}
		
		for(Tache ape : tachesAperiodiques)
		{			
			
			if(uniteCourante.getIdUnite()==((Aperiodique) ape).getR()){
				
				// Si tache deja affecté dans liste d'attente on mets dans la liste d'attente
				if(dejaAffecte) enAttente.add(new PrioEDL(((Aperiodique) ape)));
				else {
					uniteCourante.setIdTache(ape.getId());
					dejaAffecte=true;
					tacheEnCours = ape;
					unitesRestantes = tacheEnCours.getC()-1;
				}
				
			}
		}
		
		dejaAffecte=false; // On remet a jour notre variable booleenne
	}
	
	
	/*for(UniteTemps uniteCourante : this.ordonnancement)
	{
		System.out.println(uniteCourante);
	}*/
	
	
	
	
	
	// On effectue une premiere simulation pour récuperer le contexte d'execution d'EDF
	LinkedList<UniteTemps> init=this.ordonnancement;
	
	EDF edf = new EDF(this.ppcm);
	edf.setOrdonnancement(init); 
	//edf.executer(tachesPeriodiques, tachesAperiodiques);
	this.mapTacheUnitesRestantes = edf.getMapTacheUnitesRestantes(tachesPeriodiques);
	
	
	System.out.println("après recup contexte");
	
	PriorityQueue<PrioEDL> enAttente = new PriorityQueue<PrioEDL>(); //taches en attentes	
	Tache tacheEnCours=null;
	int unitesRestantes =0;
	int tempsPlusTard=0;
	boolean dejaAffecte=false;
	for(UniteTemps uniteCourante : this.ordonnancement)
	{
		
		for(Tache ape : tachesAperiodiques)
		{			
			
			if(uniteCourante.getIdUnite()==((Aperiodique) ape).getR()){
				System.out.println("u="+uniteCourante.getIdUnite()+" reveil aperio "+ape.getId());
				if(dejaAffecte) enAttente.add(new PrioEDL(((Aperiodique) ape)));
				else {
					tempsPlusTard = plusTard(uniteCourante, tachesPeriodiques, tachesAperiodiques);
					System.out.println("temps plus tard "+tempsPlusTard);
					if(tempsPlusTard>uniteCourante.getIdUnite()){
						dejaAffecte=true;
						tacheEnCours = ape;
						unitesRestantes = tacheEnCours.getC();
						
						
						// On ajoute l'aperio dans la map si besoin
						if(this.mapTacheUnitesRestantes.get(ape)==null || this.mapTacheUnitesRestantes.get(ape)==0){
							System.out.println("ajoute aperio map "+ape.getId()+" "+unitesRestantes);
							this.mapTacheUnitesRestantes.put(ape,unitesRestantes);						
						}
					}
						
				}
			}
		}
		
		
		if(enAttente.peek()!=null && tacheEnCours==null){
			tempsPlusTard = plusTard(uniteCourante, tachesPeriodiques,tachesAperiodiques);
			tacheEnCours = enAttente.remove().getTache();
			this.mapTacheUnitesRestantes.put(tacheEnCours,tacheEnCours.getC());	
			dejaAffecte=true;
		}
		
		
		if(tacheEnCours!=null && tempsPlusTard>uniteCourante.getIdUnite() ){
			
			unitesRestantes = this.mapTacheUnitesRestantes.get(tacheEnCours)-1;
			System.out.println("tache en cours: "+tacheEnCours.getId()+"("+unitesRestantes+")");
			
			this.mapTacheUnitesRestantes.put(tacheEnCours,unitesRestantes);
			uniteCourante.setIdTache(tacheEnCours.getId());
			
			// Si tache aperio est finie on verifie qu'une autre peut etre commencée
			if(unitesRestantes==0){
				dejaAffecte=false;
				tacheEnCours=null;				
				
			}else {
				dejaAffecte=true;
				uniteCourante.setIdTache(tacheEnCours.getId());
				
			}
		}
		
		
		// Si aucune aperiodique dans cette unité on affecte une perio si besoin
		if(!dejaAffecte && tacheEnCours==null)			
		EDF(uniteCourante, tachesPeriodiques, tachesAperiodiques);
			
		
		
	}
	
	

	return this.ordonnancement;
		
	}
	
	
	private void EDF(UniteTemps uniteCourante, ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques){
		
		System.out.println("appel methode EDF à lunite "+uniteCourante.getIdUnite());
		LinkedList<UniteTemps> resultat= new LinkedList<UniteTemps>();
		for(UniteTemps ut : this.ordonnancement)
			resultat.add((UniteTemps) ut.clone());
		
		// On appel EDF pour continuer le travail
		EDF edf = new EDF(this.ppcm);
		edf.setOrdonnancement(resultat); // on affecte le contexte courant
		edf.setMapTacheUnitesRestantes(mapTacheUnitesRestantes);
		
		
		UniteTemps u = edf.executerInstantT(uniteCourante, tachesPeriodiques, tachesAperiodiques);
		
		
		System.out.println("methode EDF : u="+uniteCourante.getIdUnite()+" t="+u.getIdTache());
		uniteCourante.setIdTache(u.getIdTache());
			
		
		
	}
	
	// Fonction permettant d'indiquer le temps max où les taches doivent être exécutées
	private int plusTard(UniteTemps actuel,ListeTaches tachesPeriodiques,ListeTaches tachesAperiodiques){
		
		// Construction de la file avec priorité qui classe les taches selon le Di le plus tard
		/*PriorityQueue<PrioAuPlusTard> prioAuPlusTard = new PriorityQueue<PrioAuPlusTard>(); //taches en attentes	
		
		HashMap<Tache,Integer> deadlines = new HashMap<Tache,Integer>();
		
		for(Tache t : tachesPeriodiques)
			deadlines.put(t,new Integer(0));
		int tempsMax = this.ppcm;
		int ecart=0;
		
		for(Tache t : tachesPeriodiques){				
				deadlines.put(t, ((Periodique)t).getDi(this.ppcm-1));			
		}
		
		
		for(int i=this.ppcm-1; i>=actuel.getIdUnite();i--){
			
			// A chaque nouvelle valeur d'unité de temps on vérifie que la deadline ne change pas
			// si elle change on rajoute alors la tache dans les taches en attentes
			for(Tache t : tachesPeriodiques){
				if(deadlines.get(t)!=((Periodique)t).getDi(i)){
					//System.out.println(deadlines.get(t)+"!="+((Periodique)t).getDi(i));
					prioAuPlusTard.add(new PrioAuPlusTard((Periodique)t,new UniteTemps(i)));
					deadlines.put(t, ((Periodique)t).getDi(i));
				}
			}
			
			if(prioAuPlusTard.peek()!=null){
				Periodique tEnCours = prioAuPlusTard.remove().getTache();
				Periodique tAvant=tEnCours;
				
					
				tempsMax = tempsMax - tEnCours.getC();
				
				
				// On retire l'ensemble des executions possibles e partant de la fin
				while(prioAuPlusTard.peek()!=null && tempsMax>0){
					tEnCours = prioAuPlusTard.remove().getTache();		
					ecart = tempsMax-tAvant.getDi(i);
					tempsMax = tempsMax-ecart-tEnCours.getC();
					tAvant = tEnCours;
				}
			}
		}*/
		
		
		UniteTemps dernier=null;
		
		LinkedList<UniteTemps> resultat= new LinkedList<UniteTemps>();
		for(UniteTemps ut : this.ordonnancement)
			resultat.add((UniteTemps) ut.clone());
		
		// On appel EDF pour continuer le travail
		EDF edf = new EDF(this.ppcm);
		edf.setOrdonnancement(resultat); // on affecte le contexte courant
		edf.setMapTacheUnitesRestantes(mapTacheUnitesRestantes);
		
		for(int i = actuel.getIdUnite(); i < this.ppcm ; i++){
			UniteTemps tmp =new UniteTemps(i);
			dernier = edf.executerInstantT(tmp, tachesPeriodiques, tachesAperiodiques);
		}
		
		System.out.println("DERNIER : "+dernier.getIdUnite());
		
		
		
		
	return ppcm-dernier.getIdUnite();
		
	}
	
	
	
}
