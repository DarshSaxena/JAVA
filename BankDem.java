interface Bank
{
void deposit(double amount);
void withdraw(double amount);
}

class Account implements Bank
{
double balance;
Account(double initialBalance)
{
balance=initialBalance;
}
public void deposit(double amount)
{
balance+=amount;
System.out.println("Deposited: "+amount);
}
public void withdraw(double amount)
{
if(amount<=balance)
{
balance-=amount;
System.out.println("Withdrawn: "+amount);
}
else
{
System.out.println("Insufficient balance");
}
}
void displayBalance()
{
System.out.println("Current Balance: "+balance);
}
}
class BankDem
{
public static void main(String args[])
{
Account acc=new Account(1000);
acc.displayBalance();
acc.deposit(5);
acc.displayBalance();
acc.withdraw(800);
acc.displayBalance();
acc.withdraw(1500);
}
}
