package pack.cap.module5;

//Generalization business account,sip,
class BankAccount{
	String accountNumber;
	double balance;
	public BankAccount(String accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	public void deposit(double amount) {
		if( amount>0) {
			balance += amount;
			System.out.println("Deposited successfully : "+ amount + "New Balance :"+ balance);
		}else {
			System.out.println("deposite amount must be positive ");
		}
	}
	public void withDraw(double amount ) {
		if(amount >0 && amount <= balance ) {
			balance -= amount ; 
			System.out.println("withdrqw successfully : "+ amount + "New Balance :"+ balance);
		}else {
			System.out.println("insufficient balance ");
		}
	}
	public void checkBalance() {
		System.out.println("Balance for account "+accountNumber +" $ "+ balance);
	}
}

//specialization 
class SavingsAccount extends BankAccount{
	double intRate;
	public SavingsAccount(String accountNumber, double balance , double intRate) {
		super(accountNumber, balance);
		// TODO Auto-generated constructor stub
		this.intRate = intRate;
	}
public void applyInterest () {
	double intr = balance * intRate ;
	balance += intr;
	System.out.println("Interest of $ "+ intr +" applied New balance "+ balance);
	
		}}
class CheckingAccount extends BankAccount{
	double fee;
	public CheckingAccount(String accountNumber, double balance,double fee) {
		super(accountNumber, balance);
		// TODO Auto-generated constructor stub
		this.fee = fee;
	}
	@Override
	public void withDraw(double amount) {
		// TODO Auto-generated method stub
		super.withDraw(amount);
		if(amount > 0 && amount <= balance) {
			balance -= amount ; 
			balance -= fee;
			System.out.println("withdraw "+amount + " with fee of "+fee);
		}else {
			System.out.println("Insufficieant balance ");
		}
	}

	}
class BusinessAccount extends BankAccount{
	double salary;
	int hike;
	public BusinessAccount(String accountNumber, double balance,double salary,int hike) {
		super(accountNumber, balance);
		this.salary=salary;
		this.hike=hike;
	}
	public void hike(int hike) {
			salary=salary*(hike/100);
	}
	public void deposite_sal(double salary) {
		super.deposit(salary);
		balance+=salary;
		System.out.println("Balance for account after adding salary and hike"+accountNumber +" $ "+ balance);
		
	}
	
}

class TradingAccount extends BankAccount {
    double investment;

    public TradingAccount(String accountNumber, double balance,double investment) {
        super(accountNumber, balance);
        this.investment = investment;
    }

    // Method to buy stocks
    public void buyStocks(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            investment += amount;
            System.out.println("Bought stocks worth $" + amount + ". Remaining Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance to buy stocks.");
        }
    }

    // Method to sell stocks
    public void sellStocks(double amount) {
        if (amount > 0 && amount <= investment) {
            balance += amount;
            investment -= amount;
            System.out.println("Sold stocks worth $" + amount + ". New Balance: $" + balance);
        } else {
            System.out.println("Insufficient stocks to sell.");
        }
    }
}


class Sip extends BankAccount{

	double monthlyContribution;
    double annualInterestRate;
    int monthsInvested;

    public Sip(String accountNumber, double balance, double monthlyContribution, double annualInterestRate) {
        super(accountNumber, balance);
        this.monthlyContribution = monthlyContribution;
        this.annualInterestRate = annualInterestRate;
        this.monthsInvested = 0;
    }

    // Method to contribute monthly
    public void investMonthly() {
        balance += monthlyContribution;
        monthsInvested++;
        System.out.println("Invested $" + monthlyContribution + " for month " + monthsInvested + 
                ". New Balance: $" + balance);
    }

    // Method to apply annual interest
    public void applyAnnualInterest() {
        double interest = balance * (annualInterestRate / 100);
        balance += interest;
        System.out.println("Annual interest of $" + interest + " applied. New Balance: $" + balance);
    }
}
	

public class Gen_Spe_Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  SavingsAccount sa = new SavingsAccount("SA123", 5000, 0.03);
  sa.deposit(10000);
  sa.applyInterest();// specialization apply for savingAccount 
  
  
  CheckingAccount ca = new CheckingAccount("CA456", 4000, 2.5);
  ca.deposit(1000);
  ca.withDraw(200);//specialization withdraw with fee for checkingAccount 
  
  TradingAccount ta = new TradingAccount("TA789", 10000,0);
  ta.buyStocks(3000);
  ta.sellStocks(1500);

  // Sip example
  Sip sip = new Sip("SIP987", 2000, 500, 6);
  sip.investMonthly();
  sip.investMonthly();
  sip.applyAnnualInterest();
  
  
	}

}
