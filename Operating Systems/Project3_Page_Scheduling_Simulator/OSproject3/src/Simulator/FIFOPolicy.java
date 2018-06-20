package Simulator;
import java.util.*;

public class FIFOPolicy {
	
	Scheduler scheduler;
	int bufferSize;
	double faultRate = 0.0;
	double numberOfFaults = 0;
	double numberOfAttempts = 0;
	LinkedList<page> buffer;//fifo linked list
	
	public FIFOPolicy(Scheduler sched, int buffSize) {
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
					buffer.remove();//remove first element in buffer
					buffer.add(currentPage);
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
		faultRate =  (numberOfFaults / numberOfAttempts) * 100;
		return faultRate;
	}
	
	public double getFaultRate() {
		return percentFaultRate();
	}
	
}
