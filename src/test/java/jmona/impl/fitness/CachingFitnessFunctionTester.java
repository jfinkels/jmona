/**
 * CachingFitnessFunctionTester.java
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
package jmona.impl.fitness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import jmona.FitnessException;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the CachingFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class CachingFitnessFunctionTester {
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.fitness.CachingFitnessFunction#getCachedFitness(java.lang.Object)}
   * .
   */
  @Test
  public void testGetCachedFitness() {
    final CachingFitnessFunction<ExampleIndividual> function = new ExampleFitnessFunction();

    ExampleIndividual individual = new ExampleIndividual(0);

    assertNull(function.getCachedFitness(individual));

    try {
      // cache the fitness
      function.adjustedFitness(individual);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }

    assertNotNull(function.getCachedFitness(individual));
    assertEquals(1.0, function.getCachedFitness(individual), ZERO_DELTA);

    individual = new ExampleIndividual(1);

    function.putCachedFitness(individual, 0.5);

    assertNotNull(function.getCachedFitness(individual));
    assertEquals(0.5, function.getCachedFitness(individual), ZERO_DELTA);

  }

}
