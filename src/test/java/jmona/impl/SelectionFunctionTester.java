/**
 * SelectionFunctionTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.Individual;
import jmona.Population;
import jmona.SelectionFunction;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the OnesSelectionFunction class.
 * 
 * @author jeff
 */
public class SelectionFunctionTester {
  /**
   * A simple individual which has one property representing its fitness.
   * 
   * @author jeff
   */
  class FitIndividual implements Individual {
    /** The fitness of this individual. */
    private final double fitness;

    /**
     * Instantiate this individual with the specified initial fitness.
     * 
     * @param initialFitness
     *          The initial fitness of the individual.
     */
    public FitIndividual(final double initialFitness) {
      this.fitness = initialFitness;
    }

    /**
     * Get the fitness of this individual.
     * 
     * @return The fitness of this individual.
     */
    public double fitness() {
      return this.fitness;
    }

    /**
     * Get the String representation of the fitness of this individual.
     * 
     * @return The String representation of the fitness of this individual.
     */
    @Override
    public String toString() {
      return String.valueOf(this.fitness);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     * @see jmona.Individual#copy()
     */
    @Override
    public FitIndividual copy() {
      return new FitIndividual(this.fitness);
    }
  }

  /** The number of individuals in the population to test. */
  public static final int NUM_INDIVIDUALS = 8;

  /**
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param exception
   *          The exception which caused the test failure.
   */
  protected static void fail(final Throwable exception) {
    exception.printStackTrace(System.err);
    org.junit.Assert.fail(exception.getMessage());
  }

  /** The list of all individuals to test. */
  private List<FitIndividual> allIndividuals = null;
  /** The mapping from individual to fitness. */
  private Map<FitIndividual, Double> fitnesses = null;

  /** The fitness function for tests in this class. */
  private final FitnessFunction<FitIndividual> fitnessFunction = new FitnessFunction<FitIndividual>() {

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
    public double fitness(final FitIndividual individual)
        throws FitnessException {
      return individual.fitness();
    }
  };

  /** Establish a fixture for all tests in this class. */
  @Before
  public final void setUp() {

    // initialize a list of individuals with different fitnesses for testing
    this.allIndividuals = new Vector<FitIndividual>();
    for (int i = 0; i < NUM_INDIVIDUALS; i++) {
      this.allIndividuals.add(new FitIndividual(i));
    }

    // initialize the mapping from individual to fitness
    this.fitnesses = new HashMap<FitIndividual, Double>();

    // get the fitness of each individual
    try {
      for (final FitIndividual individual : this.allIndividuals) {
        this.fitnesses
            .put(individual, this.fitnessFunction.fitness(individual));
      }
    } catch (final FitnessException exception) {
      fail(exception);
    }

  }

  /**
   * Test method for
   * {@link jmona.impl.MaxSelectionFunction#select(java.util.Map, int)} .
   */
  @Test
  public void testSelectMax() {
    // create a minimum selection function
    final SelectionFunction<FitIndividual> selectionFunction = new MaxSelectionFunction<FitIndividual>();

    // select some individuals
    Population<FitIndividual> selectedPopulation = null;
    for (int i = NUM_INDIVIDUALS; i >= 0; --i) {

      // selected i individuals from the given fitnesses
      selectedPopulation = selectionFunction.select(fitnesses, i);
      assertEquals(i, selectedPopulation.size());

      // iterate over each individual in the selected population
      for (int j = 0; j < i; ++j) {
        // assert that the maximum individuals were chosen
        assertEquals(NUM_INDIVIDUALS - j - 1, selectedPopulation.get(j)
            .fitness(), Double.MIN_VALUE);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.MinSelectionFunction#select(java.util.Map, int)} .
   */
  @Test
  public void testSelectMin() {
    // create a minimum selection function
    final SelectionFunction<FitIndividual> selectionFunction = new MinSelectionFunction<FitIndividual>();

    // select some individuals
    Population<FitIndividual> selectedPopulation = null;
    for (int i = NUM_INDIVIDUALS; i >= 0; --i) {

      // selected i individuals from the given fitnesses
      selectedPopulation = selectionFunction.select(fitnesses, i);
      assertEquals(i, selectedPopulation.size());

      // iterate over each individual in the selected population
      for (int j = 0; j < i; ++j) {
        // assert that the minimum individuals were chosen
        assertEquals(j, selectedPopulation.get(j).fitness(), Double.MIN_VALUE);
      }
    }
  }
}
