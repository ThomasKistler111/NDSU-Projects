package Simulator;
import java.util.*;

public class LRUPolicy {
	Scheduler scheduler;
	int bufferSize;
	double faultRate = 0.0;
	double numberOfFaults = 0;
	double numberOfAttempts = 0;
	ArrayList<LRUpage> buffer;
	
	private class LRUpage{
		page p;
		int timespent;
		public LRUpage(page pg) {
			p = pg;
			timespent = 0;
		}
		public page getPage() {return p;}
		public void setPage(page pg) {p = pg;}
		
		public int getTimesUsed() {return timespent;}
		public void incrementTimesUsed() {timespent++;}
		public void setTimesUsed(int x) {timespent = x;}
		
		public boolean equals(LRUpage pg) {
			return pg.getPage().equals(p);
		}
	}
	
	public LRUPolicy(Scheduler sched, int buffSize) {
		scheduler = sched;
		bufferSize = buffSize;
		buffer = new ArrayList<>();
		scheduler.reset();
	}
	
	public double run() {
	
		for(int i = 0; scheduler.hasNextPage(); i++) {
			page pg = scheduler.getNextPage();
			LRUpage currentPage = new LRUpage(pg);
			if(bufferIsFull()) {
				if(!pageInBuffer(currentPage)) {//if page is not in buffer
					replaceLeastRecentPage(currentPage);
					numberOfFaults++;
				}else {//if the page is in the buffer already
					setPageUsesToZero(currentPage);
				}
				
			}else {//if the buffer is not full 
				buffer.add(currentPage);
			}
			//increment count of uses
			for(LRUpage lru : buffer) {
				lru.incrementTimesUsed();
			}
			numberOfAttempts+=1.0;
			
		}
		return percentFaultRate();
	}
	
	public void setPageUsesToZero(LRUpage pg) {
		
		for(LRUpage p : buffer) {
			if(p.equals(pg)) {
				p.setTimesUsed(0);
			}
		}
	}
	
	public boolean pageInBuffer(LRUpage p) {
		if(buffer.size() == 0) return false;
		for(LRUpage pg : buffer) {
			if(p.equals(pg)) {
				return true;
			}
		}
		return false;//page must not be in buffer
	}
	
	public void replaceLeastRecentPage(LRUpage toInsert) {
		int times = 0;
		LRUpage leastPage = null;
		int index = 0;
		int i = 0;
		for(LRUpage pg : buffer) {
			if(pg.getTimesUsed() > times) {
				times = pg.getTimesUsed();
				leastPage = pg;
				index = i;
			}
			i++;
		}
		buffer.remove(index);
		buffer.add(index, toInsert);
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
