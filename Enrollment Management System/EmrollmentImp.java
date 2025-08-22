package com.jdbc.student_course_enrollment_app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

public class EmrollmentImp implements EnrollmentDAO {
	Connection conn;
	public EmrollmentImp() {
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
	public void enrollStudent(int studentId, int courseId){
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ENROLLMENT VALUES(?, ?, sysdate)");
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			
			int row = ps.executeUpdate();
			System.out.println(row+" Data added successfully...");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getEnrollments(){
		try {
			PreparedStatement ps = conn.prepareStatement("""
					SELECT ENROLL_DATE,STUDENT_NAME, COURSE_NAME 
					FROM ENROLLMENT E
					JOIN STUDENT_ENROLL S ON E.STUDENT_ID=S.STUDENT_ID
					JOIN COURSE_ENROLL C ON E.COURSE_ID=C.COURSE_ID
					""");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rm = rs.getMetaData();
			int countColumn = rm.getColumnCount();
			
			for(int i=1;i<=countColumn;i++) {
				System.out.printf("%-30s",rm.getColumnName(i));
			}
			System.out.println();
			
			while(rs.next()) {
				for(int i=1;i<=countColumn;i++) {
					String value = rs.getString(i);
					System.out.printf("%-30s",value != null? value:"NULL");
				}
				System.out.println();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void getCoursesByStudentId(int studentId){
		try {
			PreparedStatement ps = conn.prepareStatement("""
															SELECT ENROLL_DATE, COURSE_NAME
														    FROM ENROLLMENT E
														    JOIN STUDENT_ENROLL S ON E.STUDENT_ID=S.STUDENT_ID
														    JOIN COURSE_ENROLL C ON E.COURSE_ID=C.COURSE_ID
														    where e.student_id = ?
															""");
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rm = rs.getMetaData();
			int countColumn = rm.getColumnCount();
			
			for(int i = 1;i<=countColumn;i++) {
				System.out.printf("%-30s",rm.getColumnName(i));
			}
			System.out.println();
			
			while(rs.next()) {
				for(int i = 1;i<=countColumn;i++) {
					String value = rs.getString(i);
					System.out.printf("%-30s",value != null ? value:"NULL");
				}
				System.out.println();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
							
	}
	@Override
	public void deleteEnrollment(int studentId, int courseId){
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ENROLLMENT WHERE STUDENT_ID=? OR COURSE_ID=?");
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			int row = ps.executeUpdate();
			
			System.out.println(row+" Enrollment data deleted successfully...");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
