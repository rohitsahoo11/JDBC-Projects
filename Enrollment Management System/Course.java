package com.jdbc.student_course_enrollment_app;

public class Course {
	private Integer course_id;
	private String course_name;
	private String duration;
	
	public Course(Integer course_id, String course_name, String duration) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.duration = duration;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public String getDuration() {
		return duration;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
