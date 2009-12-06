/**
 * EvaluationExceptionTester.java
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
 * Test class for the EvaluationException class.
 * 
 * @author jfinkels
 */
public class EvaluationExceptionTester {

  /**
   * Test method for {@link jmona.gp.EvaluationException#EvaluationException()}.
   */
  @Test
  public void testEvaluationException() {
    try {
      throw new EvaluationException();
    } catch (final EvaluationException exception) {
      assertTrue(exception instanceof EvaluationException);
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.EvaluationException#EvaluationException(java.lang.String)}.
   */
  @Test
  public void testEvaluationExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new EvaluationException(message);
    } catch (final EvaluationException exception) {
      assertTrue(exception instanceof EvaluationException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.EvaluationException#EvaluationException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testEvaluationExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new EvaluationException(message, cause);
    } catch (final EvaluationException exception) {
      assertTrue(exception instanceof EvaluationException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.EvaluationException#EvaluationException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testEvaluationExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new EvaluationException(cause);
    } catch (final EvaluationException exception) {
      assertTrue(exception instanceof EvaluationException);
      assertSame(cause, exception.getCause());
    }
  }

}
