package algo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import noyau.Creneau;
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
				this.ordonnancement.get(this.ordonnancement.indexOf(new UniteTemps(periodeTemp))).ajouterPeriode(tacheTemp.getId());
				//this.periodes[periodeTemp-1][tacheTemp.getId()] = true;
				periodeTemp += periodeTache;
			}
		}
	}
	public LinkedList<Creneau> executer(ListeTaches liste)
	{
		this.liste = liste;
		this.calculePeriodes();
		Iterator<UniteTemps> it = this.ordonnancement.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		//this.periodes = new boolean[ppcm][this.liste.size()];
		return null;


	}
}
