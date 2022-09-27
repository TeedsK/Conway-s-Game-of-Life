package gameOfLife;

/**
 * 
 * @author teeds
 *
 * Starter class
 */
public class Main {
	
	/**
	 * Starts the Java code
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Frame frame = new Frame();
		GameOfLife game = new GameOfLife(frame);
//		Thread t = new Thread() {
//			public void run() {
//				try {
//					Thread.sleep(5000);
//				} catch(Exception e) {}
//				System.out.println("Starting!");
//				game.start(100);
//			}
//		}; t.start();
	}
}