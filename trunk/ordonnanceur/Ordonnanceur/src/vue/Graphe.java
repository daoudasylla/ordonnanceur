package vue;
import java.awt.*;
import java.applet.*;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import exe.Programme;

import noyau.Aperiodique;
import noyau.Periodique;
import noyau.Tache;
import noyau.TachePs;

import algo.UniteTemps;
public class Graphe extends JPanel {
	Font clockFaceFont;
	Color repereColor;
	Color graduationColor;
	static int LONGUEUR_LIGNE=850;
	static int ESPACEMENT_LIGNE=50;
	static int xDepart = 100;
	static int yDepart = 20;
	private int nbreTaches;
	private int ppcm;
	private LinkedList<UniteTemps> liste;
	private HashMap<Integer,Integer> correspondance;
	private Programme fenetrePrincipale;
	
	public Graphe(Programme frame,LinkedList<UniteTemps> liste,int nbreTaches, int ppcm){
		this.nbreTaches=nbreTaches;
		this.ppcm=ppcm;
		this.liste=liste;
		this.correspondance = new HashMap<Integer,Integer>();
		this.fenetrePrincipale=frame;
		
		init();
		
	}
	public void init() {
		setBackground(new Color(212,208,200));
		this.setSize(xDepart+LONGUEUR_LIGNE+60, y0Bas()+20);
		repereColor = Color.black;
		graduationColor=Color.gray;
		int i=1;
		
		
		// Si tache PS présente on l'entre dans la correspondance en derniere tache
		TachePs tachePs = this.fenetrePrincipale.getTachePS();
		if(tachePs!=null){
			correspondance.put(tachePs.getId(),this.nbreTaches);		
		}		
		
		for(Tache t : this.fenetrePrincipale.getListeTaches()){
				int id = t.getId();
				int idTacheGraph = i;
				
				
				
				// Si algo polling ne pas afficher les autres taches aperio que ps
				if(tachePs!=null){
					if(t instanceof Aperiodique) idTacheGraph=correspondance.get(tachePs.getId());
				}
				
				// Si algo EDL pareil
				if(this.fenetrePrincipale.getListeAperio().getSelectedIndex()==2 && this.fenetrePrincipale.tachesAperiodiquesPresentes()){
					if(t instanceof Aperiodique) idTacheGraph=this.nbreTaches+1;
					
				}
					
				
					if(!correspondance.containsKey(id)){
					correspondance.put(id,idTacheGraph);
					i++;					
					}
			}
		}
	public void plotrepere(Graphics g) {
		System.out.println((this.nbreTaches-1)*Graphe.ESPACEMENT_LIGNE);
		g.drawLine(Graphe.xDepart,Graphe.yDepart,Graphe.xDepart,Graphe.yDepart+((this.nbreTaches+1)*Graphe.ESPACEMENT_LIGNE));//vertical
		g.drawLine(Graphe.xDepart,Graphe.yDepart+((this.nbreTaches+1)*Graphe.ESPACEMENT_LIGNE),Graphe.xDepart+Graphe.LONGUEUR_LIGNE,Graphe.yDepart+((this.nbreTaches+1)*Graphe.ESPACEMENT_LIGNE));//horizon
		}
	public void plotgraduation(int x0,int y0,Graphics g) {
	int i;
	int espacement_graduation = espaceGraduationTps();
		for(i=1;i<=this.nbreTaches;i++){
			g.drawLine(x0,y0+(Graphe.ESPACEMENT_LIGNE*i),x0+Graphe.LONGUEUR_LIGNE,y0+(Graphe.ESPACEMENT_LIGNE*i));//axe horizon
			g.drawString(""+i+"", x0+(espacement_graduation*i)-5, y0Bas()+30);
		}
		
		for(i=1;i<=this.ppcm;i++){
			g.setColor(repereColor);
			g.drawLine(x0+(espacement_graduation*i),y0Bas()-5,x0+(espacement_graduation*i),y0Bas()+5);//axe verti
			g.setColor(Color.black);
			g.drawString(""+i+"", x0+(espacement_graduation*i)-5, y0Bas()+30);
		}
	}
	public void tracebar (int tache, int temps,Graphics g){
		int espacement_graduation = espaceGraduationTps();
		g.setColor(Color.green);
		g.fill3DRect(xTemps(temps),yTache(tache)-Graphe.ESPACEMENT_LIGNE+15,espacement_graduation,Graphe.ESPACEMENT_LIGNE-15,false);
	}
	public void tracegraphe(Graphics g){
		
		
		for(UniteTemps ut : this.liste){
			if(ut.getTache()!=null){
				int tacheDansGraphe = this.correspondance.get(ut.getTache().getId());
				
				// Tracage de l'unité de temps
				tracebar (tacheDansGraphe, ut.getIdUnite(),g);
				
			}
		}
		
		// Tracage des deadlines et periodes
		
		for(Tache t : this.fenetrePrincipale.getListeTaches()){
			int xDead=0;
			int xPeriode=0;
			
			int tacheDansGraphe = this.correspondance.get(t.getId());
			
			if(t instanceof Periodique){
				
				while(xDead<=ppcm){
				xDead += ((Periodique)t).getD();
				g.setColor(Color.red);
				g.drawLine(xTemps(xDead),yTache(tacheDansGraphe),xTemps(xDead),yTache(tacheDansGraphe)-ESPACEMENT_LIGNE+15);//axe verti
				g.drawString("D", xTemps(xDead),yTache(tacheDansGraphe)-ESPACEMENT_LIGNE+10);

				}
				
				while(xPeriode<=ppcm){
					xPeriode += ((Periodique)t).getP();
					g.setColor(Color.MAGENTA);
					g.drawLine(xTemps(xPeriode),yTache(tacheDansGraphe),xTemps(xPeriode),yTache(tacheDansGraphe)-ESPACEMENT_LIGNE+15);//axe verti
					g.drawString("P", xTemps(xPeriode),yTache(tacheDansGraphe)-ESPACEMENT_LIGNE+10);

					}
			}
			
			// Trcage des dates de reveil pour les aperio
			if(t instanceof Aperiodique){
				int xReveil=((Aperiodique)t).getR();	
				
				g.setColor(Color.BLUE);
				g.drawLine(xTemps(xReveil),yTache(tacheDansGraphe),xTemps(xReveil),yTache(tacheDansGraphe)-ESPACEMENT_LIGNE+15);//axe verti
				g.drawString("R", xTemps(xReveil),yTache(tacheDansGraphe)-ESPACEMENT_LIGNE+10);

			}
		}
		
		
		
		
	}

