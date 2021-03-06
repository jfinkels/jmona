/**
 * GPEvolutionContextTester.java
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
package jmona.gp.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.CrossoverException;
import jmona.CrossoverFunction;
import jmona.EvolutionException;
import jmona.Factory;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.InitializationException;
import jmona.MutationException;
import jmona.MutationFunction;
import jmona.PropertyNotSetException;
import jmona.SelectionException;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleGPFitnessFunction;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.impl.selection.FitnessProportionateSelection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
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
  private GPEvolutionContext context = null;
  /** The tree factory for creating initial random Trees. */
  private Factory<Tree> factory = null;
  /** The initial population for the EvolutionContext under test. */
  private List<Tree> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new ExampleTreeFactory();
    this.population = new Vector<Tree>();
    try {
      this.population.add(this.factory.createObject());
      this.population.add(this.factory.createObject());
    } catch (final InitializationException exception) {
      TestUtils.fail(exception);
    }
    this.context = new GPEvolutionContext(this.population);
    this.context.setCrossoverFunction(new GPCrossoverFunction());
    final GPMutationFunction mutationFunction = new GPMutationFunction();
    mutationFunction.setTreeFactory(this.factory);
    this.context.setMutationFunction(mutationFunction);

    this.context
        .setSelectionFunction(new FitnessProportionateSelection<Tree>());

    final Set<Object> evaluationInputs = new HashSet<Object>();
    evaluationInputs.add(new Object());

    final FitnessFunction<Tree> fitnessFunction = new ExampleGPFitnessFunction();
    try {
      this.context.setFitnessFunction(fitnessFunction);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
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
      TestUtils.fail(exception);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }

  /** Test for throwing exceptions. */
  @Test
  public void testExceptions() {

    this.context.setCrossoverFunction(null);

    try {
      this.context.stepGeneration();
      TestUtils.shouldHaveThrownException();
    } catch (final PropertyNotSetException exception) {
      assertTrue(exception instanceof PropertyNotSetException);
      assertNull(this.context.crossoverFunction());
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.GPEvolutionContext#stepGeneration()}.
   */
  // TODO make assertions that some individuals have changed
  @Test
  public void testStepGeneration() {

    final List<Tree> before = this.context.currentPopulation();
    LOG.debug(before);

    try {
      this.context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    final List<Tree> after = this.context.currentPopulation();
    LOG.debug(after);

    try {
      this.context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    final List<Tree> after2 = this.context.currentPopulation();
    LOG.debug(after2);

    this.context.setCrossoverFunction(new CrossoverFunction<Tree>() {
      @Override
      public void crossover(final Tree individual1, final Tree individual2)
          throws CrossoverException {
        throw new CrossoverException();
      }
    });

    this.context.setMutationProbability(0);

    try {
      this.context.executeGenerationStep();
      TestUtils.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof CrossoverException);
      this.context.setCrossoverFunction(new GPCrossoverFunction());
    }

    this.context.setMutationFunction(new MutationFunction<Tree>() {
      @Override
      public void mutate(final Tree object) throws MutationException {
        throw new MutationException();
      }
    });

    this.context.setMutationProbability(1);
    try {
      this.context.executeGenerationStep();
      TestUtils.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof MutationException);
      this.context.setMutationFunction(new GPMutationFunction());
    }

    this.context.setSelectionFunction(new IndependentSelectionFunction<Tree>() {
      @Override
      public Tree select(final Map<Tree, Double> fitnesses)
          throws SelectionException {
        throw new SelectionException();
      }
    });

    try {
      this.context.executeGenerationStep();
      TestUtils.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof SelectionException);
      this.context
          .setSelectionFunction(new FitnessProportionateSelection<Tree>());
    }

  }

}
