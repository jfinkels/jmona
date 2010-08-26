/**
 * MaximizingFitnessFunctionTester.java
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
import jmona.FitnessException;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the MaximizingFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class MaximizingFitnessFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.fitness.MaximizingFitnessFunction#typeOfExtremum()}.
   */
  @Test
  public void testTypeOfExtremum() {
    final double maximum = 10;
    final MaximizingFitnessFunction<ExampleIndividual> function = new MaximizingFitnessFunction<ExampleIndividual>(
        maximum) {
      @Override
      public double rawFitness(final ExampleIndividual individual) {
        return individual.fitness();
      }
    };
    assertEquals(KnownExtremumFitnessFunction.MAXIMUM, function
        .typeOfExtremum());

    try {
      assertEquals(maximum, function.standardizedFitness(new ExampleIndividual(
          0)), ZERO_DELTA);
      assertEquals(0, function.standardizedFitness(new ExampleIndividual(
          maximum)), ZERO_DELTA);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }

  }

}
