package Simulator;

public class page {

	public int number;
	public int process;
	
	public page(int value, int processID) {
		number = value;
		process = processID;
	}
	
	public String toString() {
		return "("+number+", "+process+")";
	}
	
	public boolean equals(page p) {
		return p.number == this.number && p.process == this.process;
	}
}
