
public class CustomRunnable implements Runnable {
	
	private BankAccount account;
	private String name;
	
	public CustomRunnable(BankAccount account, String name) {
		this.account = account;
		this.name = name;
	}

	@Override
	public void run() {
		for(int i = 0; i < 26; i++) {
			if(account.getSolde() > 0) {
				account.withdrawal(2);
				System.out.println("Withdrwal performed by " + this.name);
			}
			else {
				System.out.println("You are completely broke.");
			}
		}	
	}
}
