package noyau;

import algo.Algorithme;

public class Ordonnanceur {
	private ListeTaches liste;
	private Algorithme algo;
	private ListeOrdonnee result;
	public Ordonnanceur(Algorithme algo,ListeTaches liste){
		this(algo);
		this.liste = liste;
	}
	public Ordonnanceur(Algorithme algo){
		this.algo = algo;
	}
	public void setListe(ListeTaches liste) {
		this.liste = liste;
	}
	public void ordonnancer()
	{
		this.result = this.algo.executer(this.liste);
	}
}
