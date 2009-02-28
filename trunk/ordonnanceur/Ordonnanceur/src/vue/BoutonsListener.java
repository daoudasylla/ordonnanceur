package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import noyau.Periodique;

import exe.Programme;

public class BoutonsListener implements ActionListener {
	Programme fenetrePrincipale;

	public BoutonsListener(Programme frame){
		fenetrePrincipale=frame;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		String commande = arg0.getActionCommand();
		if(commande.equals("fenAjoutPerio")){
			this.fenetrePrincipale.getFenAjoutPerio().setVisible(true);
		}
		else if(commande.equals("fenAjoutAperio")){
			this.fenetrePrincipale.getFenAjoutAperio().setVisible(true);
		}	
		else if(commande.equals("ajouterPerio")){
			// Recuperation des valeurs
			int ci = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextCi().getText());
			int di = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextDi().getText());
			int pi = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextPi().getText());
			
			
			Periodique perio = new Periodique(ci,di,pi);
			Integer id = new Integer(perio.getId());
			
			this.fenetrePrincipale.getListeTaches().add(perio);
			this.fenetrePrincipale.getDatasListeTaches().add(new String[]{id.toString(),""});
			
		}
	}

}
