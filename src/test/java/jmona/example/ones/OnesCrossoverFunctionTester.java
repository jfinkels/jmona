/**
 * OnesCrossoverFunctionTester.java
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
import static org.junit.Assert.assertTrue;
import jmona.Pair;

import org.junit.Test;

/**
 * Test class for the OnesCrossoverFunction class.
 * 
 * @author jeff
 */
public class OnesCrossoverFunctionTester {

  /**
   * Test method for
   * {@link jmona.example.ones.OnesCrossoverFunction#crossover(jmona.Pair)}.
   */
  @Test
  public final void testCrossover() {
    // create some individuals
    final OnesIndividual left = new OnesIndividual(new short[] { 0, 0, 0, 0, 0,
        0, 0, 0 });
    final OnesIndividual right = new OnesIndividual(new short[] { 1, 1, 1, 1,
        1, 1, 1, 1 });

    // pair them together
    final Pair<OnesIndividual, OnesIndividual> pair = new Pair<OnesIndividual, OnesIndividual>(
        left, right);

    final OnesCrossoverFunction function = new OnesCrossoverFunction();

    final int beforeLengthLeft = pair.left().gene().length;
    final int beforeLengthRight = pair.right().gene().length;

    final Pair<OnesIndividual, OnesIndividual> crossed = function
        .crossover(pair);

    assertEquals(beforeLengthLeft, crossed.left().gene().length);
    assertEquals(beforeLengthRight, crossed.right().gene().length);

    for (int i = crossed.left().gene().length - 1; i >= 0; --i) {
      assertTrue(crossed.left().gene()[i] != crossed.right().gene()[i]);
    }
  }
}
