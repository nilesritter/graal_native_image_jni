package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Jaython.
 */
public class AppDemoTest {

  private static AppDemo demo;


  @Before
  public void setup() {
	String userDirectory = System.getProperty("user.dir");
  	final String LIBDEMO=userDirectory + "/../libdemo/target/libnativeimpl.so";
	demo = new AppDemo(LIBDEMO);
  }

  @Test
  public void appDemoAddLongShouldWork() {
	assertEquals(3,demo.public_add(1,2));
  }

  // THIS TEST CRASHES
  @Test
  public void appDemoEvalShouldWork() {
	String result = "";
	try {
 	  result= demo.public_evaluate("wookie");
	} catch (Exception e) {
	  result = e.toString();
	}
	assertEquals("Foo-wookie",result);
  }

}
