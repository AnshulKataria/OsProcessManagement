//package classes;
//
//import java.util.ArrayList;
//
//public class ProcessScheduling {
//	private int totalProcesses=12;
//	private int burstCountForCPUBoundProcess=8;
//	private int numberOfProcessors=4; //to implement multiple processors
//	private int activeCPUBoundProcess; // to store number of CPU bound process remaining
//	private int contextSwitchingTime=2;
//
//	static int currentTime=0; // to store to current time in ms
//
//	ArrayList<Process> processes;
//	Process nextShortestJob;
//
//	public Process findNextShortestJob(){
//
//		if(processes.size()!=0){
//			int indexOfShortest=0;
//			nextShortestJob=processes.get(0);
//			for(int i=1; i< processes.size();i++){
//				// obtaining the shortest burst job
//				if(processes.get(i).getBurstTime()<nextShortestJob.getBurstTime()){
//					nextShortestJob=processes.get(i);
//					indexOfShortest=i;
//				}
//			}
//			processes.remove(indexOfShortest);
//			return nextShortestJob;
//		}
//		return null;
//	}
//
//	public void scheduleSJFWithoutPreemption(ArrayList<Process> processes){
//
//		Process inCPUProcess=null;
//		if(processes.size()!=0){
//			inCPUProcess=findNextShortestJob();
//		}
//		while(processes.size()!=0 && activeCPUBoundProcess > 0){
//			// serve each process till all the process get over or CPU bound process get finish
//			System.out.println("enter");
//			System.out.print(inCPUProcess.getProcessType()+" ");
//			System.out.print("Process with process id: "+inCPUProcess.getProcessID());
//			if(inCPUProcess.getProcessType().equals(ProcessType.CPUBOUND)){
//				System.out.print(" with burst number ["+inCPUProcess.getBurstCount()+"] ");
//			}
//			System.out.println(" starts at time ["+currentTime+"] ");
//
//			currentTime=currentTime+inCPUProcess.getBurstTime();
//
//			System.out.print(inCPUProcess.getProcessType()+" ");
//			System.out.print("Process with process id: "+inCPUProcess.getProcessID());
//			if(inCPUProcess.getProcessType().equals(ProcessType.CPUBOUND)){
//				System.out.print(" with burst number ["+inCPUProcess.getBurstCount()+"] ");
//			}
//			System.out.println(" ends at time ["+currentTime+"] ");
//			currentTime=currentTime+2; // because of context switch
////			if(inCPUProcess.getProcessType().equals(ProcessType.INTERACTIVE)){
////				processes.add(inCPUProcess);
////			}
////			else if (inCPUProcess.getProcessType().equals(ProcessType.CPUBOUND)){
////				inCPUProcess.setBurstCount(inCPUProcess.getBurstCount()-1);
////				if(inCPUProcess.getBurstCount()==0){
////					// do nothing
////					setActiveCPUBoundProcess(getActiveCPUBoundProcess()-1);
////				}
////				else {
////					// add again in the queqe
////					processes.add(inCPUProcess);
////				}
////			}
//			inCPUProcess=findNextShortestJob();
//			if(getActiveCPUBoundProcess()==0 || processes.size()==0){
//				System.out.println("All CPU bound processes ended-- sheduling ended");
//			}
//		}
//
//	}
//	public void scheduleSJFWithPreemption(){
//
//	}
//	public void scheduleRoundRobin(){
//
//	}
//	public void schedulePreemptivePriority(){
//
//	}
//	public ProcessScheduling() {
//		//processes= new Process[totalProcesses];
//		//initializeProcesses();
//
//	}
//	public ProcessScheduling(int totalProcesses) {
//		this.totalProcesses=totalProcesses;
//		//processes= new Process[totalProcesses];
//		//initializeProcesses();
//	}
//	public void initializeProcesses(){
//		//  create each process and initialization
//		processes=new ArrayList<Process>();
//		int numberOfInteractive=0;
//		int numberOfCPUBound=0;
//		numberOfInteractive=(int)(getTotalProcesses()*(0.8));
//		numberOfCPUBound=getTotalProcesses()-numberOfInteractive;
//		int processID=0;
//		Process process;
//		int burstTime=0;
//		int burstCount=0;
//		int blockTime=0;
//		RandomNumberGenerator interactiveBurstTimeGenerator = new RandomNumberGenerator(20,200);
//		RandomNumberGenerator interactiveBlockTimeGenerator = new RandomNumberGenerator(1000,4500);
//		RandomNumberGenerator CPUBoundBurstTimeGenerator = new RandomNumberGenerator(200,3000);
//		RandomNumberGenerator CPUBoundBlockTimeGenerator = new RandomNumberGenerator(1200,3200);
//		for(int index=1; index<= numberOfInteractive; index++){
//			burstTime= interactiveBurstTimeGenerator.generateNumber();
//			blockTime=interactiveBlockTimeGenerator.generateNumber();
//			process= new Process(ProcessType.INTERACTIVE, burstCount, burstTime, ProcessState.READY, blockTime, processID+1,0,0);
//			System.out.println("process id : "+process.getProcessID());
//			System.out.println("burst time: "+process.getBurstTime());
//			processes.add(process);
//			process=null;
//			processID++;
//		}
//		for (int index=1; index<= numberOfCPUBound; index++){
//			burstTime= CPUBoundBurstTimeGenerator.generateNumber();
//			burstCount=getBurstCountForCPUBoundProcess();
//			blockTime=CPUBoundBlockTimeGenerator.generateNumber();
//			process= new Process(ProcessType.CPUBOUND, burstCount, burstTime, ProcessState.READY, blockTime, processID+1,0,0);
//			System.out.println("process id : "+process.getProcessID());
//			System.out.println("burst time: "+process.getBurstTime());
//			System.out.println("burst count: "+process.getBurstCount());
//			processes.add(process);
//			process=null;
//			processID++;
//		}
//		setActiveCPUBoundProcess(numberOfCPUBound);
//
//	} 
//
//	public int getTotalProcesses() {
//		return totalProcesses;
//	}
//	public void setTotalProcesses(int totalProcesses) {
//		this.totalProcesses = totalProcesses;
//	}
//	public int getBurstCountForCPUBoundProcess() {
//		return burstCountForCPUBoundProcess;
//	}
//	public void setBurstCountForCPUBoundProcess(int burstCountForCPUBoundProcess) {
//		this.burstCountForCPUBoundProcess = burstCountForCPUBoundProcess;
//	}
//	public int getActiveCPUBoundProcess() {
//		return activeCPUBoundProcess;
//	}
//	public void setActiveCPUBoundProcess(int activeCPUBoundProcess) {
//		this.activeCPUBoundProcess = activeCPUBoundProcess;
//	}
//	public int getNumberOfProcessors() {
//		return numberOfProcessors;
//	}
//	public void setNumberOfProcessors(int numberOfProcessors) {
//		this.numberOfProcessors = numberOfProcessors;
//	}
//	public int getContextSwitchingTime() {
//		return contextSwitchingTime;
//	}
//	public void setContextSwitchingTime(int contextSwitchingTime) {
//		this.contextSwitchingTime = contextSwitchingTime;
//	}
//
//
//	public static void main(String[] args) {
//		ProcessScheduling processScheduling; 
//		// taking argument out
//		if(args.length!=0){
//			if(args[0]!=null){
//				int numberOfProcesses=0;
//				try { 
//					numberOfProcesses=Integer.parseInt(args[0]); 
//				} catch(NumberFormatException e) { 
//					System.out.println("First Argument pass is not a number. Please try again !!");
//					System.exit(0);
//				}
//				processScheduling=new ProcessScheduling(numberOfProcesses);
//				if(args[1]!=null){
//					int numberOfBurstForCPUBoundProcess=0;
//					try { 
//						numberOfBurstForCPUBoundProcess=Integer.parseInt(args[1]); 
//					} catch(NumberFormatException e) { 
//						System.out.println("Second Argument pass is not a number. Please try again !!");
//						System.exit(0);
//					}
//					processScheduling.setBurstCountForCPUBoundProcess(numberOfBurstForCPUBoundProcess);
//				}
//				if(args[2]!=null){
//					int numberOfProcessors=0;
//					try { 
//						numberOfProcessors=Integer.parseInt(args[2]); 
//					} catch(NumberFormatException e) { 
//						System.out.println("Third Argument pass is not a number. Please try again !!");
//						System.exit(0);
//					}
//					processScheduling.setTotalProcesses(numberOfProcessors);
//				}
//			}	
//		}
//
//		processScheduling=new ProcessScheduling();
//		processScheduling.initializeProcesses();
//		System.out.println("start");
//		processScheduling.scheduleSJFWithoutPreemption(processScheduling.processes);
//
//
//
//	}
//
//}
