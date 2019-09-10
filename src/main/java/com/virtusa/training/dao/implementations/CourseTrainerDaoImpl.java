package com.virtusa.training.dao.implementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.virtusa.training.dao.interfaces.CourseTrainerDao;
import com.virtusa.training.helpers.MySQLHelper;
import com.virtusa.training.models.CourseTrainer;

public class CourseTrainerDaoImpl implements CourseTrainerDao {

	private Connection conn;
	private CallableStatement callable;
	private ResultSet resultSet;
	private PreparedStatement pre;
	private ResourceBundle rb;
	private static Logger logger = Logger.getLogger(CourseTrainerDaoImpl.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	public CourseTrainerDaoImpl() {
		rb = ResourceBundle.getBundle("com/virtusa/training/resources/db");
	}

	// Adding CourseTrainer
	@Override
	public int addCourseTrainer(CourseTrainer courseTrainer) throws SQLException {
		conn = MySQLHelper.getConnection();
		int count = 0;
		try {
			callable = conn.prepareCall("{call addCourseTrainer(?,?)}");
			callable.setInt(1, courseTrainer.getCourseId());
			callable.setInt(2, courseTrainer.getTrainerId());
			count = callable.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return count;
	}

	// Get list of courses posted by trainer
	@Override
	public List<Integer> viewPostedByTrainerId(int trainerId) throws SQLException {
		List<Integer> courseIdList = new ArrayList<Integer>();
		conn = MySQLHelper.getConnection();
		String query = rb.getString("viewPostedByTrainerId");

		try {
			pre = conn.prepareStatement(query);
			pre.setInt(1, trainerId);
			resultSet = pre.executeQuery();
			while (resultSet.next()) {
				courseIdList.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return courseIdList;
	}

}
