/**
 * GPEvolutionContextTester.java
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
package jmona.gp.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import jmona.Factory;
import jmona.exceptions.EvolutionException;
import jmona.exceptions.FitnessException;
import jmona.exceptions.InitializationException;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.impl.selection.FitnessProportionateSelection;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPEvolutionContext class.
 * 
 * @author jfinkels
 */
public class GPEvolutionContextTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(GPEvolutionContextTester.class);
  /** The number of generations for the evolution to run. */
  public static final int NUM_GENERATIONS = 10;
  /** The number of individuals to add to the evolution context. */
  public static final int NUM_INDIVIDUALS = 100;
  /** The EvolutionContext under test. */
  private GPEvolutionContext<Integer> context = null;
  /** The tree factory for creating initial random Trees. */
  private Factory<Tree<Integer>> factory = null;
  /** The initial population for the EvolutionContext under test. */
  private List<Tree<Integer>> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new ExampleTreeFactory();
    this.population = new Vector<Tree<Integer>>();
    try {
      this.population.add(this.factory.createObject());
      this.population.add(this.factory.createObject());
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
    this.context = new GPEvolutionContext<Integer>(this.population);
    this.context.setCrossoverFunction(new GPCrossoverFunction<Integer>());
    final GPMutationFunction<Integer> mutationFunction = new GPMutationFunction<Integer>();
    mutationFunction.setTreeFactory(this.factory);
    this.context.setMutationFunction(mutationFunction);

    this.context
        .setSelectionFunction(new FitnessProportionateSelection<Tree<Integer>>());

    final Set<Object> evaluationInputs = new HashSet<Object>();
    evaluationInputs.add(new Object());

    final GPFitnessFunction<Integer, Object> fitnessFunction = new GPFitnessFunction<Integer, Object>();
    fitnessFunction.setEquivalenceTester(new EqualityTester<Integer>());
    fitnessFunction.setEvaluationInputs(evaluationInputs);
    fitnessFunction.setTarget(0);

    try {
      this.context.setFitnessFunction(fitnessFunction);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /** Test method for running a full evolution. */
  @Test
  // TODO make assertions about the population before and after the evolution
  public void testEvolution() {
    try {

      // add some extra Trees into the population
      for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
        this.context.currentPopulation().add(this.factory.createObject());
      }

      // run the evolution for multiple generations
      for (int i = 0; i < NUM_GENERATIONS; ++i) {
        this.context.stepGeneration();
      }

    } catch (final InitializationException exception) {
      Util.fail(exception);
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }
  }

  /** Test for throwing exceptions. */
  @Test
  public void testExceptions() {
    this.context.currentPopulation().clear();
    try {
      this.context.stepGeneration();
      Util.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    } catch (final RuntimeException exception) {
      assertEquals(0, this.context.currentPopulation().size());
      assertTrue(exception instanceof RuntimeException);
    }

    this.context.setCrossoverFunction(null);

    try {
      this.context.stepGeneration();
      Util.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof NullPointerException);
      assertNull(this.context.crossoverFunction());
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPEvolutionContext#GPEvolutionContext(jmona.Population)}
   * .
   */
  @Test
  public void testGPEvolutionContext() {
    try {
      new GPEvolutionContext<Integer>(new Vector<Tree<Integer>>());
      Util.shouldHaveThrownException();
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.GPEvolutionContext#stepGeneration()}.
   */
  // TODO make assertions that some individuals have changed
  @Test
  public void testStepGeneration() {

    final List<Tree<Integer>> before = this.context.currentPopulation();
    LOG.debug(before);

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    final List<Tree<Integer>> after = this.context.currentPopulation();
    LOG.debug(after);

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    final List<Tree<Integer>> after2 = this.context.currentPopulation();
    LOG.debug(after2);
  }

}
