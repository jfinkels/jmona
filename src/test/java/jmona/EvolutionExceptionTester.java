/**
 * EvolutionExceptionTester.java
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
 * Test class for the EvolutionException class.
 * 
 * @author Jeffrey Finkelstein
 */
public class EvolutionExceptionTester {

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException()}.
   */
  @Test
  public void testEvolutionException() {
    try {
      throw new EvolutionException();
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
    }
  }

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException(java.lang.String)}
   * .
   */
  @Test
  public void testEvolutionExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new EvolutionException(message);
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testEvolutionExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new EvolutionException(message, cause);
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.EvolutionException#EvolutionException(java.lang.Throwable)}
   * .
   */
  @Test
  public void testEvolutionExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new EvolutionException(cause);
    } catch (final EvolutionException exception) {
      assertTrue(exception instanceof EvolutionException);
      assertSame(cause, exception.getCause());
    }
  }

}
