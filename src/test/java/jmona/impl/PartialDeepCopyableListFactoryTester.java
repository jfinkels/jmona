/**
 * PartialDeepCopyableListFactoryTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import jmona.CopyingException;
import jmona.DeepCopyableList;
import jmona.Factory;
import jmona.InitializationException;
import jmona.functional.Range;
import jmona.ga.impl.BitFactory;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PartialDeepCopyableListFactory class.
 * 
 * @author Jeffrey Finkelstein
 */
public class PartialDeepCopyableListFactoryTester {

  /** The factory under test. */
  private PartialDeepCopyableListFactory<Byte> factory = null;
  /** The size of the list to create. */
  public static final int SIZE = 100;
  /** The element factory used by the list factory. */
  private Factory<Byte> elementFactory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.elementFactory = new BitFactory();
    this.factory = new PartialDeepCopyableListFactory<Byte>();
    this.factory.setElementFactory(this.elementFactory);
    this.factory.setSize(SIZE);
  }

  /**
   * Test method for
   * {@link jmona.impl.PartialDeepCopyableListFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    DeepCopyableList<Byte> list = null;
    try {
      list = this.factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    DeepCopyableList<Byte> clonedList = null;
    try {
      clonedList = list.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    assertEquals(clonedList.size(), list.size());
    assertNotSame(clonedList, list);
    assertTrue(Util.areEqual(clonedList, list));
    for (final int i : new Range(list.size())) {
      assertSame(list.get(i), clonedList.get(i));
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.PartialDeepCopyableListFactory#elementFactory()}.
   */
  @Test
  public void testElementFactory() {
    assertSame(this.elementFactory, this.factory.elementFactory());
  }

  /**
   * Test method for
   * {@link jmona.impl.PartialDeepCopyableListFactory#setElementFactory(jmona.Factory)}
   * .
   */
  @Test
  public void testSetElementFactory() {
    this.factory.setElementFactory(null);
    try {
      this.factory.createObject();
      Util.shouldHaveThrownException();
    } catch (final InitializationException exception) {
      assertNull(this.factory.elementFactory());
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.PartialDeepCopyableListFactory#setSize(int)}.
   */
  @Test
  public void testSetSize() {
    this.factory.setSize(2 * SIZE);
    List<Byte> list = null;
    try {
      list = this.factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    assertEquals(2 * SIZE, list.size());
  }

  /**
   * Test method for {@link jmona.impl.PartialDeepCopyableListFactory#size()}.
   */
  @Test
  public void testSize() {
    List<Byte> list = null;

    try {
      list = this.factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    assertEquals(SIZE, list.size());
  }

}
