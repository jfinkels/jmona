/**
 * EvolutionExceptionTester.java
 */
package jmona;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the EvolutionException class.
 * 
 * @author jeff
 */
public class EvolutionExceptionTester {

  /**
   * Test method for {@link jmona.EvolutionException#EvolutionException()}.
   */
  @Test
  public void testEvolutionException() {
    try {
      throw new EvolutionException();
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
    }
  }

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException(java.lang.String)}.
   */
  @Test
  public void testEvolutionExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new EvolutionException(message);
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testEvolutionExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new EvolutionException(message, cause);
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException(java.lang.Throwable)}.
   */
  @Test
  public void testEvolutionExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new EvolutionException(cause);
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
      assertSame(cause, exception.getCause());
    }
  }

}
