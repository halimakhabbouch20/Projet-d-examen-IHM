import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuAdministrateur extends JFrame {

	private JPanel contentPane;
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
					MenuAdministrateur frame = new MenuAdministrateur();
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
	public MenuAdministrateur() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_4 = new JLabel("        GESTION DES ETUDIANTS  ECOLE ANONYME");
		lblNewLabel_4.setBounds(165, 11, 685, 75);
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUsers obj = new GestionUsers();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				Fermer();
			}
		});
		btnNewButton.setBounds(10, 144, 173, 184);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\ajouter.jpg"));
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Utilisateurs");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 339, 173, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEtudiants obj = new GestionEtudiants();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				Fermer();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\gestion.jpg"));
		btnNewButton_1.setBounds(215, 144, 164, 184);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des Etudiants");
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(225, 339, 154, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\note.png"));
		btnNewButton_2.setBounds(413, 144, 164, 184);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gestion des Notes");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(423, 339, 154, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\fi.jpg"));
		btnNewButton_3.setBounds(612, 144, 164, 184);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_5 = new JLabel("Gestion des Filieres");
		lblNewLabel_5.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(624, 339, 152, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\matiere.png"));
		btnNewButton_4.setBounds(810, 144, 164, 184);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_6 = new JLabel("Gestion des Mati\u00E8res");
		lblNewLabel_6.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(820, 339, 144, 14);
		contentPane.add(lblNewLabel_6);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 984, 461);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\bleu.png"));
		contentPane.add(lblNewLabel);
	}

}
