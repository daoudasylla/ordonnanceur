package noyau;

import java.util.List;

import algo.Algorithme;
import algo.UniteTemps;

public class Ordonnanceur {
	private ListeTaches liste;
	private Algorithme algo;
	private List<UniteTemps> result;

	//private int ppcm;
	public Ordonnanceur(Algorithme algo,ListeTaches liste){
		this(algo);
		this.liste = liste;

	}
	public Ordonnanceur(Algorithme algo){
		this.algo = algo;
		//this.ppcm = ppcm;
	}
	public void setListe(ListeTaches liste) {
		this.liste = liste;
	}
	public void ordonnancer()
	{
		this.result = this.algo.executer(this.liste);
		
	}
	
	public String toString(){
		String tmp="Affichage du résultat de l'ordonnanceur\n";
		for(UniteTemps ut : this.result){
			tmp += ut+"\n";
		}
		return tmp;
	}
}
