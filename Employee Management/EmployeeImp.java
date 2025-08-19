package com.jdbc.emp_management_app;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class EmployeeImp implements EmployeeDAO{
	
	Connection conn;
	public EmployeeImp() {
		
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
	
	
	
	@Override
	public void addEmployee(Employee emp) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO EMPLOYEE_MANAGEMENT VALUES(?, ?, ?, ?)");
			ps.setInt(1,emp.getEmp_id());
			ps.setString(2, emp.getEmp_name());
			ps.setString(3,emp.getDepartment());
			ps.setDouble(4, emp.getSalary());
			int row = ps.executeUpdate();
			System.out.println(row+" Employee Added to system successfully...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void getAllEmployee() {
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE_MANAGEMENT ORDER BY EMP_ID ASC");
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Emp_ID\tEMP_NAME\tDEPARTMENT\tSALARY");
			System.out.println("------------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t "+rs.getString(2)+"\t\t    "+rs.getString(3)+"\t\t"+rs.getDouble(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getEmployeesByName(String name) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE_MANAGEMENT WHERE EMP_NAME LIKE ?");
			ps.setString(1, "%"+name+"%");
			ResultSet rs = ps.executeQuery();
			System.out.println("Emp_ID\tEMP_NAME\tDEPARTMENT\tSALARY");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t "+rs.getString(2)+"\t\t    "+rs.getString(3)+"\t\t"+rs.getDouble(4));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Double getSalary(int emp_id) {
		try {
			PreparedStatement ps = conn.prepareStatement("Select salary from employee_management where emp_id = ?");
			ps.setInt(1, emp_id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getDouble("Salary");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void updateSalary(int emp_id, double amount) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE EMPLOYEE_MANAGEMENT SET SALARY = ? WHERE EMP_ID = ?");
			double newAmount = getSalary(emp_id)+amount;
			ps.setDouble(1, newAmount);
			ps.setInt(2, emp_id);
			int row = ps.executeUpdate();
			System.out.println("Employee id: "+emp_id+" Salary incremented by "+amount);
		} 
		catch (Exception e) {
			
		}
	}
	
	@Override
	public void deleteEmployee(int emp_id) {
		try {
	        PreparedStatement ps = conn.prepareStatement("DELETE FROM EMPLOYEE_MANAGEMENT WHERE EMP_ID = ?");
	        System.out.println("PreparedStatement created");

	        ps.setInt(1, emp_id);
	        System.out.println("Set emp_id to: " + emp_id);

	        int row = ps.executeUpdate();
	        System.out.println("Row affected: " + row);

	        if (row > 0) {
	            System.out.println("Data of Employee with ID " + emp_id + " has been deleted successfully.");
	        } else {
	            System.out.println("No employee found with ID " + emp_id + ". Nothing was deleted.");
	        }

	        ps.close();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
