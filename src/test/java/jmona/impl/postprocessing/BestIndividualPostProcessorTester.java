/**
 * BestIndividualPostProcessorTester.java
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
package jmona.impl.postprocessing;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.GeneticEvolutionContext;
import jmona.LoggingException;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the BestIndividualPostProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BestIndividualPostProcessorTester {

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.BestIndividualPostProcessor#message(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testMessage() {
    final BestIndividualPostProcessor<ExampleIndividual> processor = new BestIndividualPostProcessor<ExampleIndividual>();

    final ExampleIndividual individual1 = new ExampleIndividual(1);
    final ExampleIndividual individual2 = new ExampleIndividual(2);

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    final GeneticEvolutionContext<ExampleIndividual> context = new ExampleEvolutionContext(
        population);
    context.setFitnessFunction(new ExampleFitnessFunction());

    String fittestIndividual = null;
    try {
      fittestIndividual = processor.message(context);
    } catch (final LoggingException exception) {
      Util.fail(exception);
    }

    assertTrue(fittestIndividual.contains(individual1.toString()));
    assertTrue(fittestIndividual
        .contains(String.valueOf(individual1.fitness())));
  }

}
