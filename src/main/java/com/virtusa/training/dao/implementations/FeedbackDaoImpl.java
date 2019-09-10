package com.virtusa.training.dao.implementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.virtusa.training.dao.interfaces.FeedbackDao;
import com.virtusa.training.helpers.MySQLHelper;
import com.virtusa.training.models.Feedback;

public class FeedbackDaoImpl implements FeedbackDao {

	private Connection conn;
	private CallableStatement callable;
	private ResultSet resultSet;
	private static Logger logger = Logger.getLogger(FeedbackDaoImpl.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	@Override
	public int addFeedback(Feedback feedback) throws SQLException {
		// TODO Auto-generated method stub
		conn = MySQLHelper.getConnection();

		int count = 0;
		try {
			callable = conn.prepareCall("{call addFeedback(?,?,?)}");
			// callable.setInt(1, feedback.getFeedbackId());
			callable.setString(1, feedback.getDescription());
			callable.setInt(2, feedback.getEmpId());
			callable.setInt(3, feedback.getScheduleId());
			count = callable.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return count;
	}

	@Override
	public List<String> viewFeedback(int empId, int scheduleId) throws SQLException {
		// TODO Auto-generated method stub
		conn = MySQLHelper.getConnection();
		List<String> feedbacks = new ArrayList<String>();

		try {
			callable = conn.prepareCall("{call viewFeedback(?,?)}");
			callable.setInt(1, empId);
			callable.setInt(2, scheduleId);
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				feedbacks.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return feedbacks;
	}
}