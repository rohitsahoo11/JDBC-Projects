package com.jdbc.student_course_enrollment_app;

public class Enrollment {
	private Integer enroll_id;
	private Integer student_id;
	private Integer course_id;
	
	public Enrollment(Integer enroll_id, Integer student_id, Integer course_id) {
		super();
		this.enroll_id = enroll_id;
		this.student_id = student_id;
		this.course_id = course_id;
	}

	public Integer getEnroll_id() {
		return enroll_id;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setEnroll_id(Integer enroll_id) {
		this.enroll_id = enroll_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	
	
	
}
