package exe;

import java.util.Random;

import noyau.Generation;
import noyau.ListeTaches;


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

    }

}
