package com.virtusa.training.dao.implementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.virtusa.training.dao.interfaces.ScheduleDao;
import com.virtusa.training.helpers.MySQLHelper;
import com.virtusa.training.models.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {

	private Connection conn;
	private CallableStatement callable;
	private ResultSet resultSet;
	private Statement statement;
	private ResourceBundle rb;
	private static Logger logger = Logger.getLogger(ScheduleDaoImpl.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	public ScheduleDaoImpl() {
		// TODO Auto-generated constructor stub
		rb = ResourceBundle.getBundle("com/virtusa/training/resources/db");
	}

	// Adding new schedule
	@Override
	public int addSchedule(Schedule schedule) throws SQLException {
		conn = MySQLHelper.getConnection();
		int count = 0;
		try {
			callable = conn.prepareCall("{call addSchedule(?,?,?,?,?)}");
			callable.setInt(1, schedule.getNumOfSeats());
			callable.setDate(2, schedule.getFromDate());
			callable.setDate(3, schedule.getToDate());
			callable.setInt(4, schedule.getLocationId());
			callable.setInt(5, schedule.getCourseId());
			count = callable.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return count;
	}

	// View scheduled trainings
	@Override
	public List<Schedule> viewScheduled() throws SQLException {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		conn = MySQLHelper.getConnection();
		Schedule schedule = null;
		try {
			callable = conn.prepareCall("{call viewScheduled()}");
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				schedule = new Schedule();
				schedule.setScheduleId(resultSet.getInt(1));
				schedule.setNumOfSeats(resultSet.getInt(2));
				schedule.setFromDate(resultSet.getDate(3));
				schedule.setToDate(resultSet.getDate(4));
				schedule.setLocationId(resultSet.getInt(5));
				schedule.setCourseId(resultSet.getInt(6));
				scheduleList.add(schedule);
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return scheduleList;
	}

	// Listing the training list in which employee has not been enrolled
	@Override
	public List<Schedule> viewTrainingsNotEnrolled(int empId) throws SQLException {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		conn = MySQLHelper.getConnection();
		Schedule schedule = null;
		try {
			callable = conn.prepareCall("{call viewTrainingsNotEnrolled(?)}");
			callable.setInt(1, empId);
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				schedule = new Schedule();
				schedule.setScheduleId(resultSet.getInt(1));
				schedule.setNumOfSeats(resultSet.getInt(2));
				schedule.setFromDate(resultSet.getDate(3));
				schedule.setToDate(resultSet.getDate(4));
				schedule.setLocationId(resultSet.getInt(5));
				schedule.setCourseId(resultSet.getInt(6));
				scheduleList.add(schedule);
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return scheduleList;
	}

	// To get the courseId based on scheduleId so that it can be reused for getting
	// courseName by passing courseId in CourseImpl
	@Override
	public int viewScheduleCourseById(int scheduleId) throws SQLException {
		conn = MySQLHelper.getConnection();
		int courseId = 0;
		try {
			callable = conn.prepareCall("{call viewScheduleCourseById(?,?)}");
			callable.setInt(1, scheduleId);
			callable.registerOutParameter(2, java.sql.Types.INTEGER);
			callable.execute();
			courseId = callable.getInt(2);
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return courseId;

	}

	// view schedule information by Id
	@Override
	public Schedule viewScheduleById(int scheduleId) throws SQLException {
		conn = MySQLHelper.getConnection();
		Schedule schedule = null;
		try {
			callable = conn.prepareCall("{call viewScheduleById(?)}");
			callable.setInt(1, scheduleId);
			resultSet = callable.executeQuery();
			resultSet.next();
			schedule = new Schedule();
			schedule.setScheduleId(resultSet.getInt(1));
			schedule.setNumOfSeats(resultSet.getInt(2));
			schedule.setFromDate(resultSet.getDate(3));
			schedule.setToDate(resultSet.getDate(4));
			schedule.setLocationId(resultSet.getInt(5));
			schedule.setCourseId(resultSet.getInt(6));

		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return schedule;
	}

	// List all the trainings
	@Override
	public List<Schedule> viewSchedule() throws SQLException {
		conn = MySQLHelper.getConnection();
		String query = rb.getString("viewSchedule");
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		Schedule schedule = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				schedule = new Schedule();
				schedule.setScheduleId(resultSet.getInt(1));
				schedule.setNumOfSeats(resultSet.getInt(2));
				schedule.setFromDate(resultSet.getDate(3));
				schedule.setToDate(resultSet.getDate(4));
				schedule.setLocationId(resultSet.getInt(5));
				schedule.setCourseId(resultSet.getInt(6));
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return scheduleList;

	}
}
