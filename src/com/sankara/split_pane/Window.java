package com.sankara.split_pane;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Window extends JFrame{
	
	JSplitPane split1;
	JSplitPane split2;
	JSplitPane split3;
	JPanel pan1;
	JPanel pan2;
	JPanel pan3;
	JPanel pan4;

	public Window() {
		this.setTitle("Split Panel");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		pan1 = new JPanel();
		pan1.setBackground(Color.RED);
		
		pan2 = new JPanel();
		pan2.setBackground(Color.BLUE);
		
		pan3 = new JPanel();
		pan3.setBackground(Color.GREEN);
		
		pan4 = new JPanel();
		pan4.setBackground(Color.BLACK);
		
		split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan1, pan2);
		split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan3, pan4);
		split3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, split1, split2);
		
		this.setContentPane(split3);
		this.setVisible(true);
		
	}
}
