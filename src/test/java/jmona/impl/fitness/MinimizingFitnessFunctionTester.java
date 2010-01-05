/**
 * MinimizingFitnessFunctionTester.java
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
 * Test class for the MinimizingFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class MinimizingFitnessFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.fitness.MinimizingFitnessFunction#MinimizingFitnessFunction()}
   * .
   */
  @Test
  public void testMinimizingFitnessFunction() {
    final double minimum = 10;
    MinimizingFitnessFunction<ExampleIndividual> function = new MinimizingFitnessFunction<ExampleIndividual>(
        minimum) {
      @Override
      public double rawFitness(final ExampleIndividual individual) {
        return individual.fitness();
      }
    };
    assertEquals(KnownExtremumFitnessFunction.MINIMUM, function
        .typeOfExtremum());

    try {
      assertEquals(minimum, function.standardizedFitness(new ExampleIndividual(
          2 * minimum)), ZERO_DELTA);
      assertEquals(0, function.standardizedFitness(new ExampleIndividual(
          minimum)), ZERO_DELTA);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

    function = new MinimizingFitnessFunction<ExampleIndividual>() {
      @Override
      public double rawFitness(final ExampleIndividual individual) {
        return individual.fitness();
      }
    };

    try {
      assertEquals(minimum, function.standardizedFitness(new ExampleIndividual(
          minimum)), ZERO_DELTA);
      assertEquals(0, function.standardizedFitness(new ExampleIndividual(0)),
          ZERO_DELTA);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

  }

}
