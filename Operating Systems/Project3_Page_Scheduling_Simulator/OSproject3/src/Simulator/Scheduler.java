package Simulator;
import java.util.*;
public class Scheduler {
	
	public ArrayList<ProcessX> processes;
	public ArrayList<page> allPages;
	boolean rrNotConcurrent;
	int currentIndex;
	public Scheduler(ArrayList<ProcessX> pross, boolean roundRobin) {
		allPages = new ArrayList<>();
		processes = pross;
		rrNotConcurrent = roundRobin;
		currentIndex = 0;
		resetProcesses();
		buildPageList();
	}
	
	public void resetProcesses() {
		for(ProcessX p : processes) {
			p.resetIndexToZero();
		}
	}
	
	public void buildPageList() {
		
		//roundRobin
		if(rrNotConcurrent) {
			int done = 0;
			while(done < processes.size()) {
				for(ProcessX p : processes) {
					if(!p.hasNextPage()) {
						done++;
						break;
					}
					allPages.add(p.getNextPage());
				}
			}
		}else {//consecutive
			for(ProcessX p : processes) {
				while(p.hasNextPage()) {
					allPages.add(p.getNextPage());
				}
			}
		}
	}
	
	public page getNextPage() {
		page p = allPages.get(currentIndex);
		currentIndex++;
		return p;
	}
	
	public void reset() {
		resetProcesses();
		currentIndex = 0;
	}
	
	public boolean hasNextPage() {
		if(currentIndex < allPages.size())
			return true;
		return false;
	}
	
	public int numberOfPages() {
		return allPages.size();
	}
	
	public void printAllPages() {
		int i = 0;
		for(page p : allPages) {
			System.out.print(p.toString()+", ");
			i++;
			if(i % 20 == 0)
				System.out.println();
		}
	}
}
