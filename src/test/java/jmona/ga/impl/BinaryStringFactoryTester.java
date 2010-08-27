/**
 * BinaryStringFactoryTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import jfcommon.functional.Range;
import jmona.DeepCopyableList;
import jmona.impl.ListUtils;
import jmona.impl.mutable.MutableByte;

import org.junit.Test;

/**
 * Test class for the BinaryStringFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BinaryStringFactoryTester {

  /** The length of the binary string to create. */
  public static final int LENGTH = 1 << 12;
  /** The number of individuals that the factory will create. */
  public static final int NUM_INDIVIDUALS = 1000;

  /**
   * Test method for {@link jmona.ga.impl.BinaryStringFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    final BinaryStringFactory factory = new BinaryStringFactory(LENGTH);
    
    int zeroCount = 0;
    int oneCount = 0;
    double delta = LENGTH * 0.1;
    for (final int i : new Range(NUM_INDIVIDUALS)) {
      final DeepCopyableList<MutableByte> individual = factory.createObject();
      
      // count the number of ones and the number of zeros
      final Map<MutableByte, Integer> counts = ListUtils.count(individual);
      
      final int zeros = counts.get(MutableByte.ZERO);
      final int ones = counts.get(MutableByte.ONE);
      
      assertEquals(zeros, ones, delta);
      
      zeroCount += zeros;
      oneCount += ones;
    }
    
    delta *= NUM_INDIVIDUALS;
    assertEquals(zeroCount, oneCount, delta);
  }

}
