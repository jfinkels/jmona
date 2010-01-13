/**
 * AbstractExceptionTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import jmona.ComparisonException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public abstract class AbstractExceptionTester {

  /** The message to provide to the constructors of the Exceptions under test. */
  public static final String TEST_MESSAGE = "Hello, world!";
  /** The Throwable to provide to the constructors of the Exceptions under test. */
  public static final Throwable TEST_CAUSE = new Exception();
  private Exception exceptionNoArgs = null;
  private Exception exceptionString = null;
  private Exception exceptionThrowable = null;
  private Exception exceptionStringThrowable = null;

  private final Class<? extends Exception> exceptionClass;

  public AbstractExceptionTester(final Class<? extends Exception> clazz) {
    this.exceptionClass = clazz;
  }

  @Before
  public void setUp() {
    try {
      this.exceptionNoArgs = this.exceptionClass.getConstructor().newInstance();
      this.exceptionString = this.exceptionClass.getConstructor(String.class)
          .newInstance(TEST_MESSAGE);
      this.exceptionThrowable = this.exceptionClass.getConstructor(
          Throwable.class).newInstance(TEST_CAUSE);
      this.exceptionStringThrowable = this.exceptionClass.getConstructor(
          String.class, Throwable.class).newInstance(TEST_MESSAGE, TEST_CAUSE);
    } catch (final IllegalArgumentException exception) {
      Util.fail(exception);
    } catch (final SecurityException exception) {
      Util.fail(exception);
    } catch (final InstantiationException exception) {
      Util.fail(exception);
    } catch (final IllegalAccessException exception) {
      Util.fail(exception);
    } catch (final InvocationTargetException exception) {
      Util.fail(exception);
    } catch (final NoSuchMethodException exception) {
      Util.fail(exception);
    }
  }

  @Test
  public void testException() {
    try {
      throw this.exceptionNoArgs;
    } catch (final Exception exception) {
      assertTrue(exception.getClass().equals(this.exceptionClass));
    }

    try {
      throw this.exceptionString;
    } catch (final Exception exception) {
      assertTrue(exception.getClass().equals(this.exceptionClass));
      assertSame(TEST_MESSAGE, exception.getMessage());
    }

    try {
      throw this.exceptionThrowable;
    } catch (final Exception exception) {
      assertTrue(exception.getClass().equals(this.exceptionClass));
      assertSame(TEST_CAUSE, exception.getCause());
    }

    try {
      throw this.exceptionStringThrowable;
    } catch (final Exception exception) {
      assertTrue(exception.getClass().equals(this.exceptionClass));
      assertSame(TEST_MESSAGE, exception.getMessage());
      assertSame(TEST_CAUSE, exception.getCause());
    }
  }
}
