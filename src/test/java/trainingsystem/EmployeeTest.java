package trainingsystem;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.virtusa.training.models.Employee;

@RunWith(value = Parameterized.class)
public class EmployeeTest {

	private static Employee employee;

	@Parameter(value = 0)
	public int empId;
	@Parameter(value = 1)
	public String empName;
	@Parameter(value = 2)
	public int roleId;
	@Parameter(value = 3)
	public long contactNo;
	@Parameter(value = 4)
	public String emailId;
	@Parameter(value = 5)
	public String address;
	@Parameter(value = 6)
	public String password;

	@Parameters
	public static Collection<Object[]> getTestParameters() {
		return Arrays.asList(
				new Object[][] { { 1, "Srujana", 1, 9807654123L, "srujana.sindu@gmail.com", "Hyderabad", "Sruj@123" },

				});
	}

	@BeforeClass
	public static void createInstance() {
		employee = new Employee();
	}

	@Before
	public void initializeProduct() {
		employee.setEmpId(empId);
		employee.setEmpName(empName);
		employee.setRoleId(roleId);
		employee.setContactNo(contactNo);
		employee.setEmailId(emailId);
		employee.setAddress(address);
		employee.setPassword(password);

	}
//	@Test(expected=IllegalArgumentException.class)
//    public void testProduct() {
//       
//        product.setCost(-32468);
//    }
//	@Test
//    public void testProductDOP()
//    {
//      //  Assert.assertTrue(product.getDopDate().isBefore(LocalDate.now()));
//        //hamcrest
//        assertThat(true, is(product.getDop().isBefore(LocalDate.now())));
//    }

	@AfterClass
	public static void deleteInstance() {
		employee = null;
	}

	@Test
	public void testEmpId() {
		// Assert.assertNotNull(product.getProductName());
		assertThat(employee.getEmpId(), is(notNullValue()));

	}

	@Test
	public void testPassword() {
		// Assert.assertNotSame(employee.getEmpName(),employee.getPassword());
		// assertThat(employee.getPassword(),is(notSameValue(employee.getEmpName())));

		assertEquals(true, employee.getPassword().matches(
				"(?=^.{6,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$"));
	}

}
