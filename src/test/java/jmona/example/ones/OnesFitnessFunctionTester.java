/**
 * OnesFitnessFunctionTester.java
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
package jmona.example.ones;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

/**
 * Test class for the OnesFitnessFunction class.
 * 
 * @author jfinkels
 */
public class OnesFitnessFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.ones.OnesFitnessFunction#fitness(jmona.ga.BinaryString)}
   * .
   */
  @Test
  public void testFitness() {
    final int length = 10;
    final OnesFitnessFunction function = new OnesFitnessFunction();
    final List<Byte> individual = new Vector<Byte>();

    for (int i = 0; i < length; ++i) {
      individual.add((byte) 0);
    }

    assertEquals(0, function.fitness(individual), ZERO_DELTA);

    for (int i = 0; i < length; ++i) {
      individual.set(i, (byte) 1);
      assertEquals(i + 1, function.fitness(individual), ZERO_DELTA);
    }

    for (int i = length - 1; i >= 0; --i) {
      individual.set(i, (byte) 0);
      assertEquals(i, function.fitness(individual), ZERO_DELTA);
    }

  }

}
