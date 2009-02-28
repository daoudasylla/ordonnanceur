package exe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import noyau.ListeTaches;

import vue.AjouterAperiodique;
import vue.AjouterPeriodique;
import vue.BoutonsListener;
import vue.UneditableTableModel;


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

	private JTable ensembleTaches;
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
	private UneditableTableModel tableModel;
	private ArrayList<String[]> datasListeTaches;
	
	private AjouterPeriodique fenAjoutPerio;
	private AjouterAperiodique fenAjoutAperio;
	
	private ListeTaches listeTaches;
	

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
		this.fenAjoutAperio = new AjouterAperiodique(this);
		this.fenAjoutPerio = new AjouterPeriodique(this);
		this.listeTaches = new ListeTaches();
		initGUI();
	}
	
	public ListeTaches getListeTaches() {
		return listeTaches;
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
				datasListeTaches = new ArrayList<String[]>();
				tableModel = new UneditableTableModel(datasListeTaches,new String[] { "N°", "Type", "Infos" });
				ensembleTaches = new JTable();
				getContentPane().add(ensembleTaches);
				ensembleTaches.setModel(tableModel);
				ensembleTaches.setBounds(85, 129, 390, 74);
				ensembleTaches.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			{
				ajoutPerio = new JButton();
				getContentPane().add(ajoutPerio);
				ajoutPerio.setText("Périodique");
				ajoutPerio.setActionCommand("fenAjoutPerio");
				ajoutPerio.setBounds(91, 91, 137, 28);
				ajoutPerio.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icone-plus.png")));
				ajoutPerio.addActionListener(new BoutonsListener(this));
			}
			{
				boutonAjoutAperio = new JButton();
				getContentPane().add(boutonAjoutAperio);
				boutonAjoutAperio.setActionCommand("fenAjoutAperio");
				boutonAjoutAperio.setText("Aperiodique");
				boutonAjoutAperio.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icone-plus.png")));
				boutonAjoutAperio.setBounds(239, 91, 142, 29);
				boutonAjoutAperio.addActionListener(new BoutonsListener(this));
			}
			{
				boutonSuppr = new JButton();
				getContentPane().add(boutonSuppr);
				boutonSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icone-moins.png")));
				boutonSuppr.setBounds(397, 91, 44, 29);
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

	public ArrayList<String[]> getDatasListeTaches() {
		return datasListeTaches;
	}

	public UneditableTableModel getTableModel() {
		return tableModel;
	}

	public JTable getEnsembleTaches() {
		return ensembleTaches;
	}

	public AjouterPeriodique getFenAjoutPerio() {
		return fenAjoutPerio;
	}

	public AjouterAperiodique getFenAjoutAperio() {
		return fenAjoutAperio;
	}
	
	
	public void showError(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}

}
