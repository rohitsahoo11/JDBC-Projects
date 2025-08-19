package com.jdbc.emp_management_app;
import java.util.*;
public class EmployeeManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EmployeeImp e = new EmployeeImp();
		
		while(true) {
			System.out.println("-------Select Options from below-------");
			System.out.println("1. Add Employee.");
			System.out.println("2. Get All Employee.");
			System.out.println("3. Search Employee by name.");
			System.out.println("4. Update Salary of the Employee.");
			System.out.println("5. Delete Employee for system.");
			System.out.println("6. Exit..");
			System.out.print("Choose from the above option: ");
			int ch = sc.nextInt();
			
			switch (ch) {
				case 1 : {
					System.out.print("Enter the employee id: ");
					int id = sc.nextInt();
					System.out.print("Enter the employee name: ");
					String name = sc.next();
					System.out.print("Enter the employee department: ");
					String dep = sc.next();
					System.out.print("Enter the employee salary: ");
					double sal = sc.nextDouble();
					
					e.addEmployee(new Employee(id, name, dep, sal));
					System.out.println("-----------------------------------------");
					break;
				}
				case 2:{
					e.getAllEmployee();
					System.out.println("-----------------------------------------");
					break;
				}
				case 3:{
					System.out.print("Enter the name to search: ");
					String name = sc.next();
					e.getEmployeesByName(name);
					System.out.println("-----------------------------------------");
					break;
				}
				case 4:{
					System.out.print("Enter the emp id: ");
					int id = sc.nextInt();
					System.out.print("Enter the amount that the employee will get hike: ");
					double sal = sc.nextDouble();
					e.updateSalary(id, sal);
					System.out.println("-----------------------------------------");
					break;
				}
				case 5:{
					System.out.print("Enter the emp id: ");
					int id = sc.nextInt();
					e.deleteEmployee(id);
					System.out.println("-----------------------------------------");
					break;
				}
				case 6:{
					System.out.println("Byeee");
					System.exit(0);
				}
			
			}			
		}
	}

}
