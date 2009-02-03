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
      
      Generation gen = new Generation(5);
      liste = gen.genererTachesPeriodiques(0.75); 

    }

}
