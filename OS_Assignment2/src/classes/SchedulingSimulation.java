package classes;


public class SchedulingSimulation {
	//static String typeOfScheduling; 
	static int totalProcesses=12;
	static int burstCountForCPUBoundProcess=8;
	static int numberOfProcessors=4; //to implement multiple processors
	//static int contextSwitchingTime=2;

	public static Scheduling generateSchedulingObject(String schedulingName){
		if(schedulingName.equalsIgnoreCase("sjf")){
			return new SJFScheduling();
		}
		else if(schedulingName.equalsIgnoreCase("sjfwithp")){
			return null;
		}
		else if(schedulingName.equalsIgnoreCase("rr")){
			return new RRScheduling();
		}
		else if(schedulingName.equalsIgnoreCase("priority")){
			return null;
		}
		return null;
	}
	public static void main(String[] args) {
		Scheduling scheduling = null;
		if(args.length!=0){
			// 0-- for no of processes
			// 1-- for number of burst for cpu bound process
			// 2-- for no of processors
			// 3-- for type of scheduling 
			if(args[0]!=null){
				try { 
					totalProcesses=Integer.parseInt(args[0]); 

				} catch(NumberFormatException e) { 
					System.out.println("First Argument pass is not a number. Please try again !!");
					System.exit(0);
				}
				if(args[1]!=null){
					try { 
						burstCountForCPUBoundProcess=Integer.parseInt(args[1]); 

					} catch(NumberFormatException e) { 
						System.out.println("Second Argument pass is not a number. Please try again !!");
						System.exit(0);
					}
				}
				if(args[2]!=null){
					try { 
						numberOfProcessors=Integer.parseInt(args[2]); 
					} catch(NumberFormatException e) { 
						System.out.println("Third Argument pass is not a number. Please try again !!");
						System.exit(0);
					}
				}
				//				if(args[3]!=null){
				//					String schedulingType=args[2];
				//					if(schedulingType.equalsIgnoreCase(SchedulingType.SJF)){
				//						typeOfScheduling=SchedulingType.SJF;
				//					}
				//					else if(schedulingType.equalsIgnoreCase(SchedulingType.SJFWithP)){
				//						typeOfScheduling=SchedulingType.SJFWithP;
				//					}
				//					else if(schedulingType.equalsIgnoreCase(SchedulingType.RR)){
				//						typeOfScheduling=SchedulingType.RR;
				//					}
				//					else  if(schedulingType.equalsIgnoreCase(SchedulingType.PRIORITY)){
				//						typeOfScheduling=SchedulingType.PRIORITY;
				//					}
				//					else {
				//						System.out.println("Fourth argument is invalid. Please try again !!");
				//						System.exit(0);
				//					}
				//				}
			}	
		}
		scheduling=generateSchedulingObject("rr");
		scheduling.setBurstCountForCPUBoundProcess(burstCountForCPUBoundProcess);
		scheduling.setNumberOfProcessors(numberOfProcessors);
		scheduling.setTotalProcesses(totalProcesses);
		System.out.println("start");
		scheduling.schedule();
		System.out.println("end");
	}
}
