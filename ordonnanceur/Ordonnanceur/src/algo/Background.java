package algo;

import java.util.LinkedList;

import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class Background implements Algorithme{
	private ListeTaches aperiodique;
	private LinkedList<UniteTemps> ordonnancement;
	public LinkedList<UniteTemps> executer(ListeTaches liste)
	{
		ListeTaches periodique = new ListeTaches();
		for(Tache t : liste) {
			if(t instanceof Periodique) {
				periodique.add(t);
				liste.remove(t);
			}
		}
		this.aperiodique = liste;
		//RM rm = new RM(periodique);
		return this.ordonnancement;
	}
}
