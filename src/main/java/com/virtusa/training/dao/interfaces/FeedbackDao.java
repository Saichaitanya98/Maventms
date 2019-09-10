package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.virtusa.training.models.Feedback;

public interface FeedbackDao {

	public int addFeedback(Feedback feedback) throws SQLException;

	List<String> viewFeedback(int empId, int scheduleId) throws SQLException;
}
