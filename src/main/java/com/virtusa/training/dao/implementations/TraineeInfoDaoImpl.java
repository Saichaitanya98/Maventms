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

import com.virtusa.training.dao.interfaces.TraineeInfoDao;
import com.virtusa.training.helpers.MySQLHelper;
import com.virtusa.training.models.TraineeInfo;

public class TraineeInfoDaoImpl implements TraineeInfoDao {

	private Connection conn;
	private CallableStatement callable;
	private ResultSet resultSet;
	private PreparedStatement pre;
	private ResourceBundle rb;
	private static Logger logger = Logger.getLogger(TraineeInfoDaoImpl.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	public TraineeInfoDaoImpl() {
		rb = ResourceBundle.getBundle("com/virtusa/training/resources/db");
	}

	// When employee enrolls for any training, record will be entered in TraineeInfo
	// table
	@Override
	public int addTraineeInfo(TraineeInfo traineeInfo) throws SQLException {
		conn = MySQLHelper.getConnection();
		int count = 0;
		try {
			callable = conn.prepareCall("{call addTraineeInfo(?,?,?)}");
			callable.setInt(1, traineeInfo.getEmpId());
			callable.setInt(2, traineeInfo.getScheduleId());
			callable.setString(3, traineeInfo.getStatus());
			count = callable.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return count;
	}

	@Override
	public boolean viewTraineeInfoEnrolled(int scheduleId, int empId) throws SQLException {
		conn = MySQLHelper.getConnection();
		boolean status = false;
		try {
			callable = conn.prepareCall("{call viewTraineeInfoEnrolled(?,?)}");
			callable.setInt(1, scheduleId);
			callable.setInt(2, empId);

			resultSet = callable.executeQuery();
			if (resultSet.next()) {
				status = true;
			}

		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return status;
	}

	@Override
	public List<TraineeInfo> viewStatus(int empId) throws SQLException {
		List<TraineeInfo> traineeList = new ArrayList<TraineeInfo>();
		conn = MySQLHelper.getConnection();
		TraineeInfo traineeInfo = null;
		try {
			callable = conn.prepareCall("{call viewStatus(?)}");
			callable.setInt(1, empId);
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				traineeInfo = new TraineeInfo();
				traineeInfo.setEmpId(resultSet.getInt(1));
				traineeInfo.setScheduleId(resultSet.getInt(2));
				traineeInfo.setStatus(resultSet.getString(3));
				traineeList.add(traineeInfo);
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return traineeList;
	}

	// If the employee cancels the enrollment for the training, corresponding record
	// will be deleted from the TraineeInfo table
	@Override
	public boolean deleteTraineeInfoByIds(int scheduleId, int empId) throws SQLException {
		conn = MySQLHelper.getConnection();
		String query = rb.getString("deleteTraineeInfo");
		int count = 0;
		boolean status = false;
		try {
			pre = conn.prepareStatement(query);
			pre.setInt(1, scheduleId);
			pre.setInt(2, empId);
			count = pre.executeUpdate();
			if (count > 0)
				status = true;
		}catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return status;
	}

	// List of employees under respective manager passed as empId
	@Override
	public List<TraineeInfo> viewTraineeInfoByManagerId(int empId) throws SQLException {
		conn = MySQLHelper.getConnection();
		List<TraineeInfo> traineeList = new ArrayList<TraineeInfo>();

		TraineeInfo traineeInfo = null;
		try {
			callable = conn.prepareCall("{call viewTraineeInfoByManagerId(?)}");
			callable.setInt(1, empId);
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				traineeInfo = new TraineeInfo();
				traineeInfo.setEmpId(resultSet.getInt(1));
				traineeInfo.setScheduleId(resultSet.getInt(2));
				traineeInfo.setStatus(resultSet.getString(3));
				traineeList.add(traineeInfo);
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return traineeList;
	}

	// For updating the status column whenever PM approves or rejects for attending
	// the course
	@Override
	public boolean updateStatus(TraineeInfo traineeInfo) throws SQLException {
		int count;
		boolean status = false;
		conn = MySQLHelper.getConnection();
		try {
			callable = conn.prepareCall("{call updateStatus(?,?,?)}");
			callable.setInt(1, traineeInfo.getEmpId());
			callable.setInt(2, traineeInfo.getScheduleId());
			callable.setString(3, traineeInfo.getStatus());
			count = callable.executeUpdate();
			if (count > 0) {
				status = true;
			}
		}catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return status;
	}

	// View ScheduleId list based on empId and status(If status is approved)
	@Override
	public List<Integer> viewScheduleIdByEmpIdStatus(int empId) throws SQLException {
		conn = MySQLHelper.getConnection();
		List<Integer> scheduleIdList = new ArrayList<Integer>();
		try {
			callable = conn.prepareCall("{call viewScheduleIdByEmpIdStatus (?)}");
			callable.setInt(1, empId);
			resultSet = callable.executeQuery();
			while (resultSet.next()) {
				scheduleIdList.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return scheduleIdList;
	}
}
