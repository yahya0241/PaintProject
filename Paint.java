import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Paint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	static Color color = Color.black;
	public String shape = "";
	private int xbegine, ybegine;

	public static void setC(Color c) {
		Paint.color = c;
	}

	public static Color getC() {
		return color;
	}

	public static JPanel panel = new JPanel();

	public Paint() {

		setTitle("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 559);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel.setBackground(Color.white);
		panel.setBounds(10, 11, 500, 500);
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));

		contentPane.setLayout(null);

		final JButton button = new JButton("\u062E\u0637");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				shape = "line";
			}
		});

		button.setBounds(517, 88, 89, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u062F\u0627\u06CC\u0631\u0647");

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				shape = "circle";

			}
		});

		button_1.setBounds(517, 122, 89, 23);
		contentPane.add(button_1);

		final JButton button_2 = new JButton(
				"\u0645\u0633\u062A\u0637\u06CC\u0644");

		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				shape = "rect";

			}

		});

		button_2.setBounds(517, 156, 89, 23);
		contentPane.add(button_2);

		JLabel label = new JLabel(
				"\u0627\u0646\u062A\u062E\u0627\u0628 \u0631\u0646\u06AF");

		label.setBounds(527, 195, 69, 23);
		contentPane.add(label);

		JButton button_3 = new JButton("\u062E\u0631\u0648\u062C");

		button_3.addActionListener(new ActionListener() {

			// back to Login frame
			public void actionPerformed(ActionEvent e) {

				Login pasword = new Login();
				pasword.setVisible(true);

				shape = "";
				dispose();
			}
		});

		button_3.setBounds(517, 384, 89, 23);
		contentPane.add(button_3);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xbegine = e.getX();
				ybegine = e.getY();

			}

			public void mouseReleased(MouseEvent e) {

				// call for drawing Shapes
				if (shape.equals("rect")) {
					Rect.draw(xbegine, ybegine, e.getX(), e.getY(), color);

				} else if (shape.equals("circle")) {

					Circle.draw(xbegine, ybegine, e.getX(), e.getY(), color);

				} else if (shape.equals("line")) {

					Line.draw(xbegine, ybegine, e.getX(), e.getY(), color);
				}

			}

		});

		JButton button_4 = new JButton("\u0646\u0645\u0627\u06CC\u0634");

		button_4.addActionListener(new ActionListener() {
			// show shapes that drawing before
			public void actionPerformed(ActionEvent e) {

				new Line().show();
				new Rect().show();
				new Circle().show();
			}
		});

		button_4.setBounds(517, 54, 89, 23);
		contentPane.add(button_4);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {

			// get color shape
			public void actionPerformed(ActionEvent e) {

				if (comboBox.getSelectedItem().equals("blue")) {
					Paint.color = Color.blue;

				} else if (comboBox.getSelectedItem().equals("black")) {
					Paint.color = Color.black;

				} else if (comboBox.getSelectedItem().equals("green")) {
					Paint.color = Color.green;

				} else if (comboBox.getSelectedItem().equals("red")) {
					Paint.color = Color.red;

				}
			}
		});

		comboBox.setMaximumRowCount(4);
		comboBox.addItem("black");
		comboBox.addItem("blue");
		comboBox.addItem("green");
		comboBox.addItem("red");

		comboBox.setBounds(527, 229, 59, 33);
		contentPane.add(comboBox);

	}

}
