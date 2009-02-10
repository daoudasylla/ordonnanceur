package exe;

import java.util.Random;

import algo.Algorithme;
import algo.RM;
import algo.EDF;
import algo.RM;
import algo.UniteTemps;

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
     /*
      for(int i=1;i<40;i++){
      

      Generation gen = new Generation(10);
      liste = gen.genererTachesPeriodiques(0.5); 
      System.out.println(liste);
      }*/
      ListeTaches liste2 = new ListeTaches();
     /* liste2.add(new Periodique(2,6,6));
      liste2.add(new Periodique(2,8,8));
      liste2.add(new Periodique(2,12,12));*/
     /* liste2.add(new Periodique(3,20,20));
      liste2.add(new Periodique(2,10,10));
      liste2.add(new Periodique(2,5,5));
      Ordonnanceur o = new Ordonnanceur(new RM(20),liste2);*/
      /*ListeTaches liste2 = new ListeTaches();
     liste2.add(new Periodique(1,4,4));
      liste2.add(new Periodique(2,6,6));
      liste2.add(new Periodique(3,8,8));
     
      liste2.add(new Periodique(1,20,8));
      liste2.add(new Periodique(4,10,10));
      liste2.add(new Periodique(2,5,4));
      Ordonnanceur o = new Ordonnanceur(new EDF(20),liste2); */
      liste2.add(new Periodique(1,4,4));
      liste2.add(new Periodique(2,6,6));
      liste2.add(new Periodique(3,8,8));
      Ordonnanceur o = new Ordonnanceur(new EDF(24),liste2);
      o.ordonnancer();
      System.out.println(o);
      
    }
}
