package com.jdbc.student_management_system;
import java.sql.*;
import java.util.*;
public class StudentManagementSystem {

	public static void main(String[] args) {
		
		String url = "Your-URL";
		String username = "Your-USERNAME";
		String password = "Your-PASSWORD";
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(url,username,password);
			
			while(true) {
				System.out.println("\n=== Student Management ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                
                int choice = sc.nextInt();
                
                switch (choice) {
					case 1: {
						addStudent(conn, sc);
						break;
					}
					case 2:{
						viewStudent(conn);
						break;
					}
					case 3:{
						updateStudent(conn, sc);
						break;
					}
					case 4:{
						deleteStudent(conn,sc);
						break;
					}
					case 5:{
						System.out.println("Ok Bye.....");
						System.exit(0);
					}
                }	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addStudent(Connection conn,Scanner sc) throws SQLException{
		System.out.print("Enter the id to add: ");
		int id = sc.nextInt();
		System.out.print("Enter the name: ");
		String name = sc.next();
		System.out.print("Enter the email: ");
		String email = sc.next();
		System.out.print("Enter the age: ");
		int age = sc.nextInt();
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO student_management (id, name, email, age) VALUES (?, ?, ?, ?)");
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.setInt(4,age);
		int row = ps.executeUpdate();
		System.out.println("Row "+row+" Added successfully...");
		
		ps.close();
		
	}
	
	public static void viewStudent(Connection conn) throws SQLException{
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from student_management order by id asc");
		System.out.println("Id \t\t Name \t\t   Email \t\t  Age");
		while(rs.next()) {
			
			System.out.println(rs.getInt("id")+"\t\t "+rs.getString("name")+"\t\t"+rs.getString("email")+"\t\t  "+rs.getInt("age"));
		}
		st.close();
		rs.close();
	}
	
	public static void updateStudent(Connection conn, Scanner sc) throws SQLException{
		
		String query = "UPDATE student_management SET name = ?, email = ?, age = ? WHERE id = ?";
		
		System.out.print("Enter the id to update: ");
		int id = sc.nextInt();
		System.out.print("Enter the name: ");
		String name = sc.next();
		System.out.print("Enter the email: ");
		String email = sc.next();
		System.out.print("Enter the age: ");
		int age = sc.nextInt();
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			
			
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, age);
			ps.setInt(4, id);
			int row = ps.executeUpdate();
			System.out.println("Row "+row+" Updated successfully..");
			
		}
	}
	
	public static void deleteStudent(Connection conn, Scanner sc) throws SQLException{
		
		String query = "delete from student_management where id = ?";
		
		System.out.print("Enter the id that you want to delete the data: ");
		int id = sc.nextInt();
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			System.out.println(row+" Row deleted successfully....");
		}
	}

}
