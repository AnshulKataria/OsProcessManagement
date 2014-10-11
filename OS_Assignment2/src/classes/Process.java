package classes;

public class Process {

	/*
	 * Creator : Anshul Kataria
	 * RIN : 	 661403632
	 * Email: 	 katara@rpi.edu
	 */

	private int burstCount; // how many burst is remaining
	private int burstTime;  // random burst time assigned
	private int blockTime;
	private String processType; // to store the type of process i.e CPUBound or Interactive
	private String state; // to store state i.e  ready, waiting or processing
	private int processID;
	private int startTime;
	private int endTime;
	private int burstTimeRemaining;
	private int turnAroundTime;
	private int priority;
	private int timeToAvailable;
	private int totalBurstTime;
	
	public static final int PROCESS = 1;
	public static final int  BURST= 2;
	
	RandomNumberGenerator interactiveBurstTimeGenerator = new RandomNumberGenerator(20,200);
	RandomNumberGenerator interactiveBlockTimeGenerator = new RandomNumberGenerator(1000,4500);
	RandomNumberGenerator CPUBoundBurstTimeGenerator = new RandomNumberGenerator(200,3000);
	RandomNumberGenerator CPUBoundBlockTimeGenerator = new RandomNumberGenerator(1200,3200);
	//RandomNumberGenerator interactiveAvailableTimeGenerator = new RandomNumberGenerator(1000,4500);
	//RandomNumberGenerator CPUBoundAvailableTimeGenerator = new RandomNumberGenerator(200,3000);

	public int generateBurstTime(){
		if(processType.equals(ProcessType.INTERACTIVE)){
			return interactiveBurstTimeGenerator.generateNumber();
		}
		else{
			return CPUBoundBurstTimeGenerator.generateNumber();
		}	
	}
	public int generateAvailableTime(){
		if(processType.equals(ProcessType.INTERACTIVE)){
			return interactiveBlockTimeGenerator.generateNumber();
		}
		else{
			return CPUBoundBlockTimeGenerator.generateNumber();
		}	
	}
	
	
	public Process(String processType, int burstCount,  String state,  int processID, int startTime, int endTime, int timeToAvailable) {
		this.processType= processType;
		this.burstCount=burstCount;
		this.state=state;
		this.processID=processID;
		this.startTime=startTime;
		this.endTime=endTime;
		this.totalBurstTime=0;
		this.burstTime=generateBurstTime();
		totalBurstTime=totalBurstTime+burstTime;
		this.burstTimeRemaining=burstTime;
		this.timeToAvailable=timeToAvailable;
	}

	public int turnAroundTime(int type, int currentTime){
		if(type==BURST){
			return (currentTime-getTimeToAvailable());
		}
		else if(type==PROCESS){ 
			return (currentTime-startTime);
		}
		return 0;
		
	}
	public int waitTime(int type, int currentTime){
		if(type==BURST){
			return (currentTime-getTimeToAvailable()-getBurstTime());
		}
		else if(type==PROCESS){ 
			return (currentTime-startTime-(totalBurstTime));
		}
		return 0;
		
	}
	public int getBurstCount() {
		return burstCount;
	}

	public void setBurstCount(int burstCount) {
		this.burstCount = burstCount;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(int blockTime) {
		this.blockTime = blockTime;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getBurstTimeRemaining() {
		return burstTimeRemaining;
	}

	public void setBurstTimeRemaining(int burstTimeRemaining) {
		this.burstTimeRemaining = burstTimeRemaining;
	}
	
	public int getTurnAroundTime() {
		return turnAroundTime;
	}
	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getTimeToAvailable() {
		return timeToAvailable;
	}
	public void setTimeToAvailable(int timeToAvailable) {
		this.timeToAvailable = timeToAvailable;
	}
	public int getTotalBurstTime() {
		return totalBurstTime;
	}
	public void setTotalBurstTime(int totalBurstTime) {
		this.totalBurstTime = totalBurstTime;
	}

}
