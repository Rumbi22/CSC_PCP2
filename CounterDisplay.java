package clubSimulation;

import java.awt.Color;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JLabel;

// Simple Thread class to update the display of a PeopleCounter object

public class CounterDisplay  implements Runnable {
	
	private PeopleCounter score;
	JLabel waiting;
	JLabel inside;
	JLabel left;

	private Lock counter= new ReentrantLock();
		
	CounterDisplay(JLabel w, JLabel i, JLabel l, PeopleCounter score) {
        this.waiting=w;
        this.inside = i;
        this.left = l;
        this.score=score;
    }
	
	public synchronized void run() { //this thread just updates the display of the counters
        while (true) {
        	try{
        		//counter.lock();
        	//test changes colour when at limit and over limit of people inside
        	if (score.getMax()<score.getInside()) {
        		inside.setForeground(Color.RED);
        	}
        	else if (score.getMax()==score.getInside()) {
        		inside.setForeground(Color.ORANGE);
        	}
        	else inside.setForeground(Color.BLACK);
        	inside.setText("Inside: " + score.getInside() + "    "); 
            waiting.setText("Waiting:" +  score.getWaiting()+ "    " );
            left.setText("Left:" + score.getLeft()+ "    " ); 
        }finally { //counter.unlock();

			}
			}
    }
}
