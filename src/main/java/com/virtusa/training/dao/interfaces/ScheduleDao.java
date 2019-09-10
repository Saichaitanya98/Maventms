package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.virtusa.training.models.Schedule;

public interface ScheduleDao {
	int addSchedule(Schedule schedule) throws SQLException;

	List<Schedule> viewScheduled() throws SQLException;

	List<Schedule> viewTrainingsNotEnrolled(int empId) throws SQLException;

	int viewScheduleCourseById(int scheduleId) throws SQLException;

	Schedule viewScheduleById(int scheduleId) throws SQLException;

	List<Schedule> viewSchedule() throws SQLException;

}
