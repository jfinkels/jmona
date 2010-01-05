/**
 * FitnessLoggingPostProcessorTester.java
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
package jmona.impl.postprocessing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.GeneticEvolutionContext;
import jmona.LoggingException;
import jmona.impl.context.AbstractEvolutionContext;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the FitnessLoggingPostProcessor class.
 * 
 * @author Jeffrey Finkelstein
 */
public class FitnessLoggingPostProcessorTester {

  /** The fitness of one individual. */
  public static final int FITNESS_ONE = 1;
  /** The fitness of another individual. */
  public static final int FITNESS_TWO = 2;

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.FitnessLoggingPostProcessor#message(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testMessage() {
    final ExampleIndividual individual1 = new ExampleIndividual(FITNESS_ONE);
    final ExampleIndividual individual2 = new ExampleIndividual(FITNESS_TWO);

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    ExampleEvolutionContext context = new ExampleEvolutionContext(population);
    context.setFitnessFunction(new ExampleFitnessFunction());

    final FitnessLoggingPostProcessor<ExampleIndividual> processor = new FitnessLoggingPostProcessor<ExampleIndividual>();

    String result = null;
    try {
      result = processor.message(context);
    } catch (final LoggingException exception) {
      Util.fail(exception);
    }

    assertNotNull(result);
    assertTrue(result.contains(String.valueOf(individual1.fitness())));
    assertTrue(result.contains(String.valueOf(individual2.fitness())));

    final EvolutionContext<ExampleIndividual> badContext = new AbstractEvolutionContext<ExampleIndividual>(
        population) {
      @Override
      protected void executeGenerationStep() throws EvolutionException {
        // intentionally unimplemented
      }
    };

    try {
      processor.message(badContext);
      Util.shouldHaveThrownException();
    } catch (final LoggingException exception) {
      assertFalse(badContext.getClass().equals(GeneticEvolutionContext.class));
    }

  }

}
