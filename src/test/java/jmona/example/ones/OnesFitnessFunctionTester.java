/**
 * OnesFitnessFunctionTester.java
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
package jmona.example.ones;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jmona.DeepCopyableList;
import jmona.impl.DeepCopyableVector;
import jmona.impl.mutable.MutableByte;

import org.junit.Test;

/**
 * Test class for the OnesFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class OnesFitnessFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.ones.OnesFitnessFunction#rawFitness(List)}.
   */
  @Test
  public void testRawFitness() {
    final int length = 10;
    final OnesFitnessFunction function = new OnesFitnessFunction(length);
    final DeepCopyableList<MutableByte> individual = new DeepCopyableVector<MutableByte>();

    for (int i = 0; i < length; ++i) {
      individual.add(new MutableByte(0));
    }

    assertEquals(0, function.rawFitness(individual), ZERO_DELTA);

    for (int i = 0; i < length; ++i) {
      individual.set(i, new MutableByte(1));
      assertEquals(i + 1, function.rawFitness(individual), ZERO_DELTA);
    }

    for (int i = length - 1; i >= 0; --i) {
      individual.set(i, new MutableByte(0));
      assertEquals(i, function.rawFitness(individual), ZERO_DELTA);
    }

  }

}
