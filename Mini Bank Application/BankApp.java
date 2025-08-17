package com.jdbc.mini_bank_app;
import java.util.*;
public class BankApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		BankDAO dao = new BankDAO();
		
		while(true) {
			System.out.println("1. Create Account\n2. View Balance\n3. Deposite Amount\n4. Withdraw Amount\n5. Transfer Amount\n6. View Account Details\n7. Exit");
	        System.out.print("Enter choice: ");
	        int ch = sc.nextInt();
	        System.out.println("--------------------------------------------");
	        switch (ch) {
				case 1: {
					System.out.print("Enter the Account no: ");
					int acc = sc.nextInt();
					System.out.print("Enter the Name: ");
					String name = sc.next();
					System.out.print("Enter the Balance: ");
					double bal = sc.nextDouble();
					
					dao.createBankAccount(new Account(acc, name, bal));
					System.out.println("--------------------------------------------");
					break;
				}
				
				case 2: {
					System.out.print("Enter the Account no: ");
					int acc = sc.nextInt();
					System.out.println("Balance: "+dao.getBalance(acc));
					System.out.println("--------------------------------------------");
					break;
				}
				
				case 3:{
					System.out.print("Enter the Account no: ");
					int acc = sc.nextInt();
					System.out.print("Enter the Amount to deposite: ");
					double amount = sc.nextDouble();
					
					dao.depositeAmount(acc, amount);
					System.out.println("--------------------------------------------");
					break;
				}
				
				case 4:{
					System.out.print("Enter the Account no: ");
					int acc = sc.nextInt();
					System.out.print("Enter the Amount to Withdraw: ");
					double amount = sc.nextDouble();
					
					dao.withdrawAmount(acc, amount);
					System.out.println("--------------------------------------------");
					break;
				}
				
				case 5:{
					System.out.print("Enter Your Account no: ");
					int from_acc = sc.nextInt();
					System.out.print("Enter the Account no to whome you want to Transfer: ");
					int to_acc = sc.nextInt();
					System.out.print("Enter the Amount to Transfer: ");
					double amount = sc.nextDouble();
					
					dao.transfer(from_acc, to_acc, amount);
					System.out.println("--------------------------------------------");
					break;
				}
				
				case 6:{
					System.out.print("Enter Your Account no: ");
					int acc_no = sc.nextInt();
					
					dao.getDetails(acc_no);
					
					System.out.println("--------------------------------------------");
					break;
				}
				
				case 7:{
					System.out.println("Byee....");
					System.exit(0);
				}
			}
		}
		

	}

}
