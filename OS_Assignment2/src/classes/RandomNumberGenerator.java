package classes;

import java.util.Random;

public class RandomNumberGenerator {

	private int start;
	private int end;
	Random rm=new Random();
	int randomNumber;
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public RandomNumberGenerator(int start, int end) {
		this.start=start;
		this.end=end;
		
	}
	
	public int generateNumber(){
		randomNumber = rm.nextInt((end-start)+1)+start;
		return randomNumber;
	}
	
}
