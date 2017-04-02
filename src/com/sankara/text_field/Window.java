package com.sankara.text_field;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Window extends JFrame {
	
	private JTextField textField = new JTextField();
	private JPanel motherPane    = new JPanel();
	private JLabel label         = new JLabel("Nice text field : ");
	private JButton button       = new JButton("OK");
	
	
	public Window() {
		this.setTitle("Text Fielding");
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Font police =  new Font("Arial", Font.BOLD, 14);
		textField.setFont(police);
		textField.setPreferredSize(new Dimension(150, 30));
		textField.setForeground(Color.BLUE);
		textField.addKeyListener(new KeyboardListener());
		
		motherPane.setBackground(Color.WHITE);
		motherPane.add(label);
		motherPane.add(textField);
		motherPane.add(button);
		
		this.setContentPane(motherPane);
		this.setVisible(true);
	}
	
	class KeyboardListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			System.out.println("Pressed key code : " + arg0.getKeyCode() + "\n\t-character pressed : " 
														+ arg0.getKeyChar());
			pause();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if(!isNumeric(arg0.getKeyChar())) {
				textField.setText(textField.getText().replace(String.valueOf(arg0.getKeyChar()), ""));
				
				System.out.println("Key released is not a number.");
			}
			else {
				System.out.println("Realeased key code : " + arg0.getKeyCode() + "\n\t-character released : " 
						+ arg0.getKeyChar());
				pause();
			}			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			System.out.println("Typed key code : " + arg0.getKeyCode() + "\n\t-character typed : " 
					+ arg0.getKeyChar());
			pause();
		}
	}
	
	public void pause() {
		try {
			Thread.sleep(500);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isNumeric(char carac) {
		
		try {
			Integer.parseInt(String.valueOf(carac));
		}
		catch(NumberFormatException e) { 
			return false;
		}
		return true;
	}
}
