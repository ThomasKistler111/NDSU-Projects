package Simulator;
import java.util.*;

public class ClockPolicy {
	Scheduler scheduler;
	int bufferSize;
	double faultRate = 0.0;
	double numberOfFaults = 0;
	double numberOfAttempts = 0;
	
	ArrayList<Clockpage> buffer;//simulates circular queue through the
								//...clockReplace function and clockHand
	int clockHand = 0;
	
	private class Clockpage{
		page p; 
		int usedBit = 1;
		public Clockpage(page pg) {
			p = pg;
		}
		
		public int getUsedBit() {return usedBit;}
		public void setUseTo1() {usedBit = 1;}
		public void setUseTo0() {usedBit = 0;}
		
		
		public void setPage(page pg) {p = pg;}
		public page getPage() {return p;}
		
		public boolean equals(Clockpage cp) {return cp.getPage().equals(this.p);}
		public boolean equals(page pg) {return pg.equals(this.p);}
		
		public String toString() {
			return "("+p.toString() +" "+ usedBit + ")";
		}
	}
	
	public ClockPolicy(Scheduler sched, int buffSize) {
		scheduler = sched;
		bufferSize = buffSize;
		buffer = new ArrayList<>();
		scheduler.reset();
	}
	
	public double run() {

		for(int i = 0; scheduler.hasNextPage(); i++) {
			Clockpage currentPage = new Clockpage(scheduler.getNextPage());
			if(bufferIsFull()) {
				if(!pageInBuffer(currentPage)) {//if page is not in buffer
					clockReplace(currentPage);
					numberOfFaults += 1.0;
				}else {//if the page is already in the buffer
					setAlreadyInBuffer(currentPage);
				}
			}else {//if the buffer is not full
				buffer.add(currentPage);
			}
			numberOfAttempts+=1.0;
		}
		return percentFaultRate();
	}

	private void incrementClockHand() {
		if(clockHand + 1 < bufferSize) {
			clockHand ++;
		}else {
			//reset if end of buffer (circular iterator)
			clockHand = 0;
		}
	}
	
	public void clockReplace(Clockpage toInsert) {
		boolean victimFound = false;

		while(!victimFound) {
			Clockpage current = buffer.get(clockHand);
			if(current.getUsedBit() == 0) {
				buffer.remove(clockHand);
				buffer.add(clockHand, toInsert);
				victimFound = true;
			}else {//if usedBit == 1
				buffer.get(clockHand).setUseTo0();
			}
			incrementClockHand();
		}
	}
	
	public void setAlreadyInBuffer(Clockpage cpg) {
		if(buffer.size() == 0)return;
		for(Clockpage pg : buffer) {
			if(pg.equals(cpg)) {
				pg.setUseTo1();
				return;
			}
		}
	}
	
	public boolean pageInBuffer(Clockpage p) {
		if(buffer.size() == 0) return false;
		for(Clockpage pg : buffer) {
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
