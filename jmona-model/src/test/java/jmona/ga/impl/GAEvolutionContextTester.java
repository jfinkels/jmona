/**
 * GAEvolutionContextTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.CrossoverException;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.MutationException;
import jmona.PropertyNotSetException;
import jmona.SelectionException;
import jmona.impl.example.ExampleBadCrossoverFunction;
import jmona.impl.example.ExampleBadFitnessFunction;
import jmona.impl.example.ExampleBadMutationFunction;
import jmona.impl.example.ExampleBadSelectionFunction;
import jmona.impl.example.ExampleCrossoverFunction;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;
import jmona.impl.selection.FitnessProportionateSelection;

import org.junit.Test;

/**
 * Test class for the GAEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GAEvolutionContextTester {

  /** Test for throwing exceptions during the evolution. */
  @Test
  public void testExceptions() {
    // create three individuals to put into a population
    final ExampleIndividual individual1 = new ExampleIndividual();
    final ExampleIndividual individual2 = new ExampleIndividual();
    final ExampleIndividual individual3 = new ExampleIndividual();

    // put those three individuals in a population
    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);
    population.add(individual3);

    // instantiate a new EvolutionContext
    final GAEvolutionContext<ExampleIndividual> context = new GAEvolutionContext<ExampleIndividual>(
        population);

    // set the necessary functions on the context
    context.setCrossoverFunction(new ExampleBadCrossoverFunction());
    context.setMutationFunction(new ExampleBadMutationFunction());
    context.setSelectionFunction(new ExampleBadSelectionFunction());
    try {
      context.setFitnessFunction(new ExampleBadFitnessFunction());
      TestUtils.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      try {
        context.setFitnessFunction(new ExampleFitnessFunction());
      } catch (final FitnessException exception2) {
        TestUtils.fail(exception2);
      }
    }

    context.setMutationProbability(1);
    context.setCrossoverProbability(1);

    try {
      context.stepGeneration();
      TestUtils.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      // bad selection function
      assertTrue(exception.getCause() instanceof SelectionException);
      context
          .setSelectionFunction(new FitnessProportionateSelection<ExampleIndividual>());
    }
    
    try {
      context.stepGeneration();
      TestUtils.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      // bad crossover function
      assertTrue(exception.getCause() instanceof CrossoverException);
      context.setCrossoverFunction(new ExampleCrossoverFunction());
    }

    try {
      context.stepGeneration();
      TestUtils.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      // bad mutation function
      assertTrue(exception.getCause() instanceof MutationException);
      context.setMutationFunction(new ExampleMutationFunction());
    }

    try {
      context.stepGeneration();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.GAEvolutionContext#executeGenerationStep()}.
   */
  @Test
  public void testStepGeneration() {
    // create three individuals to put into a population
    final ExampleIndividual individual1 = new ExampleIndividual();
    final ExampleIndividual individual2 = new ExampleIndividual();
    final ExampleIndividual individual3 = new ExampleIndividual();

    // put those three individuals in a population
    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);
    population.add(individual3);

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
      TestUtils.fail(exception);
    }

    assertEquals(0, context.currentGeneration());

    assertEquals(population.size(), context.currentPopulation().size());

    assertTrue(context.currentPopulation().contains(individual1));
    assertTrue(context.currentPopulation().contains(individual2));

    try {
      context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(population.size(), context.currentPopulation().size());

    assertFalse(context.currentPopulation().contains(individual1));
    assertFalse(context.currentPopulation().contains(individual2));
    assertFalse(context.currentPopulation().contains(individual3));

    context.currentPopulation().remove(0);
    try {
      context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(population.size() - 1, context.currentPopulation().size());

    context.setMutationFunction(null);
    try {
      context.executeGenerationStep();
      TestUtils.shouldHaveThrownException();
    } catch (final PropertyNotSetException exception) {
      assertTrue(exception instanceof PropertyNotSetException);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

  }
}
