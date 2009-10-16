/**
 * DefaultEvolutionContextTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jmona.BreedingFunction;
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

  /**
   * A basic implementation of an Individual.
   * 
   * @author jeff
   */
  class ExampleIndividual implements Individual {
    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    public Individual copy() {
      return this;
    }
  }

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

  private final CrossoverFunction<Individual> crossoverFunction = new CrossoverFunction<Individual>() {
    @Override
    public Pair<Individual, Individual> crossover(
        final Pair<Individual, Individual> parents) {
      return parents;
    }
  };
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

  /** Test method for {@link DefaultEvolutionContext#stepGeneration()}. */
  @Test
  public final void testStepGeneration() {

    // no functions have been set
    try {
      this.context.stepGeneration();
      fail("Exception should have been thrown on the previous line.");
    } catch (final EvolutionException exception) {
      try {
        this.context.setFitnessFunction(this.badFitnessFunction);
        fail("Exception should have been thrown on the previous line.");
      } catch (final FitnessException fitnessException) {
        try {
          this.context.setFitnessFunction(this.goodFitnessFunction);
        } catch (final FitnessException fitnessException2) {
          fitnessException2.printStackTrace(System.err);
          fail(fitnessException2.getMessage());
        }
      }
    }

    // at this point, a fitness function has been set
    try {
      this.context.stepGeneration();
      fail("Exception should have been thrown on the previous line.");
    } catch (final EvolutionException exception) {
      final BreedingFunction<Individual> breedingFunction = new DefaultBreedingFunction<Individual>();
      breedingFunction.setCrossoverFunction(this.crossoverFunction);
      this.context.setBreedingFunction(breedingFunction);
    }

    // at this point, a breeding function has been set
    try {
      this.context.stepGeneration();
      fail("Exception should have been thrown on the previous line.");
    } catch (final EvolutionException exception) {
      this.context.setSelectionFunction(new MaxSelectionFunction<Individual>());
    }

    // at this point, a selection function has been set
    try {
      this.context.stepGeneration();
      fail("Exception should have been thrown on the previous line.");
    } catch (final EvolutionException exception) {
      this.context.setMutatorFunction(this.badMutatorFunction);
    }

    // at this point a bad mutator function has been set, and the population is
    // too small
    try {
      this.context.stepGeneration();
      fail("Exception should have been thrown on the previous line.");
    } catch (final EvolutionException exception) {
      assertTrue(this.context.currentPopulation().size() < 2);
      this.context.currentPopulation().add(new ExampleIndividual());
      this.context.currentPopulation().add(new ExampleIndividual());
    }

    // at this point the population is large enough
    try {
      this.context.stepGeneration();
      fail("Exception should have been thrown on the previous line.");
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof MutationException);
      // need to replenish the population because the mutation exception caused
      // a decrement
      this.context.currentPopulation().add(new ExampleIndividual());
      this.context.setMutatorFunction(this.goodMutatorFunction);
    }

    // at this point a good mutator function has been set
    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

  }
}
