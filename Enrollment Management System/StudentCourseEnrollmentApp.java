package com.jdbc.student_course_enrollment_app;
import java.util.*;
public class StudentCourseEnrollmentApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		StudentImp si = new StudentImp();
		CourseImp ci = new CourseImp();
		EmrollmentImp ei = new EmrollmentImp();
		
		while(true) {
			System.out.println("1. Add Student\r\n"
					+ "2. Add Course\r\n"
					+ "3. Enroll Student into Course\r\n"
					+ "4. View All Students with Enrolled Courses\r\n"
					+ "5. Delete Enrollment\r\n"
					+ "6. Get Course By Student Id\r\n"
					+ "7. Exit\r\n"
					+ "");
			System.out.print("Enter the option: ");
			int ch = sc.nextInt();
			
			
			switch (ch) {
				case 1: {
					System.out.print("Enter the Student id: ");
					Integer id = sc.nextInt();
					System.out.print("Enter the Student Name: ");
					String name = sc.next();
					System.out.print("Enter the email: ");
					String email = sc.next();
					
					si.addStudent(new Student(id, name, email));
					System.out.println("--------------------------------------------------------------");
					break;
					
					
				}
				
				case 2:{
					System.out.print("Enter the Course id: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter the Course name: ");
					String name = sc.nextLine();
					
					System.out.print("Enter the Course duration");
					String duration = sc.nextLine();
					
					
					
					ci.addCourse(new Course(id, name, duration));
					System.out.println("--------------------------------------------------------------");
					break;
				}
				case 3:{

					System.out.print("Enter the Student id: ");
					Integer sid = sc.nextInt();
					
					System.out.print("Enter the Course id: ");
					Integer cid = sc.nextInt();
					
					ei.enrollStudent(sid, cid);
					System.out.println("--------------------------------------------------------------");
					break;
					
				}
				case 4:{
					ei.getEnrollments();
					System.out.println("--------------------------------------------------------------");
					break;
				}
				
				case 5:{
					System.out.print("Enter the Student id: ");
					Integer sid = sc.nextInt();
					
					System.out.print("Enter the Course id: ");
					Integer cid = sc.nextInt();
					
					ei.deleteEnrollment(sid, cid);
					System.out.println("--------------------------------------------------------------");
					break;
				}
				
				case 6:{
					System.out.print("Enter the Student id: ");
					Integer sid = sc.nextInt();
					
					ei.getCoursesByStudentId(sid);
					System.out.println("--------------------------------------------------------------");
					break;
					
				}
				
				case 7:{
					System.out.println("Thanku.......");
					System.exit(0);
				}
				
			
			}
		}
		
	}

}
