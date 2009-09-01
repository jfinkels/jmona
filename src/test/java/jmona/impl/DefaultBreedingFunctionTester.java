/**
 * DefaultBreedingFunctionTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;

import jmona.CrossoverFunction;
import jmona.Individual;
import jmona.Pair;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultBreedingFunction class.
 * 
 * @author jfinke
 */
public class DefaultBreedingFunctionTester {
  /** The maximum difference of expected swaps and actual swaps. */
  public static final double EPSILON = 10.;
  /** The total number of pairs of individuals to test breeding on. */
  private static final int NUM_PAIRS = 100;
  /** The DefaultBreedingFunction under test. */
  private DefaultBreedingFunction<Individual> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new DefaultBreedingFunction<Individual>();
    this.function.setCrossoverFunction(new CrossoverFunction<Individual>() {
      /**
       * Swap the parents!
       * 
       * @param parents
       *          A pair of individuals.
       * @return The pair in the opposite order.
       */
      @Override
      public Pair<Individual, Individual> crossover(
          final Pair<Individual, Individual> parents) {
        return new Pair<Individual, Individual>(parents.right(), parents.left());
      }
    });
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultBreedingFunction#breed(jmona.Pair)}.
   */
  @Test
  public void testBreed() {
    // initialize a list of pairs to hold the bred children
    final List<Pair<Individual, Individual>> allPairs = new Vector<Pair<Individual, Individual>>();

    // create some dummy individuals
    final Individual leftParent = new Individual() {
      @Override
      public Individual copy() {
        return this;
      }
    };
    final Individual rightParent = new Individual() {
      @Override
      public Individual copy() {
        return this;
      }
    };

    // breed each pair of parents to produce a list of children
    for (int i = 0; i < NUM_PAIRS; ++i) {
      allPairs.add(i, this.function.breed(new Pair<Individual, Individual>(
          leftParent, rightParent)));
    }

    // determine the total number of crossed over pairs
    int actualCrossovers = 0;
    for (final Pair<Individual, Individual> child : allPairs) {
      if (child.left() == rightParent && child.right() == leftParent) {
        actualCrossovers += 1;
      }
    }

    // get the expected number of crossovers
    final double expectedCrossovers = this.function.crossoverProbability()
        * NUM_PAIRS;

    assertEquals(expectedCrossovers, actualCrossovers, EPSILON);
  }

}
