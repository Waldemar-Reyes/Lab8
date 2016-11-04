import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ContactMeGuiPartial extends JFrame
{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private JLabel nameL, emailL, passwordL,reEnterPassL, messageL;
	private JTextField nameTF, emailTF, messageTF;
	private JPasswordField passwordTF,reEnterPassTF;
	private JButton sendB, exitB, clearB;

	private SendButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;
	private ClearButtonHandler cbHandler;

	private JMenuBar  menuBar;
	private JMenu make;
	private JMenuItem byEmail;
	private JMenuItem byFax;
	private JMenuItem byCel;
	private JMenuItem byMail;

	private ButtonGroup genderGroup;
	private JRadioButton female;
	private JRadioButton male;

	public ContactMeGuiPartial()
	{
		nameL = new JLabel("Complete Name: ", SwingConstants.CENTER);
		emailL = new JLabel("Email: ", SwingConstants.CENTER);
		passwordL = new JLabel("Password: ", SwingConstants.CENTER);
		reEnterPassL= new JLabel("Re-enter Password: ", SwingConstants.CENTER);
		messageL = new JLabel("Addtional Info: ", SwingConstants.CENTER);

		nameTF = new JTextField();
		emailTF = new JTextField();
		passwordTF = new JPasswordField();
		reEnterPassTF = new JPasswordField();
		messageTF = new JTextField();

		//SPecify handlers for each button and add (register) ActionListeners to each button.
		sendB = new JButton("Send");
		sbHandler = new SendButtonHandler();
		sendB.addActionListener(sbHandler);

		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		clearB = new JButton("Clear");
		cbHandler = new ClearButtonHandler();
		clearB.addActionListener(cbHandler);


		menuBar = new JMenuBar();
		make = new JMenu("            Contact Me                 ");
		byEmail = new JMenuItem("by Email");
		byFax = new JMenuItem("by Fax");
		byCel = new JMenuItem("by Cellphone");
		byMail = new JMenuItem("by Regular Mail");
		menuBar.add(make);
		make.add(byEmail);
		make.add(byFax);
		make.add(byCel);
		make.add(byMail);

		genderGroup = new ButtonGroup();
		genderGroup.add(female = new JRadioButton("Female"));
		genderGroup.add(male = new JRadioButton("Male"));

		setTitle("Registration Form");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(8, 3));

		//Add things to the pane in the order you want them to appear (left to right, top to bottom)
		pane.add(nameL);
		pane.add(nameTF);
		pane.add(emailL);
		pane.add(emailTF);
		pane.add(passwordL);
		pane.add(passwordTF);
		pane.add(reEnterPassL);
		pane.add(reEnterPassTF);
		pane.add(messageL);
		pane.add(messageTF);
		pane.add(female);
		pane.add(male);

		pane.add(menuBar);

		pane.add(clearB);
		pane.add(sendB);
		pane.add(exitB);

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class SendButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//We use the getText & setText methods to manipulate the data entered into those fields.
			String inputEmail= emailTF.getText();
			String inputPassword= passwordTF.getText();
			String reinputPassword= reEnterPassTF.getText();
			String inputMessage= messageTF.getText();

			if(inputEmail.equals("") || inputPassword.equals("") || reinputPassword.equals("")){

				//Display error message
				JOptionPane.showMessageDialog(null,
						"Wrong input. Rememeber to fill all the spaces",
						"Something is missing!!!",
						JOptionPane.WARNING_MESSAGE);
			}

			//Invalid email address
			else if(!inputEmail.contains("@") || !inputEmail.contains(".")) {
				JOptionPane.showMessageDialog(null,"Email address is wrong",
						"Wrong Email Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			//the password is too long
			else if(inputPassword.length() > 11){
				JOptionPane.showMessageDialog(null,"Wrong password. Rememeber it is 10 characters long",
						"Wrong Password Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			//The password does not match
			else if(!inputPassword.equals(reinputPassword)) {
				JOptionPane.showMessageDialog(null,"Wrong password. Password does not match",
						"Wrong Password Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			//the message is too long
			else if(inputMessage.length() > 81){
				JOptionPane.showMessageDialog(null,"Wrong Message. Rememeber it is 80 characters long",
						"Wrong Message Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			// Gender not selected
			else if((!female.isSelected()) && (!male.isSelected())) {
				JOptionPane.showMessageDialog(null,"Gender not selected",
						"Wrong Gender Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			//all is correct
			else
			{
				JOptionPane.showMessageDialog(null,
						"Your message has been sent.");
			}

			//massageTF.setText("" + area);
		}
	}

	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	public class ClearButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			nameTF.setText("");
			emailTF.setText("");
			passwordTF.setText("");
			reEnterPassTF.setText("");
			messageTF.setText("");
			genderGroup.clearSelection();
		}
	}

	public static void main(String[] args)
	{
		ContactMeGuiPartial contactMe = new ContactMeGuiPartial();
	}
}