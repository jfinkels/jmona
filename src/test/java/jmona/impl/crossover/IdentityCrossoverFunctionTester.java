/**
 * IdentityCrossoverFunctionTester.java
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
package jmona.impl.crossover;

import static org.junit.Assert.assertEquals;
import jmona.CrossoverException;
import jmona.CrossoverFunction;
import jmona.impl.crossover.IdentityCrossoverFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the IdentityCrossoverFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class IdentityCrossoverFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.crossover.IdentityCrossoverFunction#crossover(Object, Object)}
   * .
   */
  @Test
  public void testCrossover() {
    final CrossoverFunction<ExampleIndividual> function = new IdentityCrossoverFunction<ExampleIndividual>();

    final double fitness1 = 0;
    final double fitness2 = 1;

    final ExampleIndividual individual1 = new ExampleIndividual(0);
    final ExampleIndividual individual2 = new ExampleIndividual(1);

    try {
      function.crossover(individual1, individual2);
    } catch (final CrossoverException exception) {
      Util.fail(exception);
    }

    assertEquals(fitness1, individual1.fitness(), ZERO_DELTA);
    assertEquals(fitness2, individual2.fitness(), ZERO_DELTA);
  }

}
