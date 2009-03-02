package vue;
import java.awt.*;
import java.applet.*;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algo.UniteTemps;
public class Graphe extends JPanel {
	Font clockFaceFont;
	Color repereColor;
	Color graduationColor;
	static int LONGUEUR_LIGNE=650;
	static int ESPACEMENT_LIGNE=50;
	static int xDepart = 100;
	static int yDepart = 20;
	private int nbreTaches;
	private int ppcm;
	private LinkedList<UniteTemps> liste;
	private HashMap<Integer,Integer> correspondance;
	
	public Graphe(LinkedList<UniteTemps> liste,int nbreTaches, int ppcm){
		this.nbreTaches=nbreTaches;
		this.ppcm=ppcm;
		this.liste=liste;
		this.correspondance = new HashMap<Integer,Integer>();
		
		String tmp="Affichage du résultat de l'ordonnanceur\n";
		for(UniteTemps ut : this.liste){
			tmp += ut+"\n";
		}
		System.out.println(tmp);
		init();
		
	}
	public void init() {
		setBackground(new Color(212,208,200));
		this.setSize(xDepart+LONGUEUR_LIGNE+60, y0Bas()+20);
		repereColor = Color.black;
		graduationColor=Color.gray;
		int i=1;
			for(UniteTemps ut : this.liste){
				if(ut.getTache()!=null){
					if(!correspondance.containsKey(ut.getTache().getId())){
					correspondance.put(ut.getTache().getId(),i);
					i++;
					}
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
		g.fill3DRect(xTemps(temps),yTache(tache)-Graphe.ESPACEMENT_LIGNE,espacement_graduation,Graphe.ESPACEMENT_LIGNE,false);
	}
	public void tracegraphe(Graphics g){
		
		
		for(UniteTemps ut : this.liste){
			if(ut.getTache()!=null)
			tracebar (this.correspondance.get(ut.getTache().getId()), ut.getIdUnite(),g);
		}
		
		
		
	}

	public void paintComponent(Graphics g) {	
		
		g.setColor(repereColor);
		plotrepere(g);
		g.setColor(graduationColor);
		plotgraduation(Graphe.xDepart,Graphe.yDepart,g);
		
		
		for(Integer i : correspondance.keySet())
			setNomsTaches(correspondance.get(i), "Tache n°"+i, g);
		
		
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