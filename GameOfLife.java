package gameOfLife;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/**
 * 
 * @author teeds
 *
 */
public class GameOfLife {
	
	Frame frame; 
	Node[][] nodes;
	boolean started;
	
	/**
	 * @param frame - the Frame object the game is displayed on
	 */
	public GameOfLife(Frame frame) {
		this.frame = frame;
		this.started = false;
		createNodes();
		frame.finishBuildingFrame();
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
            	switch (ke.getID()) {
                case KeyEvent.KEY_PRESSED:
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        start(100);
                    }
                    break;
            	}
            	return false;
            }
        });
		
	}
	
	/**
	 * starts the game of life
	 * @param refresh - the time to wait before refreshing
	 */
	public void start(int refresh) {
		if(!started) {
			started = true;
			Thread t = new Thread() {
				public void run() {
					while(true) {
						for(Node[] row : nodes) {
							for(Node node : row) {
								node.prepareNextGeneration();
							}
						}
						for(Node[] row : nodes) {
							for(Node node : row) {
								node.nextGeneration();
							}
						}
						try {
							Thread.sleep(refresh);
						} catch(Exception e) {}
					}
				}
			}; t.start();
		}
	}
	
	/**
	 * creates the node elements
	 */
	public void createNodes() {
		
		JPanel panel = this.frame.getBackgroundPanel();
		this.nodes = new Node[100][100];
		
		//initializes the nodes
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[i].length; j++) {
				this.nodes[i][j] = new Node(j, i);
				panel.add(this.nodes[i][j]);
			}
		}
		
		//sets the neighbors for each node
		for(int y = 0; y < nodes.length; y++) {
			for(int x = 0; x < nodes[y].length; x++) {
				Node[] temp = new Node[8];
				
				//top
				if(y > 0) {
					//middle top
					temp[1] = this.nodes[y - 1][x];
					if(x > 0) 
						//top left
						temp[0] = this.nodes[y -1][x - 1];
					
					if(x < nodes[x].length - 1) 
						//top right
						temp[2] = this.nodes[y - 1][x + 1];
				}
				
				//middle left
				if(x > 0) {
					temp[3] = this.nodes[y][x - 1];
				}
				
				//middle right
				if(x < nodes[x].length - 1) {
					temp[4] = this.nodes[y][x + 1];
				}
				
				if(y < nodes.length - 1) {
					//bottom middle
					temp[6] = this.nodes[y + 1][x];
					if(x > 0) 
						//top left
						temp[5] = this.nodes[y + 1][x - 1];
					
					if(x < nodes[x].length - 1) 
						//top right
						temp[7] = this.nodes[y + 1][x + 1];
				}
				
				this.nodes[y][x].setNeighbors(temp);
			}
		}
		frame.revalidate();
		frame.repaint();
	}
	
}