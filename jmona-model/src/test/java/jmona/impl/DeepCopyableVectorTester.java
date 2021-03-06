/**
 * DeepCopyableVectorTester.java
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import java.util.List;
import java.util.Vector;

import jfcommon.functional.Range;
import jfcommon.test.TestUtils;
import jmona.CopyingException;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DeepCopyableVector class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DeepCopyableVectorTester {

  /** The number of individuals in the vector. */
  public static final int NUM_INDIVIDUALS = 100;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The List of individuals to add to the vector. */
  private List<ExampleIndividual> beforeList = null;
  /** The vector under test in this class. */
  private DeepCopyableVector<ExampleIndividual> vector = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.beforeList = new Vector<ExampleIndividual>();

    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      this.beforeList.add(new ExampleIndividual());
    }

    try {
      this.vector = new DeepCopyableVector<ExampleIndividual>(this.beforeList);
    } catch (final CopyingException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.impl.DeepCopyableVector#DeepCopyableVector()}.
   */
  @Test
  public void testDeepCopyableVector() {
    this.vector = new DeepCopyableVector<ExampleIndividual>();
    assertEquals(0, this.vector.size());
  }

  /**
   * Test method for
   * {@link jmona.impl.DeepCopyableVector#DeepCopyableVector(java.lang.Iterable)}
   * .
   */
  @Test
  public void testDeepCopyableVectorCollectionOfE() {
    assertEquals(this.beforeList.size(), this.vector.size());
    assertNotSame(this.beforeList, this.vector);

    for (final int i : new Range(this.beforeList.size())) {
      assertNotSame(this.beforeList.get(i), this.vector.get(i));
      assertEquals(this.beforeList.get(i).fitness(), this.vector.get(i)
          .fitness(), ZERO_DELTA);
    }
  }

  /**
   * Test method for {@link jmona.impl.DeepCopyableVector#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    List<ExampleIndividual> afterList = null;
    try {
      afterList = this.vector.deepCopy();
    } catch (final CopyingException exception) {
      TestUtils.fail(exception);
    }

    assertFalse(Util.areEqual(afterList, this.vector));
    assertEquals(this.vector.size(), afterList.size());

    for (final int i : new Range(this.vector.size())) {
      assertNotSame(this.vector.get(i), afterList.get(i));
      assertEquals(this.vector.get(i).fitness(), afterList.get(i).fitness(),
          ZERO_DELTA);
    }
  }

}
