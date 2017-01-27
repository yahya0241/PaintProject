import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	public static User user;
	private JButton btnSingup;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					Login frame = new Login();
					frame.setVisible(true);

				} catch (Exception e) {
					System.out.println("create login frame exception!");
				}
			}
		});
	}

	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		username = new JTextField();
		username.setBounds(162, 34, 86, 20);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(64, 36, 66, 17);
		contentPane.add(lblUsername);

		JLabel lblPasword = new JLabel("pasword");
		lblPasword.setBounds(64, 78, 65, 14);
		contentPane.add(lblPasword);

		password = new JPasswordField();
		password.setBounds(162, 75, 86, 20);
		contentPane.add(password);

		JButton btnLogin = new JButton("login");

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// testing user exist
				if (test_Username_Password(password.getPassword())) {

					dispose();

					// go to Paint frame
					Paint frame = new Paint();
					frame.setVisible(true);

				}
				// username or password is wrong
				else {
					JOptionPane
							.showMessageDialog(null,
									"usrname or password is invalid, please try again!");
					username.setText("");
					password.setText("");
				}

			}
		});

		btnLogin.setBounds(291, 33, 89, 23);
		contentPane.add(btnLogin);

		btnSingup = new JButton("Singup");

		btnSingup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// maybe username or password be empty
				if (username.getText().isEmpty()
						|| password.getPassword().equals(null)) {
					JOptionPane.showMessageDialog(null,
							"please enter name and user name completely!");
					return;
				}

				// insert a user into database
				int sqlState = User.signUp(username.getText(),
						password.getPassword());

				// Maybe user be duplicate
				if (sqlState == -1) {
					JOptionPane
							.showMessageDialog(null,
									"this username is used, please select another username:)");
					username.setText("");
					password.setText("");

				} else if (sqlState == 1) {
					JOptionPane.showMessageDialog(null, "inserted:)");
				}
			}
		});

		btnSingup.setBounds(291, 74, 89, 23);
		contentPane.add(btnSingup);
	}

	/**
	 * for testing username and password of user
	 * 
	 * @param pas
	 * @return
	 */
	private boolean test_Username_Password(char[] pas) {
		int k = 0;

		// get all user in database
		ArrayList<User> userlist = User.show_Users();

		boolean result = false;

		for (User user : userlist) {

			if (username.getText().equals(user.getUsename())) {

				char[] password = user.getPassword().toCharArray();
				// compare password with password in database
				for (int i = 0; i < pas.length; i++) {
					try {
						if (pas[i] == password[i]) {
							k++;
						} else {
							k = 0;
							break;
						}

					} catch (ArrayIndexOutOfBoundsException e) {
						k = 0;
						break;
					}
				}
			}
			// if user exist
			if (k == user.getPassword().length()) {
				result = true;
				Login.user = user;
				break;
			}
		}

		return result;

	}
}
