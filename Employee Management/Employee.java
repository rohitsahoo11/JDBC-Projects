package com.jdbc.emp_management_app;

public class Employee {
	private int emp_id;
	private String emp_name;
	private String department;
	private double salary;
	
	public Employee(int emp_id, String emp_name, String department, double salary) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.department = department;
		this.salary = salary;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}
	
	
}
