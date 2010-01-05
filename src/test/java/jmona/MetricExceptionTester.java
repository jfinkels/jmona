/**
 * MetricExceptionTester.java
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
 * Test class for the MetricException class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class MetricExceptionTester {

  /**
   * Test method for {@link jmona.MetricException#MetricException()}.
   */
  @Test
  public void testMetricException() {
    try {
      throw new MetricException();
    } catch (final MetricException exception) {
      assertTrue(exception instanceof MetricException);
    }
  }

  /**
   * Test method for
   * {@link jmona.MetricException#MetricException(java.lang.String)}.
   */
  @Test
  public void testMetricExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new MetricException(message);
    } catch (final MetricException exception) {
      assertTrue(exception instanceof MetricException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.MetricException#MetricException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testMetricExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new MetricException(message, cause);
    } catch (final MetricException exception) {
      assertTrue(exception instanceof MetricException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.MetricException#MetricException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testMetricExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new MetricException(cause);
    } catch (final MetricException exception) {
      assertTrue(exception instanceof MetricException);
      assertSame(cause, exception.getCause());
    }
  }

}
