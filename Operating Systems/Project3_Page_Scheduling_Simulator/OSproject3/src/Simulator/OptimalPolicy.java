package Simulator;
import java.util.LinkedList;

public class OptimalPolicy {
	Scheduler scheduler;
	int bufferSize;
	double faultRate = 0.0;
	double numberOfFaults = 0;
	double numberOfAttempts = 0;
	LinkedList<page> buffer;
	
	public OptimalPolicy(Scheduler sched, int buffSize) {
		scheduler = sched;
		bufferSize = buffSize;
		buffer = new LinkedList<>();
		scheduler.reset();
	}
	
	public double run() {

		for(int i = 0; scheduler.hasNextPage(); i++) {
			page currentPage = scheduler.getNextPage();
			
			if(bufferIsFull()) {
				if(!pageInBuffer(currentPage)) {//if page is not in buffer
					this.replaceFarthestInFuture(currentPage);
					numberOfFaults += 1.0;
				}
				//if the page is in the buffer, do nothing
				
			}else {//if the buffer is not full
				buffer.add(currentPage);
			}
			numberOfAttempts+=1.0;
			
		}
		return percentFaultRate();
	}
	
	public void replaceFarthestInFuture(page toInsert) {
		int mostFuturistic = 0;
		int indexOfRemoval = 0;
		int i = 0;
		//get future of pages
		for(page p : buffer) {
			int f = futurenessOf(p);
			if(f > mostFuturistic) {
				mostFuturistic = f;
				indexOfRemoval = i;//save index to remove
			}
			i++;
		}
		buffer.remove(indexOfRemoval);
		buffer.add(indexOfRemoval, toInsert);
	}
	
	protected int futurenessOf(page p) {
		int futureness = 0;
		for(int i = scheduler.currentIndex + 1; i < scheduler.allPages.size(); i++) {
			if(scheduler.allPages.get(i).equals(p)) {
				return futureness;
			}
			futureness++;
		}
		return futureness;
	}
	
	public boolean pageInBuffer(page p) {
		if(buffer.size() == 0) return false;
		for(page pg : buffer) {
			if(p.equals(pg)) {
				return true;
			}
		}
		return false;//page must not be in buffer
	}
	
	public boolean bufferIsFull() {
		if(buffer.size() < bufferSize) {
			return false;
		}
		return true;
	}
	
	public double percentFaultRate() {
		faultRate = (numberOfFaults / numberOfAttempts) * 100;
		return faultRate;
	}
	
	public double getFaultRate() {
		return percentFaultRate();
	}
	
}
