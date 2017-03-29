
public class Main {
	
	public static void main(String[] args) {
		
		TestThread thread1 = new TestThread("Herman");
		TestThread thread2 = new TestThread("Albert", thread1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Final status of the thread " + thread1.getName() + " = " + thread1.getState());
		System.out.println("Final status of the thread " + thread2.getName() + " = " + thread2.getState());
	}
}
