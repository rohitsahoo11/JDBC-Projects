package com.jdbc.student_course_enrollment_app;

public interface CourseDAO {
	
	public void addCourse(Course course);

	public void getAllCourse();

	public void getCourseById(int id);

	public void updateCourse(Course course);

	public void deleteCourse(int id);
}
