package Pregatire.ExDriveChatPetri;

import DataObjects.DataString;
import Utilities.DataOverNetwork;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ChatGUI_1 extends JFrame implements Runnable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane petriname;
	private JTextPane txtReceived;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatGUI_1 frame = new ChatGUI_1();
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
	public ChatGUI_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		petriname = new JTextPane();
		petriname.setText("1080");
		petriname.setBounds(10, 93, 285, 20);
		contentPane.add(petriname);

		JTextPane txtData = new JTextPane();
		txtData.setText("Write message");
		txtData.setBounds(10, 52, 285, 20);
		contentPane.add(txtData);

		txtReceived = new JTextPane();
		txtReceived.setText("Received message");
		txtReceived.setBounds(10, 21, 285, 20);
		contentPane.add(txtReceived);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Socket s;
				try {
					s = new Socket(InetAddress.getByName("localhost"), Integer.parseInt(petriname.getText()));
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					DataOverNetwork DataToSend = new DataOverNetwork();

					DataToSend.petriObject = new DataString();
					DataToSend.petriObject.SetValue(txtData.getText());
					DataToSend.petriObject.SetName("P_input_user");

					DataToSend.NetWorkPort = Integer.parseInt(petriname.getText());
					oos.writeObject(DataToSend);
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSend.setBounds(10, 124, 285, 44);
		contentPane.add(btnSend);

	}

	@Override
	public void run() {
		txtReceived.setText(Terminal1_OETPN.getDisplayMessage());
	}
}
