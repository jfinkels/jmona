/**
 * DeepCopyableListFactoryTester.java
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
import static org.junit.Assert.assertTrue;
import jfcommon.test.TestUtils;
import jmona.CopyingException;
import jmona.DeepCopyableList;
import jmona.InitializationException;
import jmona.PropertyNotSetException;
import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleIndividualFactory;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the DeepCopyableListFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DeepCopyableListFactoryTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.impl.DeepCopyableListFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    final int size = 1;

    final DeepCopyableListFactory<ExampleIndividual> factory = new DeepCopyableListFactory<ExampleIndividual>(
        size);

    try {
      factory.createObject();
      TestUtils.shouldHaveThrownException();
    } catch (final PropertyNotSetException exception) {
      // elementFactory has not been set
      assertTrue(exception instanceof PropertyNotSetException);
    } catch (final InitializationException exception) {
      TestUtils.fail(exception);
    }

    factory.setElementFactory(new ExampleIndividualFactory());

    // create a list
    DeepCopyableList<ExampleIndividual> beforeList = null;
    try {
      beforeList = factory.createObject();
    } catch (final InitializationException exception) {
      TestUtils.fail(exception);
    }

    DeepCopyableList<ExampleIndividual> afterList = null;
    try {
      afterList = beforeList.deepCopy();
    } catch (final CopyingException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(size, beforeList.size());
    assertEquals(size, afterList.size());

    assertNotSame(beforeList, afterList);

    assertFalse(Util.areEqual(beforeList, afterList));

    for (final int i : new Range(size)) {
      assertNotSame(beforeList.get(i), afterList.get(i));
      assertEquals(beforeList.get(i).fitness(), afterList.get(i).fitness(),
          ZERO_DELTA);
    }
  }

}
