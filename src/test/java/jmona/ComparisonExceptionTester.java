/**
 * ComparisonExceptionTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
 * 
 * This file is part of jmona.
 * 
 * jmona is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * jmona is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jmona. If not, see <http://www.gnu.org/licenses/>.
 */
package jmona;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the ComparisonException class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class ComparisonExceptionTester {

  /**
   * Test method for
   * {@link jmona.ComparisonException#ComparisonException()}.
   */
  @Test
  public void testComparisonException() {
    try {
      throw new ComparisonException();
    } catch (final ComparisonException exception) {
      assertTrue(exception instanceof ComparisonException);
    }
  }

  /**
   * Test method for
   * {@link jmona.ComparisonException#ComparisonException(java.lang.String)}
   * .
   */
  @Test
  public void testComparisonExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new ComparisonException(message);
    } catch (final ComparisonException exception) {
      assertTrue(exception instanceof ComparisonException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.ComparisonException#ComparisonException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testComparisonExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new ComparisonException(message, cause);
    } catch (final ComparisonException exception) {
      assertTrue(exception instanceof ComparisonException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.ComparisonException#ComparisonException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testComparisonExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new ComparisonException(cause);
    } catch (final ComparisonException exception) {
      assertTrue(exception instanceof ComparisonException);
      assertSame(cause, exception.getCause());
    }
  }

}
