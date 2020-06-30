import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import org.apache.commons.dbutils.DbUtils;
 import javax.swing.table.DefaultTableModel;


public class GestionEtudiants extends JFrame {

	private JPanel contentPane;
	private JTextField prenomfield;
	private JTextField nomfield;
	private JTextField cinfield;
	private JTextField Numtelfield;
	private JTextField naissancefield;
	private JTextField adressefield;
	
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTable table;
	
void Fermer(){
	dispose();
}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEtudiants frame = new GestionEtudiants();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionEtudiants() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		cnx =ConnexionMysql.ConnexionDB();
		JLabel lblNewLabel_4 = new JLabel("        GESTION DES ETUDIANTS  ECOLE ANONYME");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UpdateTable();
			}
		});
		lblNewLabel_4.setBounds(165, 11, 685, 75);
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Prenom :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(90, 97, 98, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("    Nom :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(90, 150, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CIN :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(117, 191, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Num Tel :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(90, 228, 128, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Date Naissance :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(70, 268, 108, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Adresse :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(85, 305, 78, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Filiere :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setBounds(103, 345, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		prenomfield = new JTextField();
		prenomfield.setBounds(198, 97, 131, 32);
		contentPane.add(prenomfield);
		prenomfield.setColumns(10);
		
		nomfield = new JTextField();
		nomfield.setBounds(198, 143, 131, 30);
		contentPane.add(nomfield);
		nomfield.setColumns(10);
		
		cinfield = new JTextField();
		cinfield.setBounds(198, 185, 131, 29);
		contentPane.add(cinfield);
		cinfield.setColumns(10);
		
		Numtelfield = new JTextField();
		Numtelfield.setBounds(198, 225, 131, 29);
		contentPane.add(Numtelfield);
		Numtelfield.setColumns(10);
		
		naissancefield = new JTextField();
		naissancefield.setBounds(198, 260, 131, 30);
		contentPane.add(naissancefield);
		naissancefield.setColumns(10);
		
		adressefield = new JTextField();
		adressefield.setBounds(198, 298, 131, 30);
		contentPane.add(adressefield);
		adressefield.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom = prenomfield.getText().toString();
				String nom = nomfield.getText().toString();
				String cin = cinfield.getText().toString();
				String numtel = Numtelfield.getText().toString();
				String naissance = naissancefield.getText().toString();
				String adresse = adressefield.getText().toString();
				String sql = "insert into etudiant (prenom,nom,cin,tel,datenaissance,adresse)values(? ,? ,? ,? ,? ,?)";
				try {
					if(!prenom.equals("")&& !nom.equals("")&& !cin.equals("")&& !numtel.equals("")&& !naissance.equals("")&&!adresse.equals("")){
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, prenom);
						prepared.setString(2, nom);
						prepared.setString(3, cin);
						prepared.setString(4, numtel);
						prepared.setString(5, naissance);
						prepared.setString(6, adresse);
						prepared.execute();
						
						prenomfield.setText("");
						nomfield.setText("");
						cinfield.setText("");
						Numtelfield.setText("");
						naissancefield.setText("");
						adressefield.setText("");
						JOptionPane.showMessageDialog(null, "Ajouter avec Succee ");
					}else{
						JOptionPane.showMessageDialog(null, "Remplissez les Champs Vides ");
					}
					
	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(51, 398, 98, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(194, 398, 115, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed1(ActionEvent e) {
				
				String sql = "update etudiant set prenom = ?,,nom =?,cin=?,tel=?,datenaissance=?,adresse=? ";
			try {
				prepared =cnx.prepareStatement(sql);
				prepared.setString(1, prenomfield.getText().toString());
				prepared.setString(2, nomfield.getText().toString());
				prepared.setString(3, cinfield.getText().toString());
				prepared.setString(4, Numtelfield.getText().toString());
				prepared.setString(5, naissancefield.getText().toString());
				prepared.setString(6, adressefield.getText().toString());
				prepared.execute();
				UpdateTable();
				JOptionPane.showMessageDialog(null, "Etudiant est modifié");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}

			private void UpdateTable() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(334, 398, 100, 32);
		contentPane.add(btnNewButton_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 338, 131, 30);
		contentPane.add(comboBox);
		
		
		
		
		JLabel lblNewLabel_9 = new JLabel("Precedent...");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				Fermer();
			}
			
		});
		lblNewLabel_9.setForeground(Color.DARK_GRAY);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setBounds(34, 63, 121, 32);
		contentPane.add(lblNewLabel_9);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(486, 92, 488, 336);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBounds(653, 282, 1, 1);
		contentPane.add(table);
		
		JButton btnNewButton_3 = new JButton("Actualiser");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setBounds(486, 70, 98, 23);
		contentPane.add(btnNewButton_3);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 1, 984, 461);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\bleu.png"));
		contentPane.add(lblNewLabel);

	}
	public void UpdateTable(){
		String  sql = "select * from etudiant ";
		try {
			prepared =cnx.prepareStatement(sql);
			 resultat = prepared.executeQuery();
			table.setModel((DbUtils.resultSetToTableModel(resultat)));
		} catch (SQLException e) 
					 { JOptionPane.showMessageDialog(null,"");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
