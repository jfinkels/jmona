/**
 * PropertyNotWritableExceptionTester.java
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
 * Test class for the PropertyNotWritableException class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PropertyNotWritableExceptionTester {

  /**
   * Test method for
   * {@link jmona.PropertyNotWritableException#PropertyNotWritableException()}.
   */
  @Test
  public void testPropertyNotWritableException() {
    try {
      throw new PropertyNotWritableException();
    } catch (final PropertyNotWritableException exception) {
      assertTrue(exception instanceof PropertyNotWritableException);
    }
  }

  /**
   * Test method for
   * {@link jmona.PropertyNotWritableException#PropertyNotWritableException(java.lang.String)}
   * .
   */
  @Test
  public void testPropertyNotWritableExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new PropertyNotWritableException(message);
    } catch (final PropertyNotWritableException exception) {
      assertTrue(exception instanceof PropertyNotWritableException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.PropertyNotWritableException#PropertyNotWritableException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testPropertyNotWritableExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new PropertyNotWritableException(message, cause);
    } catch (final PropertyNotWritableException exception) {
      assertTrue(exception instanceof PropertyNotWritableException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.PropertyNotWritableException#PropertyNotWritableException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testPropertyNotWritableExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new PropertyNotWritableException(cause);
    } catch (final PropertyNotWritableException exception) {
      assertTrue(exception instanceof PropertyNotWritableException);
      assertSame(cause, exception.getCause());
    }
  }

}
