/**
 * SelectionExceptionTester.java
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
 * Test class for the SelectionException class.
 * 
 * @author Jeffrey Finkelstein
 */
public class SelectionExceptionTester {

  /**
   * Test method for
   * {@link jmona.SelectionException#SelectionException()}.
   */
  @Test
  public void testSelectionException() {
    try {
      throw new SelectionException();
    } catch (final SelectionException exception) {
      assertTrue(exception instanceof SelectionException);
    }
  }

  /**
   * Test method for
   * {@link jmona.SelectionException#SelectionException(java.lang.String)}
   * .
   */
  @Test
  public void testSelectionExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new SelectionException(message);
    } catch (final SelectionException exception) {
      assertTrue(exception instanceof SelectionException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.SelectionException#SelectionException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testSelectionExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new SelectionException(message, cause);
    } catch (final SelectionException exception) {
      assertTrue(exception instanceof SelectionException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.SelectionException#SelectionException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testSelectionExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new SelectionException(cause);
    } catch (final SelectionException exception) {
      assertTrue(exception instanceof SelectionException);
      assertSame(cause, exception.getCause());
    }
  }

}
