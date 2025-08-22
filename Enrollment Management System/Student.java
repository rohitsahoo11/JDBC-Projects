package com.jdbc.student_course_enrollment_app;

public class Student {
	private Integer stu_id;
	private String stu_name;
	private String email;
	
	public Student(Integer stu_id, String stu_name, String email) {
		super();
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.email = email;
	}

	public Integer getStu_id() {
		return stu_id;
	}

	public String getStu_name() {
		return stu_name;
	}

	public String getEmail() {
		return email;
	}

	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
