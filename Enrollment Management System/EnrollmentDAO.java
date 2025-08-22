package com.jdbc.student_course_enrollment_app;

public interface EnrollmentDAO {
	
	public void enrollStudent(int studentId, int courseId);

	public void getEnrollments();

	public void getCoursesByStudentId(int studentId);

	public void deleteEnrollment(int studentId, int courseId);
}
