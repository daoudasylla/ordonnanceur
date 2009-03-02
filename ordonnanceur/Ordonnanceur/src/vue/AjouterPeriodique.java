package vue;
import java.awt.BorderLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import exe.Programme;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AjouterPeriodique extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton boutonValider;
	private JTextField textDi;
	private JTextField textPi;
	private JTextField textCi;
	private Programme fenetrePrincipale;
	
	public AjouterPeriodique(Programme frame) {
		super(frame);
		this.fenetrePrincipale=frame;
		initGUI();
		
		this.setTitle("Ajouter une tâche Périodique");
		this.setLocationRelativeTo(this.getParent());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JTextField getTextDi() {
		return textDi;
	}

	public JTextField getTextPi() {
		return textPi;
	}

	public JTextField getTextCi() {
		return textCi;
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Temps d'execution (Ci) :");
				jLabel1.setBounds(12, 27, 129, 19);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Période (Pi) :");
				jLabel2.setBounds(12, 61, 62, 14);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("DeadLine (Di) :");
				jLabel3.setBounds(12, 92, 71, 14);
			}
			{
				textCi = new JTextField();
				getContentPane().add(textCi);
				textCi.setBounds(170, 26, 59, 21);
			}
			{
				textPi = new JTextField();
				getContentPane().add(textPi);
				textPi.setBounds(170, 58, 59, 21);
			}
			{
				textDi = new JTextField();
				getContentPane().add(textDi);
				textDi.setBounds(170, 89, 59, 21);
			}
			{
				boutonValider = new JButton();
				getContentPane().add(boutonValider);
				boutonValider.setText("Ajouter");
				boutonValider.setBounds(113, 133, 75, 21);
				boutonValider.setActionCommand("ajouterPerio");
				boutonValider.addActionListener(new BoutonsListener(this.fenetrePrincipale));
				
			}
			this.setSize(319, 199);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
