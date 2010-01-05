/**
 * AdjustedFitnessGetterTester.java
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
import jmona.MappingException;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the AdjustedFitnessGetter class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AdjustedFitnessGetterTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.fitness.AdjustedFitnessGetter#execute(java.lang.Object)}.
   */
  @Test
  public void testExecute() {
    final AdjustedFitnessGetter<ExampleIndividual> function = new AdjustedFitnessGetter<ExampleIndividual>();
    function.setFitnessFunction(new ExampleFitnessFunction());

    final double fitness = 10;
    final ExampleIndividual individual = new ExampleIndividual(fitness);
    try {
      assertEquals(1.0 / (1 + fitness), function.execute(individual)
          .doubleValue(), ZERO_DELTA);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }
  }

}
