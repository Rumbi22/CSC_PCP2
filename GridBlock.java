package clubSimulation;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// GridBlock class to represent a block in the club.
// only one thread at a time "owns" a GridBlock

public class GridBlock {
	private int isOccupied; 
	private final boolean isExit;  //is this the exit door?
	private final boolean isBar; //is it a bar block?
	private final boolean isDance; //is it the dance area?
	private int [] coords; // the coordinate of the block.

	private Lock gridlock= new ReentrantLock();
	
	GridBlock(boolean exitBlock, boolean barBlock, boolean danceBlock) throws InterruptedException {
		isExit=exitBlock;
		isBar=barBlock;
		isDance=danceBlock;
		isOccupied= -1;
	}
	
	GridBlock(int x, int y, boolean exitBlock, boolean refreshBlock, boolean danceBlock) throws InterruptedException {
		this(exitBlock,refreshBlock,danceBlock);
		coords = new int [] {x,y};
	}
	
	public synchronized int getX() {return coords[0];}
	
	public synchronized int getY() {return coords[1];}


	//Use of lock to make sure one thread has access to a block.
	public boolean get(int threadID) throws InterruptedException {
		gridlock.lock();
		try {
			if (isOccupied == threadID) {
				return true; // thread Already in this block
			}
			if (isOccupied >= 0) {
				return false; // space is occupied
			}
			isOccupied = threadID; // set ID to thread that had block
			return true;
		} finally {
			gridlock.unlock(); // Ensure the lock is always released
		}
	}


	public  void release() {
		isOccupied=-1;
	}
	
	public  synchronized boolean occupied() {
		if(isOccupied==-1) return false;
		return true;
	}
	
	public synchronized boolean isExit() {
		return isExit;	
	}

	public   boolean isBar() {
		return isBar;
	}
	public   boolean isDanceFloor() {
		return isDance;
	}

}
