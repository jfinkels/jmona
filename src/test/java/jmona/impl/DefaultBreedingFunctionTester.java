/**
 * DefaultBreedingFunctionTester.java
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
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Vector;

import jmona.BreedingException;
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
  public static final double EPSILON = 15.;
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

  public static final int NUM_TESTS = 100;

  /**
   * Test method for
   * {@link jmona.impl.DefaultBreedingFunction#breed(jmona.Pair)}.
   */
  @Test
  public void testBreed() {
    // initialize a list of pairs to hold the bred children
    final List<Pair<Individual, Individual>> allPairs = new Vector<Pair<Individual, Individual>>();

    double sum = 0;
    for (int j = 0; j < NUM_TESTS; ++j) {
      // create some dummy individuals
      final Individual leftParent = new ExampleIndividual();
      final Individual rightParent = new ExampleIndividual();

      // breed each pair of parents to produce a list of children
      for (int i = 0; i < NUM_PAIRS; ++i) {
        try {
          allPairs.add(i, this.function.breed(new Pair<Individual, Individual>(
              leftParent, rightParent)));
        } catch (final BreedingException exception) {
          exception.printStackTrace(System.err);
          fail(exception.getMessage());
        }
      }

      // determine the total number of crossed over pairs
      int actualCrossovers = 0;
      for (final Pair<Individual, Individual> child : allPairs) {
        if (child.left() == rightParent && child.right() == leftParent) {
          actualCrossovers += 1;
        }
      }

      sum += actualCrossovers;
    }

    final double averageCrossovers = sum / (double) NUM_TESTS;

    // get the expected number of crossovers
    final double expectedCrossovers = this.function.crossoverProbability()
        * NUM_PAIRS;

    assertEquals(expectedCrossovers, averageCrossovers, EPSILON);
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultBreedingFunction#setCrossoverProbability(double)}.
   */
  @Test
  public void testSetCrossoverProbability() {
    final double newProb = 0.4;
    this.function.setCrossoverProbability(newProb);
    assertEquals(newProb, this.function.crossoverProbability(), EPSILON);
  }

}
