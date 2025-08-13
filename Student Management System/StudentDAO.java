package com.jdbc.student_management_system;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Properties;

public class StudentDAO {
	Connection conn;
	
	public StudentDAO() {
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
	
	public void addStudent(Student s) {
		
		try{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO student_management VALUES (?, ?, ?, ?)");
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setString(3, s.getEmail());
			ps.setInt(4, s.getAge());
			int row = ps.executeUpdate();
			System.out.println(row+" Row Added Successfully...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewStudent() {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT_MANAGEMENT ORDER BY ID ASC");
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Id \t\t Name \t\t   Email \t\t  Age");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t "+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t  "+rs.getInt(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(Student s) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE student_management SET name = ?, email = ?, age = ? WHERE id = ?");
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setInt(3, s.getAge());
			ps.setInt(4, s.getId());
			int row = ps.executeUpdate();
			System.out.println(row+" Row successfully Updated...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudent(Student s) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM STUDENT_MANAGEMENT WHERE ID = ?");
			ps.setInt(1, s.getId());
			int row = ps.executeUpdate();
			System.out.println(row+" Row Deleted Successfully...");
		} 
		catch (Exception e) {
			
		}
	}
}
