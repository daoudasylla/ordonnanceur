package algo;

import java.util.Iterator;
import java.util.LinkedList;

import noyau.Creneau;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class RM implements Algorithme{
	private int[] ordonnancement;

	private boolean[][] periodes;
	private ListeTaches liste;
	private int ppcm;
	public RM(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = new int[ppcm];
		this.liste = null;
	}
	
	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes()
	{
		Iterator<Tache> it = this.liste.iterator();
		int periodeTemp;
		Tache tacheTemp = null;
		while(it.hasNext())
		{
			periodeTemp = 0;
			tacheTemp = it.next();
			periodeTemp = ((Periodique)tacheTemp).getP();
			while(periodeTemp < this.ppcm)
			{
				this.periodes[periodeTemp-1][tacheTemp.getId()] = true;
				periodeTemp += periodeTemp;
			}
		}
	}
	public LinkedList<Creneau> executer(ListeTaches liste)
	{
		this.liste = liste;
		this.periodes = new boolean[ppcm][this.liste.size()];
		return null;


	}
}
