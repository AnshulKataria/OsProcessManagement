package classes;

import java.util.ArrayList;

public abstract class Scheduling {
	protected int totalProcesses=12;
	protected int burstCountForCPUBoundProcess=8;
	protected int numberOfProcessors=4; //to implement multiple processors
	protected int activeCPUBoundProcess; // to store number of CPU bound process remaining
	protected int contextSwitchingTime=2;

	static int currentTime=0; // to store to current time in ms

	ArrayList<Process> processes;
	Process nextJob;

	public abstract void schedule();
	public abstract Process findNextJob();
	public void scheduleSJFWithPreemption(){

	}
	public void scheduleRoundRobin(){

	}
	public void schedulePreemptivePriority(){

	}
	public Scheduling() {
		//processes= new Process[totalProcesses];
		initializeProcesses();

	}
	public Scheduling(int totalProcesses) {
		this.totalProcesses=totalProcesses;
		//processes= new Process[totalProcesses];
		initializeProcesses();
	}
	public void initializeProcesses(){
		//  create each process and initialization
		processes=new ArrayList<Process>();
		int numberOfInteractive=0;
		int numberOfCPUBound=0;
		numberOfInteractive=(int)(getTotalProcesses()*(0.8));
		numberOfCPUBound=getTotalProcesses()-numberOfInteractive;
		int processID=0;
		Process process;
		int burstTime=0;
		int burstCount=0;
		int blockTime=0;
		int timeToAvailable=currentTime;
		
		for(int index=1; index<= numberOfInteractive; index++){
			process= new Process(ProcessType.INTERACTIVE, burstCount, ProcessState.READY,  processID+1,0,0,timeToAvailable);
			//System.out.println("process id : "+process.getProcessID());
			//System.out.println("burst time: "+process.getBurstTime());
			processes.add(process);
			process=null;
			processID++;
		}
		for (int index=1; index<= numberOfCPUBound; index++){
			burstCount=getBurstCountForCPUBoundProcess();
			process= new Process(ProcessType.CPUBOUND, burstCount,  ProcessState.READY,  processID+1,0,0,timeToAvailable);
			//System.out.println("process id : "+process.getProcessID());
			//System.out.println("burst time: "+process.getBurstTime());
			//System.out.println("burst count: "+process.getBurstCount());
			processes.add(process);
			process=null;
			processID++;
		}
		setActiveCPUBoundProcess(numberOfCPUBound);

	} 

	public int getTotalProcesses() {
		return totalProcesses;
	}
	public void setTotalProcesses(int totalProcesses) {
		this.totalProcesses = totalProcesses;
	}
	public int getBurstCountForCPUBoundProcess() {
		return burstCountForCPUBoundProcess;
	}
	public void setBurstCountForCPUBoundProcess(int burstCountForCPUBoundProcess) {
		this.burstCountForCPUBoundProcess = burstCountForCPUBoundProcess;
	}
	public int getActiveCPUBoundProcess() {
		return activeCPUBoundProcess;
	}
	public void setActiveCPUBoundProcess(int activeCPUBoundProcess) {
		this.activeCPUBoundProcess = activeCPUBoundProcess;
	}
	public int getNumberOfProcessors() {
		return numberOfProcessors;
	}
	public void setNumberOfProcessors(int numberOfProcessors) {
		this.numberOfProcessors = numberOfProcessors;
	}
	public int getContextSwitchingTime() {
		return contextSwitchingTime;
	}
	public void setContextSwitchingTime(int contextSwitchingTime) {
		this.contextSwitchingTime = contextSwitchingTime;
	}
	public void eventNotifier(int event, Process current, Process previous){
		switch (event) {
		case EventType.ENTRY:       		entryEvent(current);
											break;
			
		case EventType.TERMINATE:			terminateEvent(current);
											break;
		
		case EventType.BURSTCOMPLETE:		burstCompleteEvent(current);
											break;

		case EventType.CONTEXT_SWITCH:		contextSwitchEvent(current,previous);
											break;
									
		case EventType.PRIORITY_INCREASED:	priorityIncreasedEvent(current);
											break;

		default:
											break;
		}
		
	}
	private void priorityIncreasedEvent(Process current) {
			System.out.println("Increased priority of "+ current.getProcessType()+" process ID "+current.getProcessID()+" to  due to aging");
		
	}
	private void contextSwitchEvent(Process current, Process previous) {
		System.out.println("Context Switch ( swapping out process ID "+previous.getProcessID()+" for process ID "+current.getProcessID()+")");
		
	}
	private void burstCompleteEvent(Process current) {
		System.out.println(current.getProcessType()+" process ID "+current.getProcessID()+" CPU burst done ( turnaround time "+current.turnAroundTime(Process.BURST, currentTime)+"ms, total wait time "+current.waitTime(Process.BURST, currentTime)+"ms)");
	}
	private void terminateEvent(Process current) {
		System.out.println(current.getProcessType()+" process ID "+current.getProcessID()+" terminated ( average turnaround time "+current.turnAroundTime(Process.PROCESS, currentTime)+"ms, average total wait time "+(current.waitTime(Process.PROCESS, currentTime))+"ms)");
		
	}
	private void entryEvent(Process current) {
		System.out.println(current.getProcessType()+" process ID "+current.getProcessID()+" entered queue ( requires "+current.getBurstTime()+"ms CPU time; priority)");
		
	}


}
