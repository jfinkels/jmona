/**
 * FitnessExceptionTester.java
 */
package jmona;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the FitnessException class.
 * 
 * @author jeff
 */
public class FitnessExceptionTester {

  /**
   * Test method for {@link jmona.FitnessException#FitnessException()}.
   */
  @Test
  public void testFitnessException() {
    try {
      throw new FitnessException();
    } catch (final FitnessException exception) {
      assertTrue(exception instanceof FitnessException);
    }
  }

  /**
   * Test method for
   * {@link jmona.FitnessException#FitnessException(java.lang.String)}.
   */
  @Test
  public void testFitnessExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new FitnessException(message);
    } catch (final FitnessException exception) {
      assertTrue(exception instanceof FitnessException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.FitnessException#FitnessException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testFitnessExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new FitnessException(message, cause);
    } catch (final FitnessException exception) {
      assertTrue(exception instanceof FitnessException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.FitnessException#FitnessException(java.lang.Throwable)}.
   */
  @Test
  public void testFitnessExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new FitnessException(cause);
    } catch (final FitnessException exception) {
      assertTrue(exception instanceof FitnessException);
      assertSame(cause, exception.getCause());
    }
  }

}
