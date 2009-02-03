package noyau;

import java.util.LinkedList;

public class ListeTaches {

	LinkedList<Tache> listeTaches;
	
	
	public ListeTaches(){		
		this.listeTaches = new LinkedList<Tache>();
	}
	
	public void addTache(Tache task){
		this.listeTaches.add(task);
	}
	
	public void supprTache(Tache task){
		this.listeTaches.remove(task);
	}	
	
	public void reinitialiserListe(){
		this.listeTaches.clear();
	}
	
	public String toString(){
		
		String tmp = new String();
		for(Tache t : this.listeTaches)
			tmp += "\n"+t;
		
		return tmp;
	}
}
