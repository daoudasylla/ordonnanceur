package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;



public class RunMe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String reponse = new String();
		boolean quit=false;
		int nbTachesPeriodiques=0;
		int nbTachesAPeriodiquesCritiques=0;
		int nbTachesAPeriodiquesNonCritiques=0;
		double facteurUp,facteurUs;
		
		
		System.out.println("--------------------------------------------");
		System.out.println("Projet de Systeme distribues - Ordonnanceur");
		System.out.println("--------------------------------------------");
		
		
		
		
		
		try {
			
		System.out.println("Nombre de taches periodiques ?");
		BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		reponse =  entree.readLine();		
		nbTachesPeriodiques = Integer.parseInt(reponse);
		
		System.out.println("Facteur Up ?");
		entree = new BufferedReader(new InputStreamReader(System.in));
		reponse =  entree.readLine();		
		facteurUp = Double.parseDouble(reponse);
		
		/*System.out.println("Nombre de taches aperiodiques \"critiques\" ?");
		entree = new BufferedReader(new InputStreamReader(System.in));
		reponse =  entree.readLine();		
		nbTachesAPeriodiquesCritiques = Integer.parseInt(reponse);
		
		System.out.println("Nombre de taches aperiodiques \"non critiques\" ?");
		entree = new BufferedReader(new InputStreamReader(System.in));
		reponse =  entree.readLine();		
		nbTachesAPeriodiquesNonCritiques = Integer.parseInt(reponse);
		
		
		System.out.println("Facteur Us ?");
		entree = new BufferedReader(new InputStreamReader(System.in));
		reponse =  entree.readLine();		
		facteurUs = Double.parseDouble(reponse);*/
		
		
		
		
			
		
		
		
		while(!quit){		
			
		switch(String.parseInt(reponse)){	
			case 0:
				quit=true;				
			break;
		}	
		
		
			
		}
			      
			      
			      
			

			
			
		}
		} catch( IOException e ) {
			System.out.println("Erreur lors de la lecture des données");
		} 
		
		

	}

}
