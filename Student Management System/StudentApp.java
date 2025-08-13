package com.jdbc.student_management_system;
import java.util.*;


public class StudentApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StudentDAO dao = new StudentDAO();
		
		while(true) {
			System.out.println("\n1. Add Student\n2. View Student\n3. Update Student\n4. Delete Students\n5. Exit");
	        System.out.print("Enter choice: ");
	        int ch = sc.nextInt();
        
        
        	switch (ch) {
            case 1:{
    	            System.out.print("Enter ID: ");
    	            int id = sc.nextInt();
    	            System.out.print("Enter Name: ");
    	            String name = sc.next();
    	            System.out.print("Enter Email: ");
    	            String email = sc.next();
    	            System.out.print("Enter Age: ");
    	            int age = sc.nextInt();
    	            dao.addStudent(new Student(id, name, email, age));
    	            break;
            	}
            case 2:{
            	dao.viewStudent();
            	break;
            }
            case 3:{
            	System.out.print("Enter Name: ");
                String name = sc.next();
                System.out.print("Enter Email: ");
                String email = sc.next();
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                dao.updateStudent(new Student(id, name, email, age));
                break;
            }
            case 4:{
            	System.out.print("Enter ID: ");
                int id = sc.nextInt();
                dao.deleteStudent(new Student(id, null, null, 0));
                break;
            }
            case 5:{
            	System.out.println("Byeee....");
            	System.exit(0);
            }
            
            }
        
        }
        
	}
}
