/**
 * GameplayExceptionTester.java
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
package jmona.game;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the GameplayException class.
 * 
 * @author jfinkels
 */
public class GameplayExceptionTester {

  /**
   * Test method for {@link jmona.GameplayException#GameplayException()}.
   */
  @Test
  public void testGameplayException() {
    try {
      throw new GameplayException();
    } catch (final GameplayException exception) {
      assertTrue(exception instanceof GameplayException);
    }
  }

  /**
   * Test method for
   * {@link jmona.GameplayException#GameplayException(java.lang.String)}.
   */
  @Test
  public void testGameplayExceptionString() {
    final String message = "Hello, world!";
    try {
      throw new GameplayException(message);
    } catch (final GameplayException exception) {
      assertTrue(exception instanceof GameplayException);
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.GameplayException#GameplayException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testGameplayExceptionStringThrowable() {
    final Throwable cause = new Exception();
    final String message = "Hello, world!";
    try {
      throw new GameplayException(message, cause);
    } catch (final GameplayException exception) {
      assertTrue(exception instanceof GameplayException);
      assertSame(cause, exception.getCause());
      assertSame(message, exception.getMessage());
    }
  }

  /**
   * Test method for
   * {@link jmona.GameplayException#GameplayException(java.lang.Throwable)}.
   */
  @Test
  public void testGameplayExceptionThrowable() {
    final Throwable cause = new Exception();
    try {
      throw new GameplayException(cause);
    } catch (final GameplayException exception) {
      assertTrue(exception instanceof GameplayException);
      assertSame(cause, exception.getCause());
    }
  }

}