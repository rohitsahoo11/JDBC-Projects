package com.jdbc.emp_management_app;

public interface EmployeeDAO {
	
	public void addEmployee(Employee emp);
	
	public void getAllEmployee();
	
	public void getEmployeesByName(String name);
	
	public void updateSalary(int emp_id, double amount);
	
	public void deleteEmployee(int emp_id);
}
