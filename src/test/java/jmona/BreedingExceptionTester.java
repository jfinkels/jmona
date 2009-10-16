/**
 * BreedingExceptionTester.java
 */
package jmona;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the BreedingException class.
 * 
 * @author jeff
 */
public class BreedingExceptionTester {

  /**
   * Test method for {@link jmona.BreedingException#BreedingException()}.
   */
  @Test
  public void testBreedingException() {
    try {
      throw new BreedingException();
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
    }
  }

  /**
   * Test method for
   * {@link jmona.BreedingException#BreedingException(java.lang.String)}.
   */
  @Test
  public void testBreedingExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new BreedingException(message);
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.BreedingException#BreedingException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testBreedingExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new BreedingException(message, cause);
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.BreedingException#BreedingException(java.lang.Throwable)}.
   */
  @Test
  public void testBreedingExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new BreedingException(cause);
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
      assertSame(cause, exception.getCause());
    }
  }

}
