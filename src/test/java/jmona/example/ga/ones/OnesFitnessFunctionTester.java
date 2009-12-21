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
package jmona.example.ga.ones;

import static org.junit.Assert.assertEquals;
import jmona.ga.BinaryString;
import jmona.ga.impl.CharArrayBinaryString;

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
   * {@link jmona.example.ga.ones.OnesFitnessFunction#fitness(jmona.ga.BinaryString)}
   * .
   */
  @Test
  public void testFitness() {
    final OnesFitnessFunction function = new OnesFitnessFunction();
    final BinaryString individual = new CharArrayBinaryString(4, false);

    assertEquals(0, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(0);
    assertEquals(1, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(1);
    assertEquals(2, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(2);
    assertEquals(3, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(3);
    assertEquals(4, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(2);
    assertEquals(3, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(1);
    assertEquals(2, function.fitness(individual), ZERO_DELTA);

    individual.flipBit(0);
    assertEquals(1, function.fitness(individual), ZERO_DELTA);
  }

}
