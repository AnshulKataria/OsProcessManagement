package classes;

public class SJFScheduling extends Scheduling{

	@Override
	public void schedule() {
		Process inCPUProcess=null;
		Process temp;
		if(processes.size()!=0){
			inCPUProcess=findNextJob();
			temp=inCPUProcess;
			while( activeCPUBoundProcess > 0){
				if((inCPUProcess!=null)){
					// serve each process till all the process get over or CPU bound process get finish
					System.out.print(inCPUProcess.getProcessType()+" ");
					System.out.print("Process with process id: "+inCPUProcess.getProcessID());
					if(inCPUProcess.getProcessType().equals(ProcessType.CPUBOUND)){
						System.out.print(" with burst number ["+inCPUProcess.getBurstCount()+"] ");
					}
					System.out.println(" starts at time ["+currentTime+"] ");
					currentTime=currentTime+inCPUProcess.getBurstTime();
					System.out.print(inCPUProcess.getProcessType()+" ");
					System.out.print("Process with process id: "+inCPUProcess.getProcessID());
					if(inCPUProcess.getProcessType().equals(ProcessType.CPUBOUND)){
						System.out.print(" with burst number ["+inCPUProcess.getBurstCount()+"] ");
					}
					System.out.println(" ends at time ["+currentTime+"] ");
					if(inCPUProcess.getProcessType().equals(ProcessType.INTERACTIVE)){
						eventNotifier(EventType.BURSTCOMPLETE,inCPUProcess,null);
						inCPUProcess.setBurstTime(inCPUProcess.generateBurstTime());
						inCPUProcess.setTotalBurstTime(inCPUProcess.getTotalBurstTime()+inCPUProcess.getBurstTime());
						inCPUProcess.setBurstTimeRemaining(inCPUProcess.getBurstTime());
						//***** do some time scheduling*****
						inCPUProcess.setTimeToAvailable(currentTime+inCPUProcess.generateAvailableTime());
						processes.add(inCPUProcess);
						
					}
					else if (inCPUProcess.getProcessType().equals(ProcessType.CPUBOUND)){
						inCPUProcess.setBurstCount(inCPUProcess.getBurstCount()-1);
						eventNotifier(EventType.BURSTCOMPLETE,inCPUProcess,null);
						if(inCPUProcess.getBurstCount()==0){
							// do nothing
							setActiveCPUBoundProcess(getActiveCPUBoundProcess()-1);
							inCPUProcess.setTurnAroundTime(currentTime-inCPUProcess.getStartTime());
							eventNotifier(EventType.TERMINATE,inCPUProcess,null);
							
						}
						else {
							inCPUProcess.setBurstTime(inCPUProcess.generateBurstTime());
							inCPUProcess.setTotalBurstTime(inCPUProcess.getTotalBurstTime()+inCPUProcess.getBurstTime());
							inCPUProcess.setBurstTimeRemaining(inCPUProcess.getBurstTime());
							//***** do some time scheduling*****
							inCPUProcess.setTimeToAvailable(currentTime+inCPUProcess.generateAvailableTime());
							// add again in the queqe
							processes.add(inCPUProcess);
						}
					}
					inCPUProcess=findNextJob();
					if(inCPUProcess!=null){
						eventNotifier(EventType.CONTEXT_SWITCH, inCPUProcess, temp);
						temp=inCPUProcess;
					}
					
					// because of context switch
					currentTime=currentTime+contextSwitchingTime;
				}
				else{
					// icreasing time to find next available process
					currentTime=currentTime+10;
					inCPUProcess=findNextJob();
					if(inCPUProcess!=null){
						eventNotifier(EventType.CONTEXT_SWITCH, inCPUProcess, temp);
						temp=inCPUProcess;
					}
				}
				
			}
			System.out.println("All CPU bound processes ended-- sheduling ended");
		}
	}

	@Override
	public Process findNextJob() {
		if(processes.size()!=0){
			int indexOfShortest=0;
			nextJob=null;
			for(int j=0;j<processes.size();j++){
				if(processes.get(j).getTimeToAvailable()<=currentTime){
					nextJob=processes.get(j);
					indexOfShortest=j;
					break;
				}
			}
			if(nextJob!=null){
			for(int i=indexOfShortest; i< processes.size();i++){
				// obtaining the shortest burst job that is available
				if((processes.get(i).getBurstTime()<nextJob.getBurstTime()) && (processes.get(i).getTimeToAvailable()<=currentTime)){
					
					nextJob=processes.get(i);
					indexOfShortest=i;
				}
			}
			
				processes.remove(indexOfShortest);
				return nextJob;
			}
			
		}
		return null;
	}
	public SJFScheduling() {
		super();
	}
	public SJFScheduling(int totalProcesses){
		super(totalProcesses);
	}
}
