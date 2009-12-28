/**
 * ProcessingExceptionTester.java
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
import jmona.exceptions.ProcessingException;

import org.junit.Test;

/**
 * Test class for the ProcessingException class.
 * 
 * @author jfinkels
 */
public class ProcessingExceptionTester {

  /**
   * Test method for {@link jmona.exceptions.ProcessingException#ProcessingException()}.
   */
  @Test
  public void testProcessingException() {
    try {
      throw new ProcessingException();
    } catch (final ProcessingException exception) {
      assertTrue(exception instanceof ProcessingException);
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.ProcessingException#ProcessingException(java.lang.String)}.
   */
  @Test
  public void testProcessingExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new ProcessingException(message);
    } catch (final ProcessingException exception) {
      assertTrue(exception instanceof ProcessingException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.ProcessingException#ProcessingException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testProcessingExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new ProcessingException(message, cause);
    } catch (final ProcessingException exception) {
      assertTrue(exception instanceof ProcessingException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.ProcessingException#ProcessingException(java.lang.Throwable)}.
   */
  @Test
  public void testProcessingExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new ProcessingException(cause);
    } catch (final ProcessingException exception) {
      assertTrue(exception instanceof ProcessingException);
      assertSame(cause, exception.getCause());
    }
  }

}
