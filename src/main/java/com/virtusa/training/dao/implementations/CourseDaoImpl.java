package com.virtusa.training.dao.implementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.virtusa.training.dao.interfaces.CourseDao;
import com.virtusa.training.helpers.MySQLHelper;
import com.virtusa.training.models.Course;

public class CourseDaoImpl implements CourseDao {

	private Connection conn;
	private CallableStatement callable;
	private ResultSet resultSet;

	private static Logger logger = Logger.getLogger(CourseDaoImpl.class);
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	// Adding course
	@Override
	public int addCourse(Course course) throws SQLException {
		conn = MySQLHelper.getConnection();
		int count = 0;
		try {
			callable = conn.prepareCall("{call addCourse(?,?)}");
			callable.setString(1, course.getCourseName());
			callable.setString(2, course.getDescription());
			count = callable.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return count;
	}

	// View the courses which have not been scheduled so that TA can schedule it

	@Override
	public List<Course> viewNotScheduled() throws SQLException {
		List<Course> courseList = new ArrayList<Course>();
		conn = MySQLHelper.getConnection();
		Course course = null;
		try {
			callable = conn.prepareCall("{call viewNotScheduled()}");
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				course = new Course();
				course.setCourseId(resultSet.getInt(1));
				course.setCourseName(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				courseList.add(course);
			}
		} catch (SQLException e) { // TODO: handle exception e.printStackTrace(); } finally {
			logger.error("SQL Exception" + e.getMessage());
		}
		finally
		{
			conn.close();
		}
		return courseList;
	}

	// View All the courses

	@Override
	public List<Course> getCourse() throws SQLException {

		List<Course> courseList = new ArrayList<Course>();
		conn = MySQLHelper.getConnection();
		Course course = null;
		try {
			callable = conn.prepareCall("{call viewCourse()}");
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				course = new Course();
				course.setCourseId(resultSet.getInt(1));
				course.setCourseName(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				courseList.add(course);
			}
		} catch (SQLException e) { // TODO: handle exception e.printStackTrace(); } finally {
			logger.error("SQL Exception" + e.getMessage());
		}
		finally
		{
			conn.close();
		}
		return courseList;
	}

	// Retrieving Course by id
	@Override
	public Course viewCourseById(int courseId) throws SQLException {
		conn = MySQLHelper.getConnection();
		Course course = null;
		try {
			callable = conn.prepareCall("{call viewCourseById(?)}");
			callable.setInt(1, courseId);
			resultSet = callable.executeQuery();
			resultSet.next();
			course = new Course();
			course.setCourseId(resultSet.getInt(1));
			course.setCourseName(resultSet.getString(2));
			course.setDescription(resultSet.getString(3));
		} catch (SQLException e) { // TODO: handle exception e.printStackTrace(); } finally {
			logger.error("SQL Exception" + e.getMessage());
		}
		finally
		{
			conn.close();
		}
		return course;
	}

	// View the list of courses posted by trainer
	@Override
	public List<Course> viewCourseTrainerById(int trainerId) throws SQLException {
		conn = MySQLHelper.getConnection();
		List<Course> courseList = new ArrayList<Course>();
		Course course = null;
		try {
			callable = conn.prepareCall("{call viewCourseTrainerById(?)}");
			callable.setInt(1, trainerId);
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				course = new Course();
				course.setCourseId(resultSet.getInt(1));
				course.setCourseName(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				courseList.add(course);
			}
			Collections.sort(courseList, (e1, e2) -> {
				return e1.getCourseName().compareTo(e2.getCourseName());
			});
		} catch (SQLException e) { // TODO: handle exception e.printStackTrace(); } finally {
			logger.error("SQL Exception" + e.getMessage());
		}
		finally
		{
			conn.close();
		}
		return courseList;
	}

}
