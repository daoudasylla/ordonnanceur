package algo;

import java.util.LinkedList;

import noyau.Creneau;
import noyau.ListeTaches;

public interface Algorithme {
	public LinkedList<Creneau> executer(ListeTaches liste);
}
