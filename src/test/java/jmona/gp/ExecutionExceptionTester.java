/**
 * ExecutionExceptionTester.java
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
package jmona.gp;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the ExecutionException class.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExecutionExceptionTester {

  /**
   * Test method for {@link jmona.gp.ExecutionException#ExecutionException()}.
   */
  @Test
  public void testExecutionException() {
    try {
      throw new ExecutionException();
    } catch (final ExecutionException exception) {
      assertTrue(exception instanceof ExecutionException);
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.ExecutionException#ExecutionException(java.lang.String)}.
   */
  @Test
  public void testExecutionExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new ExecutionException(message);
    } catch (final ExecutionException exception) {
      assertTrue(exception instanceof ExecutionException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.ExecutionException#ExecutionException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testExecutionExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new ExecutionException(message, cause);
    } catch (final ExecutionException exception) {
      assertTrue(exception instanceof ExecutionException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.ExecutionException#ExecutionException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testExecutionExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new ExecutionException(cause);
    } catch (final ExecutionException exception) {
      assertTrue(exception instanceof ExecutionException);
      assertSame(cause, exception.getCause());
    }
  }

}
