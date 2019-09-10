package trainingsystem;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.virtusa.training.helpers.MySQLHelper;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DbTest {
	Connection connection;

	@Test
	public void before() {
		connection = MySQLHelper.getConnection();
		Assert.assertNotNull("DB connection not created", connection);
	}

	@Test
	public void after() throws SQLException {
		connection = MySQLHelper.getConnection();
		connection.close();
		Assert.assertTrue(connection.isClosed());
	}
}
