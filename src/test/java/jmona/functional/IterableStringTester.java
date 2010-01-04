/**
 * IterableStringTester.java
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
package jmona.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import jmona.functional.IterableString;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class IterableStringTester {
  /** The string to test. */
  public static final String STRING = "Hello, world!";

  /**
   * Test method for {@link jmona.functional.IterableString#iterator()}.
   */
  @Test
  public void testIterator() {
    final Iterator<Character> iterator = new IterableString(STRING).iterator();

    int i = 0;
    while (iterator.hasNext()) {
      assertEquals(STRING.charAt(i), iterator.next().charValue());
      i += 1;
    }

    assertFalse(iterator.hasNext());
    try {
      iterator.next();
      Util.shouldHaveThrownException();
    } catch (final NoSuchElementException exception) {
      assertEquals(STRING.length(), i);
    }
  }

  /**
   * Test method for {@link jmona.functional.IterableString#hasNext()}.
   */
  @Test
  public void testHasNext() {
    assertFalse(new IterableString("").hasNext());
  }

  /**
   * Test method for {@link jmona.functional.IterableString#next()}.
   */
  @Test
  public void testNext() {
    try {
      new IterableString("").next();
      Util.shouldHaveThrownException();
    } catch (final NoSuchElementException exception) {
      assertTrue(exception instanceof NoSuchElementException);
    }
  }

  /**
   * Test method for {@link jmona.functional.IterableString#remove()}.
   */
  @Test
  public void testRemove() {
    try {
      new IterableString(STRING).remove();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }

}
