/**
 * FitnessExceptionTester.java
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
