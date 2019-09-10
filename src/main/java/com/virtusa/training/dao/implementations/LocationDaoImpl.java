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

import com.virtusa.training.dao.interfaces.LocationDao;
import com.virtusa.training.helpers.MySQLHelper;
import com.virtusa.training.models.Location;

public class LocationDaoImpl implements LocationDao {

	private Connection conn;
	private CallableStatement callable;
	private ResultSet resultSet;
	private Statement statement;
	private ResourceBundle rb;
	private static Logger logger = Logger.getLogger(LocationDaoImpl.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	public LocationDaoImpl() {
		// TODO Auto-generated constructor stub
		rb = ResourceBundle.getBundle("com/virtusa/training/resources/db");
	}

	// Adding location.
	@Override
	public boolean addLocation(String locationName) throws SQLException {
		conn = MySQLHelper.getConnection();
		boolean status = false;
		int count = 0;
		try {
			callable = conn.prepareCall("{call addLocation(?)}");
			callable.setString(1, locationName);
			count = callable.executeUpdate();
			if (count > 0)
				status = true;
		} catch (SQLException sq) {
			logger.error("SQL Exception" + sq.getMessage());
		} finally {
			conn.close();
		}
		return status;
	}

	// View locationame by passing locationId
	@Override
	public Location viewLocationById(int locationId) throws SQLException {
		conn = MySQLHelper.getConnection();
		Location location = new Location();
		try {
			callable = conn.prepareCall("{call viewLocationById(?)}");
			callable.setInt(1, locationId);
			resultSet = callable.executeQuery();
			resultSet.next();
			location.setLocationId(locationId);
			location.setLocationName(resultSet.getString(2));
		} catch (SQLException e) { // TODO: handle exception e.printStackTrace(); } finally {
			logger.error("SQL Exception" + e.getMessage());
		}
		finally {
			conn.close();
		}
		return location;
	}

	// View all locations
	@Override
	public List<Location> viewLocation() throws SQLException {
		conn = MySQLHelper.getConnection();
		String query = rb.getString("viewLocation");
		List<Location> locationList = new ArrayList<Location>();
		Location location = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				location = new Location();
				location.setLocationId(resultSet.getInt(1));
				location.setLocationName(resultSet.getString(2));
				locationList.add(location);

			}

		} catch (SQLException e) {
			// TODO: handle exception
			logger.error("SQL Exception" + e.getMessage());
		} finally {
			conn.close();

		}
		return locationList;
	}
}
