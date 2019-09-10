package trainingsystem;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.virtusa.training.dao.implementations.ScheduleDaoImpl;
import com.virtusa.training.dao.interfaces.ScheduleDao;
import com.virtusa.training.models.Schedule;

public class ScheduleImplTest {
	@Test
	public void testviewSchedule() throws SQLException {
		ScheduleDao scheduleDao = new ScheduleDaoImpl();
		List<Schedule> listOfSchedules = new ArrayList<Schedule>();
		listOfSchedules = scheduleDao.viewSchedule();
		assertFalse(listOfSchedules.isEmpty());

	}
}
