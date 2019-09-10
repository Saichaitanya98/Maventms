package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.virtusa.training.models.Course;

public interface CourseDao {

	int addCourse(Course course) throws SQLException;

	List<Course> getCourse() throws SQLException;

	List<Course> viewNotScheduled() throws SQLException;

	Course viewCourseById(int courseId) throws SQLException;

	List<Course> viewCourseTrainerById(int trainerId) throws SQLException;

}
