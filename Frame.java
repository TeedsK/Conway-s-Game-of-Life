package gameOfLife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Frame extends JFrame {
	
	
	private JPanel background;
	private JPanel grid;
	
	/**
	 * Initializes basic JFrame properties
	 */
	public Frame() {
		super("Game of Life");
		
		//When closed it fully terminates frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Sets beginning size properties
		this.setSize(800, 800);
		this.setPreferredSize(new Dimension(800,800));
		this.setLocationRelativeTo(null);
		
		this.background = createBackground();
		this.add(background);
		
		
	}
	
	/**
	 * finishes creating the end of the frame
	 */
	public void finishBuildingFrame() {
		//Packs everything together and makes visible
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Creates the background panel
	 * @return - a basic panel
	 */
	@SuppressWarnings("serial")
	private JPanel createBackground() {
		JPanel background = new JPanel();
		background.setBackground(Color.PINK);
		background.setLayout(new BorderLayout());
		
		GridLayout grid = new GridLayout(100,100);
		grid.setHgap(1);
		grid.setVgap(1);
		JPanel panel = new JPanel();
		panel.setLayout(grid);
		panel.setBackground(Color.BLACK);
		panel.setMinimumSize(new Dimension(500,500));
		panel.setSize(500,500);
		
		this.grid = panel;
		background.add(panel, BorderLayout.CENTER);
		
		return background;
	}
	
	/**
	 * @return - the background panel
	 */
	public JPanel getBackgroundPanel() {
		return this.grid;
	}
}