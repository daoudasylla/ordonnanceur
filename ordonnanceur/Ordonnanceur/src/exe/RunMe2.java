package exe;

import java.util.Random;


public class RunMe2 {

    public static int entier(int min,int max){
        return (int) Math.floor(Math.random()*(max+1-min)+min);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        double up = 0.75;
        int nbre = 5;
        double[] tab;
        
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
        total+=up;
        System.out.println("total"+total);

    }

}
