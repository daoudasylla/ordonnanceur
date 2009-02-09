package exe;

import java.util.Random;

import algo.Algorithme;
import algo.RM;

import noyau.Generation;
import noyau.ListeTaches;
import noyau.Ordonnanceur;
import noyau.Periodique;


public class RunMe2 {

   
    /**
     * @param args
     */
    public static void main(String[] args) {
        
      ListeTaches liste;
     
      for(int i=1;i<40;i++){
      

      Generation gen = new Generation(10);
      liste = gen.genererTachesPeriodiques(0.5); 
      System.out.println(liste);
      }
      ListeTaches liste2 = new ListeTaches();
      liste2.add(new Periodique(2,6,6));
      liste2.add(new Periodique(2,8,8));
      liste2.add(new Periodique(2,12,12));
      Ordonnanceur o = new Ordonnanceur(new RM(24),liste2);
      o.ordonnancer();
      
    }
}
