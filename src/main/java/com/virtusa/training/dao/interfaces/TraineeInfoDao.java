package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.virtusa.training.models.TraineeInfo;

public interface TraineeInfoDao {

	int addTraineeInfo(TraineeInfo traineeInfo) throws SQLException;

	boolean viewTraineeInfoEnrolled(int scheduleId, int empId) throws SQLException;

	List<TraineeInfo> viewStatus(int empId) throws SQLException;

	boolean deleteTraineeInfoByIds(int scheduleId, int empId) throws SQLException;

	List<TraineeInfo> viewTraineeInfoByManagerId(int empId) throws SQLException;

	boolean updateStatus(TraineeInfo traineeInfo) throws SQLException;

	List<Integer> viewScheduleIdByEmpIdStatus(int empId) throws SQLException;
}
