/**
 * GAEvolutionContextTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.Population;
import jmona.impl.DefaultPopulation;
import jmona.impl.example.ExampleCrossoverFunction;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;
import jmona.impl.selection.FitnessProportionateSelection;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the GAEvolutionContext class.
 * 
 * @author jfinkels
 */
public class GAEvolutionContextTester {

  /**
   * Test method for {@link jmona.ga.impl.GAEvolutionContext#stepGeneration()}.
   */
  @Test
  public void testStepGeneration() {
    // create two individuals to put into a population
    final ExampleIndividual individual1 = new ExampleIndividual();
    final ExampleIndividual individual2 = new ExampleIndividual();

    // put those two individuals in a population
    final Population<ExampleIndividual> population = new DefaultPopulation<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    // instantiate a new EvolutionContext
    final GAEvolutionContext<ExampleIndividual> context = new GAEvolutionContext<ExampleIndividual>(
        population);

    // set the necessary functions on the context
    context.setCrossoverFunction(new ExampleCrossoverFunction());
    context.setMutationFunction(new ExampleMutationFunction());
    context
        .setSelectionFunction(new FitnessProportionateSelection<ExampleIndividual>());
    try {
      context.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

    assertEquals(0, context.currentGeneration());

    assertEquals(2, context.currentPopulation().size());

    assertTrue(context.currentPopulation().contains(individual1));
    assertTrue(context.currentPopulation().contains(individual2));

    try {
      context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    assertEquals(1, context.currentGeneration());

    assertEquals(2, context.currentPopulation().size());

    assertFalse(context.currentPopulation().contains(individual1));
    assertFalse(context.currentPopulation().contains(individual2));
  }

}
