
public class BankAccount {
	
	private int solde = 100;
	
	
	
	public int getSolde() {
		if(solde < 0) {
			System.out.println("You are completely broke.");
		}
		return this.solde;
	}

	public synchronized void withdrawal(int money) {
		solde = solde - money;
		System.out.println("\n\nWithdrawal has been executed"
								+ "\nYou have " + this.getSolde() 
									+ " in your bank account.");
	}
}
