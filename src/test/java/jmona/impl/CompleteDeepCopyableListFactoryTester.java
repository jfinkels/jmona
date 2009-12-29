/**
 * CompleteDeepCopyableListFactoryTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import jmona.CopyingException;
import jmona.DeepCopyableList;
import jmona.InitializationException;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleIndividualFactory;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the CompleteDeepCopyableListFactory class.
 * 
 * @author jfinkels
 */
public class CompleteDeepCopyableListFactoryTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.CompleteDeepCopyableListFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    final int size = 1;

    final CompleteDeepCopyableListFactory<ExampleIndividual> factory = new CompleteDeepCopyableListFactory<ExampleIndividual>();
    factory.setSize(size);

    try {
      factory.createObject();
      Util.shouldHaveThrownException();
    } catch (final InitializationException exception) {
      // elementFactory has not been set
      assertTrue(exception instanceof InitializationException);
    }

    factory.setElementFactory(new ExampleIndividualFactory());

    // create a list
    DeepCopyableList<ExampleIndividual> beforeList = null;
    try {
      beforeList = factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    DeepCopyableList<ExampleIndividual> afterList = null;
    try {
      afterList = beforeList.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    assertEquals(size, beforeList.size());
    assertEquals(size, afterList.size());

    assertNotSame(beforeList, afterList);

    assertFalse(Util.areEqual(beforeList, afterList));

    for (int i = 0; i < size; ++i) {
      assertNotSame(beforeList.get(i), afterList.get(i));
      assertEquals(beforeList.get(i).fitness(), afterList.get(i).fitness(),
          ZERO_DELTA);
    }
  }

}
