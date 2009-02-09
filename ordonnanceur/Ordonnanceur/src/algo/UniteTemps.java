package algo;

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
		this.idUnite = idUnite;
		this.periodes = new LinkedList<Integer>();
	}
	public int getIdTache() {
		return idTache;
	}
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}
}
