/**
 * AlreadyVisitedExceptionTester.java
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
package jmona.aco;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the AlreadyVisitedException class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AlreadyVisitedExceptionTester {

  /**
   * Test method for
   * {@link jmona.AlreadyVisitedException#AlreadyVisitedException()}.
   */
  @Test
  public void testAlreadyVisitedException() {
    try {
      throw new AlreadyVisitedException();
    } catch (final AlreadyVisitedException exception) {
      assertTrue(exception instanceof AlreadyVisitedException);
    }
  }

  /**
   * Test method for
   * {@link jmona.AlreadyVisitedException#AlreadyVisitedException(java.lang.String)}
   * .
   */
  @Test
  public void testAlreadyVisitedExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new AlreadyVisitedException(message);
    } catch (final AlreadyVisitedException exception) {
      assertTrue(exception instanceof AlreadyVisitedException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.AlreadyVisitedException#AlreadyVisitedException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testAlreadyVisitedExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new AlreadyVisitedException(message, cause);
    } catch (final AlreadyVisitedException exception) {
      assertTrue(exception instanceof AlreadyVisitedException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.AlreadyVisitedException#AlreadyVisitedException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testAlreadyVisitedExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new AlreadyVisitedException(cause);
    } catch (final AlreadyVisitedException exception) {
      assertTrue(exception instanceof AlreadyVisitedException);
      assertSame(cause, exception.getCause());
    }
  }

}
