package vue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
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
public class CreationTachePs extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton boutonValider;
	private JTextField textDi;
	private JTextField textPi;
	private Programme fenetrePrincipale;
	private JTextField textCapacite;
	private JLabel jLabel3;

	public CreationTachePs(Programme frame) {
		super(frame);
		this.fenetrePrincipale=frame;
		this.setTitle("Création de la tâche PS");
		initGUI();
		this.setLocationRelativeTo(this.getParent());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Période (Pi) :");
				jLabel1.setBounds(24, 16, 62, 14);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("DeadLine (Di) :");
				jLabel2.setBounds(24, 47, 71, 14);
			}
			{
				textPi = new JTextField();
				getContentPane().add(textPi);
				textPi.setBounds(177, 14, 41, 19);
			}
			{
				textDi = new JTextField();
				getContentPane().add(textDi);
				textDi.setBounds(177, 45, 41, 19);
			}
			{
				boutonValider = new JButton();
				boutonValider.setActionCommand("validerCreationTachePS");
				boutonValider.addActionListener(new BoutonsListener(this.fenetrePrincipale));
				getContentPane().add(boutonValider);
				boutonValider.setText("Valider");
				boutonValider.setBounds(95, 118, 82, 21);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Capacité du serveur :");
				jLabel3.setBounds(24, 82, 104, 14);
			}
			{
				textCapacite = new JTextField();
				getContentPane().add(textCapacite);
				textCapacite.setBounds(177, 75, 41, 20);
			}
			pack();
			this.setSize(270, 182);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextField getTextDi() {
		return textDi;
	}

	public JTextField getTextPi() {
		return textPi;
	}

	public JTextField getTextCapacite() {
		return textCapacite;
	}

}
