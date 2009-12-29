/**
 * MutationExceptionTester.java
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

import org.junit.Test;

/**
 * Test class for the MutationException class.
 * 
 * @author jfinkels
 */
public class MutationExceptionTester {

  /**
   * Test method for
   * {@link jmona.exceptions.MutationException#MutationException()}.
   */
  @Test
  public void testMutationException() {
    try {
      throw new MutationException();
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.MutationException#MutationException(java.lang.String)}
   * .
   */
  @Test
  public void testMutationExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new MutationException(message);
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.MutationException#MutationException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testMutationExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new MutationException(message, cause);
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.exceptions.MutationException#MutationException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testMutationExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new MutationException(cause);
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
      assertSame(cause, exception.getCause());
    }
  }

}
