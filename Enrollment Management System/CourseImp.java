package com.jdbc.student_course_enrollment_app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import oracle.jdbc.proxy.annotation.Pre;

public class CourseImp implements CourseDAO {
	Connection conn;
	public CourseImp() {
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
	public void addCourse(Course course){
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO COURSE_ENROLL VALUES (?, ?, ?)");
			ps.setInt(1, course.getCourse_id());
			ps.setString(2, course.getCourse_name());
			ps.setString(3, course.getDuration());
			int row = ps.executeUpdate();
			System.out.println(row+" Course data added successfully....");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void getAllCourse(){
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM COURSE_ENROLL order by course_id");
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rm = rs.getMetaData();
			int countColumn = rm.getColumnCount();
			
			for(int i = 1;i<=countColumn;i++) {
				System.out.printf("%-20s",rm.getColumnName(i));
				
			}
			System.out.println();
			
			while(rs.next()) {
				for(int i = 1;i<=countColumn;i++) {
					String value = rs.getString(i);
					System.out.printf("%-20s",value != null ? value:"NULL");
				}
				System.out.println();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void getCourseById(int id){
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM COURSE_ENROLL WHERE COURSE_ID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("Course Id: "+rs.getInt("Course_id"));
				System.out.println("Course Name: "+rs.getString("Course_name"));
				System.out.println("Course Duration: "+rs.getString("duration"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void updateCourse(Course course){
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE COURSE_ENROLL SET COURSE_NAME = ?, DURATION = ? WHERE COURSE_ID = ?");
			ps.setString(1, course.getCourse_name());
			ps.setString(2, course.getDuration());
			ps.setInt(3, course.getCourse_id());
			
			int row = ps.executeUpdate();
			System.out.println(row+" Course data updated successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void deleteCourse(int id){
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM COURSE_ENROLL WHERE COURSE_ID = ?");
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			System.out.println(row+" Course data deleted successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
