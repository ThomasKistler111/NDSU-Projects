package Simulator;
import java.util.*;
import java.io.*;

public class ProcessX {
	String fileName;
	int currentIndex = 0;
	int processID;
	ArrayList<page> pageList;
	
	public ProcessX(String txtFile, int processid) {
		pageList = new ArrayList<>();
		processID = processid;
		fileName = txtFile;
		readFromFile();
	}
	
	protected void readFromFile() { 
		String line = null;
		InputStream stream = simulator.class.getResourceAsStream(fileName);
		if (stream == null) 
			System.out.println("Resource not located");
		Scanner input = null;
		try {
		 input = new Scanner (stream);
		 while (input.hasNextLine()) {
				line = input.nextLine();
				String[] temp = line.split(",");
				for(String s: temp) {
					pageList.add(new page(Integer.parseInt(s),processID));
				}
			}
		} catch (Exception e) {
			System.out.println("Scanner error");
		}		
	}
	
	/**
	 * Function returns the next page in the list
	 * and decrements the current index
	 * @return Integer page number
	 */
	public page getNextPage() {
		page p = pageList.get(currentIndex);
		currentIndex ++;
		return p;
	}
	
	public boolean hasNextPage() {
		if(currentIndex < pageList.size()) {
			return true;
		}
		return false;
	}
	
	public int numPages() {
		return pageList.size();
	}
	
	/**
	 * Function resets the current index to 0
	 */
	public void resetIndexToZero() {
		currentIndex = 0;
	}
	
	public void printPages() {
		int i = 0;
		for(page p : pageList) {
			System.out.print(p.toString()+", ");
			i++;
			if(i % 25 == 0)System.out.println();
		}
	}
}
