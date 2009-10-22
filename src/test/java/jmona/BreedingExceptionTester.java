/**
 * BreedingExceptionTester.java
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
 * Test class for the BreedingException class.
 * 
 * @author jeff
 */
public class BreedingExceptionTester {

  /**
   * Test method for {@link jmona.BreedingException#BreedingException()}.
   */
  @Test
  public void testBreedingException() {
    try {
      throw new BreedingException();
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
    }
  }

  /**
   * Test method for
   * {@link jmona.BreedingException#BreedingException(java.lang.String)}.
   */
  @Test
  public void testBreedingExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new BreedingException(message);
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.BreedingException#BreedingException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testBreedingExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new BreedingException(message, cause);
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.BreedingException#BreedingException(java.lang.Throwable)}.
   */
  @Test
  public void testBreedingExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new BreedingException(cause);
    } catch (final BreedingException exception) {
      assertTrue(exception instanceof BreedingException);
      assertSame(cause, exception.getCause());
    }
  }

}
