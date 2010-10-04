/**
 * BestIndividualProcessorTester.java
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
package jmona.impl.processing;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the BestIndividualProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BestIndividualProcessorTester {

  /**
   * Test method for
   * {@link jmona.impl.processing.BestIndividualProcessor#message(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testMessage() {
    final BestIndividualProcessor<ExampleIndividual, ExampleEvolutionContext> processor = new BestIndividualProcessor<ExampleIndividual, ExampleEvolutionContext>();

    final ExampleIndividual individual1 = new ExampleIndividual(1);
    final ExampleIndividual individual2 = new ExampleIndividual(2);

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    final FitnessFunction<ExampleIndividual> fitnessFunction = new ExampleFitnessFunction();

    final ExampleEvolutionContext context = new ExampleEvolutionContext(
        population);

    try {
      context.setFitnessFunction(fitnessFunction);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }

    final String fittestIndividual = processor.message(context);

    assertTrue(fittestIndividual.contains(individual1.toString()));
    assertTrue(fittestIndividual
        .contains(String.valueOf(individual1.fitness())));
  }

}
