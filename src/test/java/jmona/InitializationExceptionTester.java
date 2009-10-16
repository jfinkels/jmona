/**
 * InitializationExceptionTester.java
 */
package jmona;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the InitializationException class.
 * 
 * @author jeff
 */
public class InitializationExceptionTester {

  /**
   * Test method for {@link jmona.InitializationException#InitializationException()}.
   */
  @Test
  public void testInitializationException() {
    try {
      throw new InitializationException();
    } catch (final InitializationException exception) {
      assertTrue(exception instanceof InitializationException);
    }
  }

  /**
   * Test method for
   * {@link jmona.InitializationException#InitializationException(java.lang.String)}.
   */
  @Test
  public void testInitializationExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new InitializationException(message);
    } catch (final InitializationException exception) {
      assertTrue(exception instanceof InitializationException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.InitializationException#InitializationException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testInitializationExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new InitializationException(message, cause);
    } catch (final InitializationException exception) {
      assertTrue(exception instanceof InitializationException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.InitializationException#InitializationException(java.lang.Throwable)}.
   */
  @Test
  public void testInitializationExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new InitializationException(cause);
    } catch (final InitializationException exception) {
      assertTrue(exception instanceof InitializationException);
      assertSame(cause, exception.getCause());
    }
  }

}
