package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.virtusa.training.models.CourseTrainer;

public interface CourseTrainerDao {

	int addCourseTrainer(CourseTrainer courseTrainer) throws SQLException;

	List<Integer> viewPostedByTrainerId(int trainerId) throws SQLException;

}
