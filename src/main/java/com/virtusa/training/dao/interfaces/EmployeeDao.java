package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;

public interface EmployeeDao {

	String loginCheck(int empId, String password) throws SQLException;

	String viewEmployeeNameById(int empId) throws SQLException;
}
