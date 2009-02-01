package noyau;

import java.util.LinkedList;

public class ListeTaches {

	LinkedList<Tache> listeTaches;
	
	
	public void ListeTaches(){		
		this.listeTaches = new LinkedList();
	}
	
	public void addTache(Tache task){
		this.listeTaches.add(task);
	}
	
	public void supprTache(Tache task){
		this.listeTaches.remove(task);
	}	
	
}
