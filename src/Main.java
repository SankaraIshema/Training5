
public class Main {
	
	public static void main(String[] args) {
		
		BankAccount account1 = new BankAccount();
		BankAccount account2 = new BankAccount();
		
		Thread thread1 = new Thread(new CustomRunnable(account1, "thread1"));
		Thread thread2 = new Thread(new CustomRunnable(account1, "thread2"));
		thread1.start();
		thread2.start();
	}
}
