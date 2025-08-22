package com.jdbc.student_course_enrollment_app;

public interface StudentDAO {
	
	public void addStudent(Student student);

	public void getAllStudents();

	public void getStudentById(int id);

	public void updateStudent(Student student);

	public void deleteStudent(int id);
}
