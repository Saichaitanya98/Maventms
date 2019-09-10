package com.virtusa.training.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.virtusa.training.models.Location;

public interface LocationDao {
	boolean addLocation(String locationName) throws SQLException;

	Location viewLocationById(int locationId) throws SQLException;

	List<Location> viewLocation() throws SQLException;

}
