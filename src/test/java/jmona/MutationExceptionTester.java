/**
 * MutationExceptionTester.java
 */
package jmona;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the MutationException class.
 * 
 * @author jeff
 */
public class MutationExceptionTester {

  /**
   * Test method for {@link jmona.MutationException#MutationException()}.
   */
  @Test
  public void testMutationException() {
    try {
      throw new MutationException();
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
    }
  }

  /**
   * Test method for
   * {@link jmona.MutationException#MutationException(java.lang.String)}.
   */
  @Test
  public void testMutationExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new MutationException(message);
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.MutationException#MutationException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testMutationExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new MutationException(message, cause);
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.MutationException#MutationException(java.lang.Throwable)}.
   */
  @Test
  public void testMutationExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new MutationException(cause);
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
      assertSame(cause, exception.getCause());
    }
  }

}
