package noyau;

import java.util.List;

import algo.Algorithme;

public class Ordonnanceur {
	private ListeTaches liste;
	private Algorithme algo;
	private List<Creneau> result;

	private int ppcm;
	public Ordonnanceur(Algorithme algo,ListeTaches liste,int ppcm){
		this(algo,ppcm);
		this.liste = liste;

	}
	public Ordonnanceur(Algorithme algo,int ppcm){
		this.algo = algo;
		this.ppcm = ppcm;
	}
	public void setListe(ListeTaches liste) {
		this.liste = liste;
	}
	public void ordonnancer()
	{
		this.result = this.algo.executer(this.liste);
	}
}
