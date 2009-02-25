package algo;

import java.util.LinkedList;

import noyau.ListeTaches;

public interface Algorithme {	
	
	//public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques);
	public void initialiser(LinkedList<UniteTemps> ordonnancement, ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques);
	public UniteTemps uniteSuivante();
}
