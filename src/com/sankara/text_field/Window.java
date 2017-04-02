package com.sankara.text_field;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class Window extends JFrame {
	
	private JPanel motherPane    = new JPanel();
	private JPanel textFieldPane1    = new JPanel();
	private JPanel textFieldPane2    = new JPanel();
	
	private JLabel label1 = new JLabel("Phone number 1");
	private JLabel label2 = new JLabel("Phone number 2");
	
	private Box box = new Box(BoxLayout.Y_AXIS); 
	private JButton button       = new JButton("OK");
	private JFormattedTextField textField1;
	private JFormattedTextField textField2;
	
	public Window() {
		this.setTitle("Text Fielding");
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			MaskFormatter tel1 = new MaskFormatter("## ### ## ##");
			MaskFormatter tel2 = new MaskFormatter("####-##-##-##");
			
			textField1 = new JFormattedTextField(tel1);
	        textField2 = new JFormattedTextField(tel2);
		}
		catch(ParseException e) {e.printStackTrace();}
		
		Font police =  new Font("Arial", Font.BOLD, 14);
		textField1.setFont(police);
		textField1.setPreferredSize(new Dimension(150, 30));
		textField1.setForeground(Color.BLUE);
		
		textField2.setFont(police);
		textField2.setPreferredSize(new Dimension(150, 30));
		textField2.setForeground(Color.BLUE);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Text of text field 1 : " + textField1.getText());	
				System.out.println("Text of text field 2 : " + textField2.getText());	
			}
		});
		
		textFieldPane1.setPreferredSize(new Dimension(200, 40));
		textFieldPane1.add(label1);
		textFieldPane1.add(textField1);
		
		textFieldPane2.setPreferredSize(new Dimension(200, 40));
		textFieldPane2.add(label2);
		textFieldPane2.add(textField2);

		
		box.add(textFieldPane1);
		box.add(textFieldPane2);
		
		motherPane.setBackground(Color.WHITE);
		motherPane.setLayout(new BorderLayout());
		motherPane.add(box, BorderLayout.CENTER);
		motherPane.add(button, BorderLayout.SOUTH);
		
		this.setContentPane(motherPane);
		this.setVisible(true);
	}
	
}
