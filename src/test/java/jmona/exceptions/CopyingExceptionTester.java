/**
 * CopyingExceptionTester.java
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
import jmona.exceptions.CopyingException;

import org.junit.Test;

/**
 * Test class for the CopyingException class.
 * 
 * @author jfinkels
 */
public class CopyingExceptionTester {

  /**
   * Test method for {@link jmona.exceptions.CopyingException#CopyingException()}.
   */
  @Test
  public void testCopyingException() {
    try {
      throw new CopyingException();
    } catch (final CopyingException exception) {
      assertTrue(exception instanceof CopyingException);
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.CopyingException#CopyingException(java.lang.String)}.
   */
  @Test
  public void testCopyingExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new CopyingException(message);
    } catch (final CopyingException exception) {
      assertTrue(exception instanceof CopyingException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.CopyingException#CopyingException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testCopyingExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new CopyingException(message, cause);
    } catch (final CopyingException exception) {
      assertTrue(exception instanceof CopyingException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.CopyingException#CopyingException(java.lang.Throwable)}.
   */
  @Test
  public void testCopyingExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new CopyingException(cause);
    } catch (final CopyingException exception) {
      assertTrue(exception instanceof CopyingException);
      assertSame(cause, exception.getCause());
    }
  }

}
