/**
 * DefaultEvolutionContextTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jmona.BreedingException;
import jmona.CrossoverFunction;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.Individual;
import jmona.MutationException;
import jmona.MutatorFunction;
import jmona.Pair;
import jmona.Population;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultEvolutionContext class.
 * 
 * @author jeff
 */
public class DefaultEvolutionContextTester {

  /** A bad fitness function, which throws an exception. */
  private final FitnessFunction<Individual> badFitnessFunction = new FitnessFunction<Individual>() {
    /**
     * {@inheritDoc}
     * 
     * @param individual
     *          {@inheritDoc}
     * @return {@inheritDoc}
     * @throws FitnessException
     *           {@inheritDoc}
     */
    @Override
    public double fitness(final Individual individual) throws FitnessException {
      throw new FitnessException();
    }
  };

  /** A bad mutator function, which throws an exception. */
  private final MutatorFunction<Individual> badMutatorFunction = new MutatorFunction<Individual>() {
    /**
     * {@inheritDoc}
     * 
     * @param individual
     *          {@inheritDoc}
     * @throws MutationException
     *           {@inheritDoc}
     */
    @Override
    public void mutate(final Individual individual) throws MutationException {
      throw new MutationException();
    }
  };

  /** The evolution context under test. */
  private EvolutionContext<Individual> context = null;

  /** A basic crossover function. */
  private final CrossoverFunction<Individual> crossoverFunction = new CrossoverFunction<Individual>() {
    @Override
    public Pair<Individual, Individual> crossover(
        final Pair<Individual, Individual> parents) {
      return parents;
    }
  };

  /** A basic fitness function. */
  private final FitnessFunction<Individual> goodFitnessFunction = new FitnessFunction<Individual>() {
    /**
     * {@inheritDoc}
     * 
     * @param individual
     *          {@inheritDoc}
     * @return {@inheritDoc}
     * @throws FitnessException
     *           {@inheritDoc}
     */
    @Override
    public double fitness(final Individual individual) throws FitnessException {
      return 0;
    }
  };

  /** A basic mutator function. */
  private final MutatorFunction<Individual> goodMutatorFunction = new MutatorFunction<Individual>() {
    /**
     * {@inheritDoc}
     * 
     * @param individual
     *          {@inheritDoc}
     * @throws MutationException
     *           {@inheritDoc}
     */
    @Override
    public void mutate(final Individual individual) throws MutationException {
      // intentionally unimplemented
    }
  };

  /** An example population to put into the evolution context. */
  private Population<Individual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.population = new DefaultPopulation<Individual>();

    // add two individuals to the population
    this.population.add(new ExampleIndividual());
    this.population.add(new ExampleIndividual());

    // instantiate a new evolution context
    this.context = new DefaultEvolutionContext<Individual>(population);
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultEvolutionContext#DefaultEvolutionContext(jmona.Population)}
   * .
   */
  @Test
  public void testDefaultEvolutionContext() {
    assertSame(this.population, this.context.currentPopulation());
    assertEquals(0, this.context.currentGeneration());

    this.population.remove(0);
    assertEquals(1, this.population.size());
    this.context = null;
    try {
      this.context = new DefaultEvolutionContext<Individual>(this.population);
      fail("Exception should have been thrown on the previous line");
    } catch (final IllegalArgumentException exception) {
      assertSame(null, this.context);
    }

  }

  /**
   * Test method for
   * {@link DefaultEvolutionContext#setDesiredPopulationSize(int)}.
   */
  @Test
  public void testSetDesiredPopulationSize() {
    final int desiredPopulationSize = 10;
    this.context.setDesiredPopulationSize(desiredPopulationSize);
    // TODO set all the necessary functions, an initial population, and run the
    // evolution to ensure that the desired population size is met
  }

  /** Test method for {@link DefaultEvolutionContext#stepGeneration()}. */
  @Test
  public final void testStepGeneration() {
    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // fitness function has not been set
      try {
        this.context.setFitnessFunction(this.badFitnessFunction);
      } catch (final FitnessException exception2) {
        // fitness function threw fitness exception
        try {
          this.context.setFitnessFunction(this.goodFitnessFunction);
        } catch (final FitnessException exception3) {
          exception3.printStackTrace(System.err);
          fail(exception3.getMessage());
        }
      }
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // breeding function has not been set
      this.context
          .setBreedingFunction(new DefaultBreedingFunction<Individual>());
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // mutator function has not been set
      this.context.setMutatorFunction(this.badMutatorFunction);
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // selection function has not been set
      this.context.setSelectionFunction(new MaxSelectionFunction<Individual>());
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // size of population is 1, but must be greater than 1
      this.context.currentPopulation().add(new ExampleIndividual());
      this.context.currentPopulation().add(new ExampleIndividual());
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // no crossover function has been set on the breeding function
      assertTrue(exception.getCause() instanceof BreedingException);
      this.context.breedingFunction().setCrossoverFunction(
          this.crossoverFunction);
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      // bad mutator function threw an exception
      this.context.setMutatorFunction(this.goodMutatorFunction);
      this.context.currentPopulation().add(new ExampleIndividual());
      this.context.currentPopulation().add(new ExampleIndividual());
      this.context.currentPopulation().add(new ExampleIndividual());
    }

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

  }
}
