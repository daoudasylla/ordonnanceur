package algo;

import java.util.LinkedList;
import java.util.List;

import noyau.Tache;

public class UniteTemps {
	
	//la liste des id des taches dont la période commence sur cette unité
	private List<Tache> periodes;
	//l'id de la tache qui occupera cette unité de temps
	private Tache tache;
	private int idUnite;
	public UniteTemps(int idUnite)
	{
		this.tache = null; // Si 0 alors la tache est un temps creux
		this.idUnite = idUnite;
		this.periodes = new LinkedList<Tache>();
	}
	
	public UniteTemps(UniteTemps ut)
	{
		this.tache = ut.getTache(); // Si 0 alors la tache est un temps creux
		this.idUnite = ut.getIdUnite();
		this.periodes = ut.getPeriodes();
	}
	
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	public boolean equals(Object o)
	{
		UniteTemps u = (UniteTemps) o;
		return this.idUnite == u.getIdUnite();
	}
	public int getIdUnite() {
		return idUnite;
	}
	public void ajouterPeriode(Tache tache){
		if(!this.periodes.contains(tache)){
			this.periodes.add(tache);
		}
	}
	public String toString()
	{
		String temp;
		//if(this.idTache==0) temp = "Temps Creux";
		//else {
		if(this.tache==null)
			temp = "Tps creux ";
		 else 
			 temp = "unite: "+this.idUnite+" | idTache: "+this.tache.getId()+" | Périodes: ";
			
		for(Tache t : this.periodes)		
		temp+= t.getId()+" ";
		//}
		return temp;
	}
	public List<Tache> getPeriodes() {
		return periodes;
	}
	
	 public Object clone() {
		    return new UniteTemps(this);
		  }

}
