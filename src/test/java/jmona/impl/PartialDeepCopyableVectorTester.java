/**
 * PartialDeepCopyableVectorTester.java
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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.functional.Range;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PartialDeepCopyableVector class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PartialDeepCopyableVectorTester {

  /** The number of individuals in the vector. */
  public static final int NUM_ELEMENTS = 100;
  /** The List of individuals to add to the vector. */
  private List<Byte> beforeList = null;
  /** The vector under test in this class. */
  private PartialDeepCopyableVector<Byte> vector = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.beforeList = new Vector<Byte>();

    for (final int i : new Range(NUM_ELEMENTS)) {
      this.beforeList.add(new Byte((byte) 0));
      this.beforeList.add(new Byte((byte) 1));
    }

    this.vector = new PartialDeepCopyableVector<Byte>(this.beforeList);
  }

  /**
   * Test method for
   * {@link jmona.impl.PartialDeepCopyableVector#PartialDeepCopyableVector()}.
   */
  @Test
  public void testPartialDeepCopyableVector() {
    this.vector = new PartialDeepCopyableVector<Byte>();
    assertEquals(0, this.vector.size());

  }

  /**
   * Test method for
   * {@link jmona.impl.PartialDeepCopyableVector#PartialDeepCopyableVector(java.lang.Iterable)}
   * .
   */
  @Test
  public void testPartialDeepCopyableVectorListOfE() {
    assertEquals(this.beforeList.size(), this.vector.size());
    assertNotSame(this.beforeList, this.vector);

    for (final int i : new Range(this.beforeList.size())) {
      assertSame(this.beforeList.get(i), this.vector.get(i));
      assertEquals(this.beforeList.get(i), this.vector.get(i));
    }
  }

  /**
   * Test method for {@link jmona.impl.PartialDeepCopyableVector#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    List<Byte> afterList = null;
    try {
      afterList = this.vector.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    assertTrue(Util.areEqual(afterList, this.vector));
    assertEquals(this.vector.size(), afterList.size());

    for (final int i : new Range(this.vector.size())) {
      assertSame(this.vector.get(i), afterList.get(i));
      assertEquals(this.vector.get(i), afterList.get(i));
    }
  }

}
