package generic;

import org.testng.Assert;

public class AssertHelper {
	 public static void verifyTrue(boolean condition, String message) {
	        try {
	            Assert.assertTrue(condition, message);
	            System.out.println(" PASS: " + message);
	        } catch (AssertionError e) {
	            System.out.println(" FAIL: " + message);
	            throw e;
	        }

}
	  public static void verifyEquals(String actual, String expected, String message) {
	        try {
	            Assert.assertEquals(actual, expected, message);
	            System.out.println(" PASS: " + message);
	        } catch (AssertionError e) {
	            System.out.println(" FAIL: " + message);
	            throw e;
	        }
	    }
}
