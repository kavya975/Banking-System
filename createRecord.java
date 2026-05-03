package jdbcA;
import bank.*;
import java.util.*;
public class createRecord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    int choice=0,i=1,customer_id=0;
    double amount=0;
    
    while(choice<=i) {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");
        System.out.println("Enter your choice:");
        choice = sc.nextInt();
        if (choice == 1) {
    System.out.println("enter customer id:");
    customer_id=sc.nextInt();
    sc.nextLine();
    System.out.println("enter name:");
    String name=sc.nextLine();
    System.out.println("enter address:");
    String address=sc.nextLine();
    System.out.println("enter city:");
    String city=sc.nextLine();
    System.out.println("enter pincode:");
    int pincode=sc.nextInt();
    System.out.println("enter phone number:");
    sc.nextLine();
    String phoneno=sc.nextLine();
    System.out.println("enter initial deposite:");
    double initial_deposite=sc.nextDouble();
    sc.nextLine();
    double balance=initial_deposite;
    
    bankins db=new bankins();
    db.insert(customer_id, name,address,city,pincode,phoneno,initial_deposite,balance);
	}
    if(choice==2) {
    	 bankins db = new bankins();
         db.deposit(customer_id, amount);
    }
    if(choice==3) {
        bankins db = new bankins();
        db.withdraw(customer_id, amount);
        }
    if(choice==4) {
        bankins db = new bankins();
        db.checkBalance(customer_id);
    }
    if(choice==5) {
    	break;
    }
    
    }

}}
