package com.sankara.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class Window extends JFrame{
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuFile     = new JMenu("File");
	private JMenuItem menuItemOpen     = new JMenuItem("Open");
	private JMenuItem menuItemSave     = new JMenuItem("Save");
	private JMenuItem menuItemLaunch   = new JMenuItem("Launch");
	private JMenuItem menuItemExport   = new JMenuItem("Export");
	private JMenuItem menuItemImport   = new JMenuItem("Import");
	private JMenuItem menuItemClose    = new JMenuItem("Close");
	
	private JMenu menuEdit     = new JMenu("Edit");
	private JMenuItem menuItemCopy = new JMenuItem("Copy");
	private JMenuItem menuItemPaste = new JMenuItem("Paste");
	private JMenuItem menuItemCut = new JMenuItem("Cut");
	private JMenuItem menuItemSend = new JMenuItem("Send");
	
	private JMenu menuHelp     = new JMenu("Help");
	
	private JMenu menuSettings = new JMenu("Settings");
	
	private JMenuItem menuItemGeneral  = new JMenuItem("General");
	private JMenuItem menuItemZoom     = new JMenuItem("Zoom");
	private JMenuItem menuItemDisplay  = new JMenuItem("Display");
	private JMenu menuAdvanced = new JMenu("Advanced");
	
	private JCheckBox checkBox1 = new JCheckBox("First Choice");
	private JCheckBox checkBox2 = new JCheckBox("Second Choice");
	
	private JRadioButton radioButton1 = new JRadioButton("Third Choice");
	private JRadioButton radioButton2 = new JRadioButton("Third Choice bis");
	
	public Window() {
		
		setTitle("Menu");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemLaunch);
		menuFile.add(menuItemImport);
		menuFile.add(menuItemExport);
		menuFile.add(menuItemLaunch);
		menuFile.addSeparator();
		menuItemClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		menuFile.add(menuItemClose);
		
		menuEdit.add(menuItemCopy);
		menuEdit.add(menuItemPaste);
		menuEdit.add(menuItemCut);
		menuEdit.add(menuItemSend);
		
		menuHelp.add(menuSettings);
		
		menuSettings.add(menuItemGeneral);
		menuSettings.add(menuItemZoom);
		menuSettings.add(menuItemDisplay);
		menuSettings.add(menuAdvanced);
		
		menuAdvanced.add(checkBox1);
		menuAdvanced.add(checkBox2);
		menuAdvanced.addSeparator();
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		
		menuAdvanced.add(radioButton1);
		menuAdvanced.add(radioButton2);
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);
		
		setJMenuBar(menuBar);
		setVisible(true);
	}

}
