/**
 * FitnessComparatorTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import jmona.Individual;
import jmona.Population;

import org.junit.Test;

/**
 * Test class for the FitnessComparator class.
 * 
 * @author jeff
 */
public class FitnessComparatorTester {

  /** The number of individuals in the population to test. */
  public static final int NUM_INDIVIDUALS = 2;

  /**
   * Test method for
   * {@link jmona.impl.FitnessComparator#compare(jmona.Individual, jmona.Individual)}
   * .
   */
  @Test
  public void testCompare() {
    final Population<Individual> allIndividuals = new DefaultPopulation<Individual>();
    final Map<Individual, Double> fitnesses = new HashMap<Individual, Double>();
    Individual individual = null;
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      individual = new Individual() {
        @Override
        public Individual copy() {
          return this;
        }
      };
      allIndividuals.add(individual);
      fitnesses.put(individual, (double) i);
    }

    final FitnessComparator<Individual> comparator = new FitnessComparator<Individual>();
    comparator.setFitnesses(fitnesses);
    assertTrue(comparator.compare(allIndividuals.get(0), allIndividuals.get(1)) < 0);
    assertTrue(comparator.compare(allIndividuals.get(1), allIndividuals.get(0)) > 0);
    assertEquals(0, comparator.compare(allIndividuals.get(0), allIndividuals
        .get(0)));
    assertEquals(0, comparator.compare(allIndividuals.get(1), allIndividuals
        .get(1)));
  }

  /** Test for iterating over a navigable map. */
  @Test
  public final void testNavigableMap() {
    final Population<Individual> population = new DefaultPopulation<Individual>();
    final Map<Individual, Double> fitnesses = new HashMap<Individual, Double>();

    Individual individual = null;
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      individual = new Individual() {
        @Override
        public Individual copy() {
          return this;
        }
      };
      population.add(individual);
      fitnesses.put(individual, (double) i);
    }

    final FitnessComparator<Individual> comparator = new FitnessComparator<Individual>();
    comparator.setFitnesses(fitnesses);

    final NavigableSet<Individual> set = new TreeSet<Individual>(comparator);

    for (final Individual i : population) {
      set.add(i);
    }

    Iterator<Individual> iterator = set.descendingIterator();
    int i = NUM_INDIVIDUALS - 1;
    while (iterator.hasNext()) {
      individual = iterator.next();
      assertEquals((double) i, fitnesses.get(individual).doubleValue(),
          Double.MIN_VALUE);
      i -= 1;
    }

    iterator = set.iterator();
    i = 0;
    while (iterator.hasNext()) {
      individual = iterator.next();
      assertEquals((double) i, fitnesses.get(individual).doubleValue(),
          Double.MIN_VALUE);
      i += 1;
    }
  }

}
