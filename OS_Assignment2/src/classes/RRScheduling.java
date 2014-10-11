package classes;

public class RRScheduling extends Scheduling{
	int timeSlice=100;

	public RRScheduling() {
		super();
	}
	public RRScheduling(int totalProcesses){
		super(totalProcesses);
	}

	@Override
	public void schedule() {
		System.out.println("simulation start");
		Process temp;
		currentTime=0;
		Process inCPUProcess=null;
		if(processes.size()!=0){
			inCPUProcess=findNextJob();
			temp=inCPUProcess;
			while ( activeCPUBoundProcess > 0){
				if(inCPUProcess!=null){
					if(inCPUProcess.getProcessType().equals(ProcessType.INTERACTIVE)){
						System.out.println(inCPUProcess.getProcessType()+" process with process id ["+inCPUProcess.getProcessID()+"] starts on time ["+currentTime+"]");
						int burstRemaining=inCPUProcess.getBurstTimeRemaining();
						if(burstRemaining<=timeSlice){
							currentTime=currentTime+burstRemaining;
							eventNotifier(EventType.BURSTCOMPLETE, inCPUProcess, null);
							System.out.println(inCPUProcess.getProcessType()+" process with process id ["+inCPUProcess.getProcessID()+"] ends on time ["+currentTime+"]");
							// set next burst time and remaining time
							inCPUProcess.setBurstTime(inCPUProcess.generateBurstTime());
							inCPUProcess.setTotalBurstTime(inCPUProcess.getTotalBurstTime()+inCPUProcess.getBurstTime());
							inCPUProcess.setBurstTimeRemaining(inCPUProcess.getBurstTime());
							//***** do some time scheduling*****
							inCPUProcess.setTimeToAvailable(currentTime+inCPUProcess.generateAvailableTime());
							processes.add(inCPUProcess);
							currentTime=currentTime+contextSwitchingTime;
						}
						else {
							//burst require is more than time slice
							currentTime=currentTime+timeSlice;
							burstRemaining=burstRemaining-timeSlice;
							inCPUProcess.setBurstTimeRemaining(burstRemaining);
							processes.add(inCPUProcess);
							System.out.println(inCPUProcess.getProcessType()+" process with process id ["+inCPUProcess.getProcessID()+"] ends on time ["+currentTime+"]");
							currentTime=currentTime+contextSwitchingTime;
						}
					}
					else{
						// CPU BOUND PROCESS
						System.out.println(inCPUProcess.getProcessType()+" process with process id ["+inCPUProcess.getProcessID()+"] burst number ["+(burstCountForCPUBoundProcess-inCPUProcess.getBurstCount()+1)+"] starts on time ["+currentTime+"]");
						int burstRemaining=inCPUProcess.getBurstTimeRemaining();
						if(burstRemaining<=timeSlice){
							currentTime=currentTime+burstRemaining;
							eventNotifier(EventType.BURSTCOMPLETE, inCPUProcess, null);
							System.out.println(inCPUProcess.getProcessType()+" process with process id ["+inCPUProcess.getProcessID()+"] burst number ["+(burstCountForCPUBoundProcess-inCPUProcess.getBurstCount()+1)+"] ends on time ["+currentTime+"]");
							inCPUProcess.setBurstCount(inCPUProcess.getBurstCount()-1);
							if(inCPUProcess.getBurstCount()==0){
								// dont add
								activeCPUBoundProcess=activeCPUBoundProcess-1;
								inCPUProcess.setTurnAroundTime(currentTime-inCPUProcess.getStartTime());
								eventNotifier(EventType.TERMINATE, inCPUProcess, null);
							}
							else{
								inCPUProcess.setBurstTime(inCPUProcess.generateBurstTime());
								inCPUProcess.setTotalBurstTime(inCPUProcess.getTotalBurstTime()+inCPUProcess.getBurstTime());
								inCPUProcess.setBurstTimeRemaining(inCPUProcess.getBurstTime());
								//***** do some time scheduling*****
								inCPUProcess.setTimeToAvailable(currentTime+inCPUProcess.generateAvailableTime());
								processes.add(inCPUProcess);
							}
							currentTime=currentTime+contextSwitchingTime;

						}
						else{
							// burst time is greater than time slice
							currentTime=currentTime+timeSlice;
							burstRemaining=burstRemaining-timeSlice;
							inCPUProcess.setBurstTimeRemaining(burstRemaining);
							processes.add(inCPUProcess);
							System.out.println(inCPUProcess.getProcessType()+" process with process id ["+inCPUProcess.getProcessID()+"] burst number ["+(burstCountForCPUBoundProcess-inCPUProcess.getBurstCount()+1)+"] ends on time ["+currentTime+"]");
							currentTime=currentTime+contextSwitchingTime;
						}
					}
					inCPUProcess=findNextJob();
					if(inCPUProcess!=null){
						eventNotifier(EventType.CONTEXT_SWITCH, inCPUProcess, temp);
						temp=inCPUProcess;
					}
				}	
				else{
					// to increament the time if  all process available time will be late
					currentTime=currentTime+100;
					inCPUProcess=findNextJob();
					if(inCPUProcess!=null){
						eventNotifier(EventType.CONTEXT_SWITCH, inCPUProcess, temp);
						temp=inCPUProcess;
					
					}
				}
			}
			System.out.println("simulation ended");
		}
		else{
			System.out.println("No Process in system");
		}
	}


	@Override
	public Process findNextJob() {
		if(processes.size()!=0){
			nextJob=null;
			for(int i=0; i< processes.size();i++){
				if(processes.get(i).getTimeToAvailable()<= currentTime){
					nextJob=processes.get(i);
					processes.remove(i);
					return nextJob;
				}
			}
		}
		return null;
	}

	public int getTimeSlice() {
		return timeSlice;
	}

	public void setTimeSlice(int timeSlice) {
		this.timeSlice = timeSlice;
	}

}
