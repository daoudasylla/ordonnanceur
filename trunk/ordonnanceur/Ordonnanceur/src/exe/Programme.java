package exe;
import java.awt.BorderLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;


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
public class Programme extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTable tachesPeriodiques;
	private JButton boutonAjoutAperio;
	private JButton boutonSuppr;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JSeparator jSeparator1;
	private JComboBox listeAperio;
	private JLabel jLabel2;
	private JComboBox listePeriodiques;
	private JLabel jLabel1;
	private JButton ajoutPerio;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Programme inst = new Programme();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Programme() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Menu");
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Ordonnancer");
					}
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Quitter");
					}
				}
			}
			{
				TableModel tachesPeriodiquesModel = 
					new DefaultTableModel(
							new String[][] { { "One", "Two" }, { "Three", "Four" } },
							new String[] { "Column 1", "Column 2" });
				tachesPeriodiques = new JTable();
				getContentPane().add(tachesPeriodiques);
				tachesPeriodiques.setModel(tachesPeriodiquesModel);
				tachesPeriodiques.setBounds(85, 129, 390, 74);
			}
			{
				ajoutPerio = new JButton();
				getContentPane().add(ajoutPerio);
				ajoutPerio.setText("Périodique");
				ajoutPerio.setBounds(85, 85, 113, 28);
				ajoutPerio.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icone-plus.png")));
			}
			{
				boutonAjoutAperio = new JButton();
				getContentPane().add(boutonAjoutAperio);
				boutonAjoutAperio.setText("Aperiodique");
				boutonAjoutAperio.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icone-plus.png")));
				boutonAjoutAperio.setBounds(209, 85, 115, 29);
			}
			{
				boutonSuppr = new JButton();
				getContentPane().add(boutonSuppr);
				boutonSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icone-moins.png")));
				boutonSuppr.setBounds(341, 85, 44, 29);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Algorithme Périodique");
				jLabel1.setBounds(90, 259, 104, 14);
			}
			{
				ComboBoxModel listePeriodiquesModel = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				listePeriodiques = new JComboBox();
				getContentPane().add(listePeriodiques);
				listePeriodiques.setModel(listePeriodiquesModel);
				listePeriodiques.setBounds(221, 252, 103, 21);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Algorithme Apériodique");
				jLabel2.setBounds(90, 313, 111, 14);
			}
			{
				ComboBoxModel listeAperioModel = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				listeAperio = new JComboBox();
				getContentPane().add(listeAperio);
				listeAperio.setModel(listeAperioModel);
				listeAperio.setBounds(221, 306, 103, 21);
			}
			{
				jSeparator1 = new JSeparator();
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(12, 224, 536, 10);
			}
			pack();
			this.setSize(568, 482);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
