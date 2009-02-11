package noyau;

import java.util.List;

import algo.Algorithme;
import algo.UniteTemps;

public class Ordonnanceur {
	private ListeTaches liste;
	private Algorithme algo;
	private List<UniteTemps> result;
	private ListeTaches tachesPeriodiques;
	private ListeTaches tachesAperiodiques;
	
	
	//private int ppcm;
	public Ordonnanceur(Algorithme algo,ListeTaches liste){
		this(algo);
		this.liste = liste;

	}
	public Ordonnanceur(Algorithme algo){
		this.algo = algo;
		this.tachesPeriodiques = new ListeTaches();
		this.tachesAperiodiques = new ListeTaches();
		//this.ppcm = ppcm;
	}
	public void setListe(ListeTaches liste) {
		this.liste = liste;
	}
	public void ordonnancer()
	{
		
		// On remplit les deux listes
		for(Tache t : liste) {
			if(t instanceof Periodique) {
				this.tachesPeriodiques.add(t);
			}
			else
				this.tachesAperiodiques.add(t);
		}
		
		
		this.result = this.algo.executer(tachesPeriodiques,tachesAperiodiques);
		
	}
	public double tempsReponseAperiodique()
	{
		if(this.result != null)
		{
			double tempsRep = 0;
			boolean trouve = false;
			for(Tache t : this.tachesAperiodiques) {//pour toute les taches apériodiques
				for(UniteTemps u : this.result) {
					if(u.getIdTache() == t.getId() && !trouve) {//quand la tache est trouvée
						tempsRep += (u.getIdUnite() - ((Aperiodique) t).getR()); //on calcul son temps de réponse
						trouve = true;
					}
				}
				trouve = false;
			}
			return (tempsRep / this.tachesAperiodiques.size());
		}
		return 0;
		
	}
	public String toString(){
		String tmp="Affichage du résultat de l'ordonnanceur\n";
		for(UniteTemps ut : this.result){
			tmp += ut+"\n";
		}
		return tmp;
	}
}
