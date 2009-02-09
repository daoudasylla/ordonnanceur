package algo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UniteTemps {
	
	//la liste des id des taches dont la période commence sur cette unité
	private List<Integer> periodes;
	//l'id de la tache qui occupera cette unité de temps
	private int idTache;
	private int idUnite;
	public UniteTemps(int idUnite)
	{
		this.idTache = 0;
		this.idUnite = idUnite;
		this.periodes = new LinkedList<Integer>();
	}
	public int getIdTache() {
		return idTache;
	}
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}
	public boolean equals(Object o)
	{
		UniteTemps u = (UniteTemps) o;
		return this.idUnite == u.getIdUnite();
	}
	public int getIdUnite() {
		return idUnite;
	}
	public void ajouterPeriode(int idTache){
		if(!this.periodes.contains(idTache)){
			this.periodes.add(idTache);
		}
	}
	public String toString()
	{
		String temp = "unite: "+this.idUnite+"\nidTache: "+this.idTache+"\nPériodes: ";
		Iterator<Integer> it = this.periodes.iterator();
		while(it.hasNext())
			temp+= it.next()+" ";
		
		return temp;
	}
}
