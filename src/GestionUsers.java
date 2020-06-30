import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollBar;

import java.awt.ScrollPane;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GestionUsers extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;

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
					
					GestionUsers frame = new GestionUsers();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionUsers() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cnx =ConnexionMysql.ConnexionDB();
		contentPane.setLayout(null);
		JLabel lblNewLabel_4 = new JLabel("        GESTION DES ETUDIANTS  ECOLE ANONYME");
		lblNewLabel_4.setBounds(165, 11, 685, 75);
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(346, 176, 140, 33);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "select password from utilisateurs where username = ? ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					resultat = prepared.executeQuery();
					if(resultat.next()){
						
						
						String password = resultat.getString("password");
						textField_1.setText(password);
						
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(346, 220, 140, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setBounds(250, 189, 86, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setBounds(250, 226, 86, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(508, 153, 140, 33);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into utilisateurs (username,password)values(?,?)";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					
					if(!textField.getText().equals("")&& !textField_1.getText().equals("")){
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succé");
						UpdateTable();
					}else{
						JOptionPane.showMessageDialog(null, "Remplissez Les Champs Vides");
					}
					
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}

			private void UpdateTable() {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setBounds(508, 197, 140, 33);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql =" delete from utilisateurs where username = ? and password = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "L'utilisateur est Supprimé");
					textField.setText("");
					textField_1.setText("");
					UpdateTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.setBounds(508, 243, 140, 33);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText().toString();
				String sql = "update utilisateurs set password =? where username = ?";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField_1.getText().toString());
					prepared.setString(2, textField.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "L'utilisateur est modifié");
					UpdateTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Precident...");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				Fermer();
			}
		});
		lblNewLabel_3.setBounds(22, 70, 99, 39);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setIcon(null);
		contentPane.add(lblNewLabel_3);
		JLabel lblNewLabel = new JLabel("bleu");
		lblNewLabel.setBounds(0, 6, 984, 461);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\bleu.png"));
		contentPane.add(lblNewLabel);
	}

	protected void UpdateTable() {
		String sql = "select* from utilisateurs";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
}
		

