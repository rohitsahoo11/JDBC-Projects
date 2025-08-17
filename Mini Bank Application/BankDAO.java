package com.jdbc.mini_bank_app;
import java.sql.*;
import java.util.Properties;



import java.io.*;
public class BankDAO {
	Connection conn;
	
	public BankDAO() {
		try {
			Properties pro = new Properties();
			InputStream input = new FileInputStream("src/config.properties");
			pro.load(input);
			
			String url = pro.getProperty("db.url");
			String userName = pro.getProperty("db.username");
			String password = pro.getProperty("db.password");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,userName,password);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createBankAccount(Account acc) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO BANK_ACCOUNT VALUES (?, ?, ?)");
			ps.setInt(1, acc.getAccountNumber());
			ps.setString(2, acc.getName());
			ps.setDouble(3,acc.getBalance());
			int row = ps.executeUpdate();
			System.out.println(row+" New Account Added Successfully...");
			System.out.println("-------------------------------------------------------------");
		}
		catch(Exception e) {
			e.printStackTrace();
		}		 
	}
	
	public Double getBalance(int acc_no) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT BALANCE FROM BANK_ACCOUNT WHERE ACC_NO = ?");
			
			ps.setInt(1, acc_no);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getDouble("Balance");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void depositeAmount(int acc_no, double amount) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACC_NO = ?");
			double newBalance = getBalance(acc_no) + amount;
			ps.setDouble(1, newBalance);
			ps.setInt(2, acc_no);
			int row = ps.executeUpdate();
			
			System.out.println("Account number "+acc_no+": "+amount+" Amount deposited Successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdrawAmount(int acc_no, double amount) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACC_NO = ?");
			
			double newBalance = getBalance(acc_no) - amount;
			ps.setDouble(1, newBalance);
			ps.setInt(2, acc_no);
			ps.executeUpdate();
			
			System.out.println(amount+" Withdrawn successfully.");
			System.out.println("Balance: "+getBalance(acc_no));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void transfer(int fromAccount, int toAccount, double amount) {
		try {
			
			conn.setAutoCommit(false);
			
		PreparedStatement ps1 = conn.prepareStatement("UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACC_NO = ?");
			
			double fromBalance = getBalance(fromAccount)-amount;
			ps1.setDouble(1, fromBalance);
			ps1.setInt(2, fromAccount);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACC_NO = ?");
			double toBalance = getBalance(toAccount)+amount;
			ps2.setDouble(1, toBalance);
			ps2.setInt(2, toAccount);
			ps2.executeUpdate();
			
			System.out.println("Your balance: "+getBalance(fromAccount));
			
			conn.commit();
		} 
		catch (Exception e) {
			try {
				conn.rollback();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void getDetails(int acc_no) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BANK_ACCOUNT WHERE ACC_NO = ?");
			
			ps.setInt(1, acc_no);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("Account Number: "+rs.getInt("acc_no"));
				System.out.println("Name: "+rs.getString("name"));
				System.out.println("Balance: "+rs.getDouble("balance"));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
