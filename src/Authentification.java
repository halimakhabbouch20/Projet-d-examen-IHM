import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;






import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class Authentification extends JFrame {

	private JFrame frame;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	 void Fermer(){
			frame.dispose();
		}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,1000, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		cnx =ConnexionMysql.ConnexionDB();
		
		usernamefield = new JTextField();
		usernamefield.setColumns(10);
		usernamefield.setBounds(401, 136, 163, 33);
		frame.getContentPane().add(usernamefield);
		
		passwordfield = new JPasswordField();
		passwordfield.setBounds(401, 186, 163, 33);
		frame.getContentPane().add(passwordfield);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(310, 145, 81, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(310, 194, 91, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernamefield.getText().toString();
				String password = passwordfield.getText().toString();
				String sql = "select username,password from utilisateurs";
				
				try {
					prepared =cnx.prepareStatement(sql);
					resultat= prepared.executeQuery();
					int i = 0;
					if(username.equals("")||password.equals("")){
						JOptionPane.showMessageDialog(null, "Remplissez les Champs Vides");
					}else{
						while(resultat.next()){
							String username1 = resultat.getString("username");
							String password1 = resultat.getString("password");
							if(username1.equals(username)&&password1.equals(password)){
								JOptionPane.showMessageDialog(null, "Connexion Reussite " );
								i = 1;
								
							}
							MenuAdministrateur obj = new MenuAdministrateur();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							Fermer();
						}
						if(i==0)
						JOptionPane.showMessageDialog(null, "Connexion Echouee,Information Incorrect :");
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(401, 241, 163, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe oubli\u00E9");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IndicationPass obj = new IndicationPass();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(476, 219, 102, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("        GESTION DES ETUDIANTS  ECOLE ANONYME");
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel_4.setBounds(167, 0, 664, 67);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\halima\\Desktop\\bleu.png"));
		lblNewLabel.setBounds(0, 0, 984, 461);
		frame.getContentPane().add(lblNewLabel);
	}
}
