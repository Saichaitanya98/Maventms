package com.virtusa.training.dao.implementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.virtusa.training.dao.interfaces.EmployeeDao;
import com.virtusa.training.helpers.MySQLHelper;

public class EmployeeDaoImpl implements EmployeeDao {

		private static EmployeeDao dao=null;
		private EmployeeDaoImpl(){}
		
		
		public static EmployeeDao getInstance() {
			if(dao==null){
				dao=new EmployeeDaoImpl();
			}
			return dao;
		}
	//private Connection conn;
	//private CallableStatement callable;
	private static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	// For checking login
	@Override
	public String loginCheck(int empId, String password) throws SQLException {
		Connection conn;
		CallableStatement callable;
		conn = MySQLHelper.getConnection();
		int roleId = 0;
		String result = null;
		try {
			callable = conn.prepareCall("{call loginCheck(?,?,?)}");
			callable.setInt(1, empId);
			callable.setString(2, password);
			callable.registerOutParameter(3, java.sql.Types.INTEGER);
			callable.execute();
			roleId = callable.getInt(3);
			switch (roleId) {

			case 1:
				result = "TA/TA.jsp";
				break;

			case 2:

				result = "PM/PM.jsp";
				break;

			case 3:
				result = "employee/employee.jsp";
				break;

			default:
				result = "Invalid credentials";
				break;
			}

		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return result;
	}

	// Check employee name based on id
	@Override
	public String viewEmployeeNameById(int empId) throws SQLException {
		Connection conn;
		CallableStatement callable;
		conn = MySQLHelper.getConnection();
		String empName = null;
		try {
			callable = conn.prepareCall("{call viewEmployeeNameById(?,?)}");
			callable.setInt(1, empId);
			callable.registerOutParameter(2, java.sql.Types.VARCHAR);
			callable.execute();
			empName = callable.getString(2);
		} catch (SQLException e) {
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();
		}
		return empName;
	}

}