	public void paintComponent(Graphics g) {	
		
		g.setColor(repereColor);
		plotrepere(g);
		g.setColor(graduationColor);
		plotgraduation(Graphe.xDepart,Graphe.yDepart,g);
		
		if(this.fenetrePrincipale.getTachePS()!=null){
		
			// Affichage de la tache PS
			setNomsTaches(correspondance.get(this.fenetrePrincipale.getTachePS().getId()), "Tache PS", g);
			int idTachePS = correspondance.get(this.fenetrePrincipale.getTachePS().getId());
			
			for(Integer i : correspondance.keySet())
				if(correspondance.get(i)!=idTachePS)
				setNomsTaches(correspondance.get(i), "Tache n°"+i, g);
		} else if(this.fenetrePrincipale.getListeAperio().getSelectedIndex()==2 && this.fenetrePrincipale.tachesAperiodiquesPresentes()){
			
			// Affichage de la tache PS
			setNomsTaches(this.nbreTaches+1, "Tache EDL", g);
			int idTacheEDL = this.nbreTaches+1;
			
			
			for(Integer i : correspondance.keySet())
				if(correspondance.get(i)!=idTacheEDL)
				setNomsTaches(correspondance.get(i), "Tache n°"+i, g);
			
		} else {

			
			for(Integer i : correspondance.keySet())
				setNomsTaches(correspondance.get(i), "Tache n°"+i, g);
		}
		
		tracegraphe(g);
		
	 }
	
	private int y0Bas(){
		return Graphe.yDepart+((this.nbreTaches+1)*Graphe.ESPACEMENT_LIGNE);
	}
	
	private int yTache(int tache){
		return Graphe.yDepart+(Graphe.ESPACEMENT_LIGNE*tache);
	}
	
	private int xTemps(int tps){
		int espacement_graduation =espaceGraduationTps();
		return Graphe.xDepart+(espacement_graduation*tps);
	}
	
	private int espaceGraduationTps(){
		return Graphe.LONGUEUR_LIGNE/this.ppcm;
	}
	
	public void setNomsTaches(int numTache, String nom,Graphics g){
		g.drawString(nom, xDepart-g.getFontMetrics().stringWidth(nom)-20, yDepart+(Graphe.ESPACEMENT_LIGNE/2)+((Graphe.ESPACEMENT_LIGNE)*(numTache-1)));
	}
}	