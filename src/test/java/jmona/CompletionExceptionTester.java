/**
 * CompletionExceptionTester.java
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
 * Test class for the CompletionException class.
 * 
 * @author Jeffrey Finkelstein
 */
public class CompletionExceptionTester {

  /**
   * Test method for
   * {@link jmona.CompletionException#CompletionException()}.
   */
  @Test
  public void testCompletionException() {
    try {
      throw new CompletionException();
    } catch (final CompletionException exception) {
      assertTrue(exception instanceof CompletionException);
    }
  }

  /**
   * Test method for
   * {@link jmona.CompletionException#CompletionException(java.lang.String)}
   * .
   */
  @Test
  public void testCompletionExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new CompletionException(message);
    } catch (final CompletionException exception) {
      assertTrue(exception instanceof CompletionException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.CompletionException#CompletionException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testCompletionExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new CompletionException(message, cause);
    } catch (final CompletionException exception) {
      assertTrue(exception instanceof CompletionException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.CompletionException#CompletionException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testCompletionExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new CompletionException(cause);
    } catch (final CompletionException exception) {
      assertTrue(exception instanceof CompletionException);
      assertSame(cause, exception.getCause());
    }
  }

}
