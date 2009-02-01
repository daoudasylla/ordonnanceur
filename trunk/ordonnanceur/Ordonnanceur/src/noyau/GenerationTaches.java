package noyau;

import java.util.LinkedList;




public class GenerationTaches {
	

	
	
	public void GenerationTaches(){
		
	}
	
	public LinkedList<Tache> genererTachesPeriodique(int nbre, double up){
   
	        double[] tab;
	        
	        // On multiplie notre coefficien pour diminuer la marge d'erreur
	        up*=100000;
	        tab = new double[nbre];
	        int total=0;
	        for(int i=0;i<nbre-1;i++){
	            
	            int min = (int)((up/(nbre-i))*0.5);
	            int max = (int)((up/(nbre-i))*1.5);
	            int rdm = entier(min,max);
	            up = (up-rdm);
	            total+=rdm;
	            
	            System.out.println(rdm);
	            
	        }
	        
	        // Definition de l'ensemble des taches
	        for(double d : tab){
	        	
	        	// Définition
	        	
	        }
	        
	        return null;
	}
	
	private  int entier(int min,int max){
	        return (int) Math.floor(Math.random()*(max+1-min)+min);
	}

}
