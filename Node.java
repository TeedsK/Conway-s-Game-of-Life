package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * 
 * @author teeds
 *
 */
public class Node extends JPanel {
	
	public static boolean mouseActive = false;
	
	/**
	 * 0 = top left
	 * 1 = top
	 * 2 = top right
	 * 3 = middle left
	 * 4 = middle right
	 * 5 = bottom left
	 * 6 = bottom
	 * 7 = bottom right
	 */
	private Node[] neighbors;
	
	private MouseAdapter event;
	private boolean alive;
	private boolean nextAlive;
	private int xPosition, yPosition;
	
	/**
	 * 
	 * @param x - the X position
	 * @param y - the Y position
	 * @param darkBackground
	 */
	public Node(int x, int y) {
		
		this.neighbors = new Node[8];
		
		this.xPosition = x;
		this.yPosition = y;
		this.alive = false;
		this.nextAlive = false;
		
		this.setBackground(new Color(0,0,0));
		
		 event = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!alive) {
					alive = true;
					setBackground(new Color(255,255,255));
				} else {
					alive = false;
					setBackground(new Color(0,0,0));
				}
				revalidate();
				repaint();
				
			}
			public void mousePressed(MouseEvent e) {
				mouseActive = true;
			}
			public void mouseReleased(MouseEvent e) {
				mouseActive = false;
			}
			public void mouseEntered(MouseEvent e) {
				if(mouseActive) {
					alive = true;
					setBackground(new Color(255,255,255));
					revalidate();
					repaint();
				}
			}
		};
		
		this.addMouseListener(event);
	}
	
	/**
	 * 
	 * @param position
	 * @param node
	 */
	public void setNeighbor(int position, Node node) {
		this.neighbors[position] = node;
	}
	
	/**
	 * the neighbor nodes 
	 * @param nodes
	 */
	public void setNeighbors(Node[] nodes) {
		this.neighbors = nodes;
	}
	
	/**
	 * to tell if the node is alive or not
	 * @return alive - alive or not
	 */
	public boolean getAlive() {
		return alive;
	}
	
	/**
	 * get the X position
	 * @return - the X position
	 */
	public int getX() {
		return xPosition;
	}
	
	/**
	 * get the Y position
	 * @return the Y position
	 */
	public int getY() {
		return yPosition;
	}
	
	/**
	 * updates the node based on its neighbors
	 */
	public void prepareNextGeneration() {
		int count = 0;
		for(Node node : neighbors) {
			if(node != null && node.getAlive()) 
				count++;
		}
		if(count < 2 || count > 3) {
			this.nextAlive = false;
		} else {
			if(alive) {
				this.nextAlive = true;
			} else if(count == 3) {
				this.nextAlive = true;
			}
		}
	}
	
	/**
	 * updates the nodes to the next generation
	 */
	public void nextGeneration() {
		if(this.nextAlive) {
			this.alive = true;
			this.setBackground(new Color(255,255,255));
		} else {
			this.alive = false;
			this.setBackground(new Color(0,0,0));
		}
	}
}