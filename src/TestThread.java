
public class TestThread extends Thread {
	
	public Thread thread;
	
	public TestThread(String name) {
		super(name);
		System.out.println("The state of the thread " 
								+ name + " at instantiation part 1 is : " 
									+ this.getState());
		this.start();
		
		System.out.println("The state of the thread " 
								+ name + " at instantiation part 2 is : " 
									+ this.getState());
	}
	
	public TestThread(String name, Thread thread) {
		super(name);
		this.thread = thread;
		
		System.out.println("The state of the thread " 
								+ name + " at instantiation part 1 is : " 
									+ this.getState());
		
		this.start();
		
		System.out.println("The state of the thread " 
								+ name + " at instantitiation part 2 is : " 
									+ this.getState());
			}

	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println("Satus of " + this.getName() + " = " + this.getState());
			
			if(thread != null) {
				System.out.println("\n\nSimultaneous states of \n\t" + this.getName() 
																+ " = " + this.getState() 
																	+ "\n\t" + thread.getName() + " = " 
																		+ thread.getState() + "\n\n");
			}
		}
	}
}
