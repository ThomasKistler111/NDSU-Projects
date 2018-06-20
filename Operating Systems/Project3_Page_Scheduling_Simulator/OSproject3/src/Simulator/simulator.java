package Simulator;
import java.util.ArrayList;

public class simulator {

	public static void main(String[] args) {

		String txtfile;
		int numFrames = 0;
		
		//command line number of frames (buffer size)
		if (args.length > 0) {
		    try {
		        numFrames = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		}
		
		//initialize processes
		ArrayList<ProcessX> processes = new ArrayList<>();
		
		ProcessX p1 = new ProcessX("/Simulator/pageRequests/p0PageRequest.txt",1);
		processes.add(p1);  
		ProcessX p2 = new ProcessX("/Simulator/pageRequests/p1PageRequest.txt",2);
		processes.add(p2);
		ProcessX p3 = new ProcessX("/Simulator/pageRequests/p2PageRequest.txt",3);
		processes.add(p3);
		ProcessX p4 = new ProcessX("/Simulator/pageRequests/p8PageRequest.txt",4);
		processes.add(p4); 
		ProcessX p5 = new ProcessX("/Simulator/pageRequests/p9PageRequest.txt",5);
		processes.add(p5); 
		//initialize schedulers (for round robin and consecutive)
		Scheduler consec = new Scheduler(processes, false);
		Scheduler rr = new Scheduler(processes, true);
	
		double fifoResult, lruResult, optResult, clockResult;
		
		//output
		System.out.println("Buffer Size: "+numFrames);
		System.out.println();
		
		//consecutive
		System.out.println("Consecutive");
		System.out.println("-----------");
		
		FIFOPolicy fifo = new FIFOPolicy(consec,5);
		fifoResult = fifo.run();
		System.out.println("FIFO Page Fault Rate: "+String.format("%.2f",fifoResult)+"%");
		
		LRUPolicy lrup = new LRUPolicy(consec, 5);
		lruResult = lrup.run();
		System.out.println("LRU Page Fault Rate: " + String.format("%.2f",lruResult)+"%");
		
		ClockPolicy cp = new ClockPolicy(consec, 5);
		clockResult = cp.run();
		System.out.println("Clock Page Fault Rate: " + String.format("%.2f",clockResult)+"%");
		
		OptimalPolicy opt = new OptimalPolicy(consec,5);
		optResult = opt.run();
		System.out.println("Optimal Page Fault Rate: "+String.format("%.2f",optResult)+"%");
		
		//round robin
		System.out.println("\nRound Robin");
		System.out.println("-----------");
		
		FIFOPolicy RRfifo = new FIFOPolicy(rr,5);
		fifoResult = RRfifo.run();
		System.out.println("FIFO Page Fault Rate: "+String.format("%.2f",fifoResult)+"%");
		
		LRUPolicy rrlrup = new LRUPolicy(rr, 5);
		lruResult = rrlrup.run();
		System.out.println("LRU Page Fault Rate: " + String.format("%.2f",lruResult)+"%");
		
		ClockPolicy rrcp = new ClockPolicy(rr, 5);
		clockResult = rrcp.run();
		System.out.println("Clock Page Fault Rate: " + String.format("%.2f",clockResult)+"%");
		
		OptimalPolicy rropt = new OptimalPolicy(rr,5);
		optResult = rropt.run();
		System.out.println("Optimal Page Fault Rate: "+String.format("%.2f",optResult)+"%");
	}

}
