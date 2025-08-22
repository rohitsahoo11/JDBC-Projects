package com.jdbc.student_course_enrollment_app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class StudentImp implements StudentDAO {
	
	Connection conn;
	public StudentImp(){
		try {
			Properties pro = new Properties();
			InputStream input = new FileInputStream("src/config.properties");
			pro.load(input);
			
			String url = pro.getProperty("db.url");
			String userName = pro.getProperty("db.username");
			String password = pro.getProperty("db.password");
			
			conn = DriverManager.getConnection(url,userName,password);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addStudent(Student student){
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO STUDENT_ENROLL VALUES(?,?,?)");
			ps.setInt(1, student.getStu_id());
			ps.setString(2, student.getStu_name());
			ps.setString(3, student.getEmail());
			int row = ps.executeUpdate();
			System.out.println(row+" Student data Added successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void getAllStudents(){
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT_ENROLL ORDER BY STUDENT_ID");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rm = rs.getMetaData();
			int columnCount = rm.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
			    System.out.printf("%-20s", rm.getColumnName(i)); // 20-char width
			}
			System.out.println();
			
			while(rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
			        String value = rs.getString(i);
			        System.out.printf("%-20s", value != null ? value : "NULL");
			    }
				System.out.println();

			}

			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void getStudentById(int id){
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT_ENROLL WHERE STUDENT_ID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("Student id: "+rs.getInt("Student_id"));
				System.out.println("Student Name: "+rs.getString("Student_name"));
				System.out.println("Student Email: "+rs.getString("email"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void updateStudent(Student student){
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE STUDENT_ENROLL SET STUDENT_NAME = ?, EMAIL = ? WHERE STUDENT_ID = ?");
			ps.setString(1, student.getStu_name());
			ps.setString(2, student.getEmail());
			ps.setInt(3, student.getStu_id());
			
			int row = ps.executeUpdate();
			System.out.println(row+" Student data updated successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void deleteStudent(int id){
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM STUDENT_ENROLL WHERE STUDENT_ID = ?");
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			System.out.println(row+" Student data deleted successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
