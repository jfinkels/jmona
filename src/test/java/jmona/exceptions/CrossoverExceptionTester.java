/**
 * CrossoverExceptionTester.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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
package jmona.exceptions;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the CrossoverException class.
 * 
 * @author jfinkels
 */
public class CrossoverExceptionTester {

  /**
   * Test method for
   * {@link jmona.exceptions.CrossoverException#CrossoverException()}.
   */
  @Test
  public void testCrossoverException() {
    try {
      throw new CrossoverException();
    } catch (final CrossoverException exception) {
      assertTrue(exception instanceof CrossoverException);
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.CrossoverException#CrossoverException(java.lang.String)}
   * .
   */
  @Test
  public void testCrossoverExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new CrossoverException(message);
    } catch (final CrossoverException exception) {
      assertTrue(exception instanceof CrossoverException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.CrossoverException#CrossoverException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testCrossoverExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new CrossoverException(message, cause);
    } catch (final CrossoverException exception) {
      assertTrue(exception instanceof CrossoverException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.CrossoverException#CrossoverException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testCrossoverExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new CrossoverException(cause);
    } catch (final CrossoverException exception) {
      assertTrue(exception instanceof CrossoverException);
      assertSame(cause, exception.getCause());
    }
  }

}
