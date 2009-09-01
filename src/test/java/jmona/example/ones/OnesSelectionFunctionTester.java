/**
 * OnesSelectionFunctionTester.java
 */
package jmona.example.ones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Test;

/**
 * Test class for the OnesSelectionFunction class.
 * 
 * @author jeff
 */
public class OnesSelectionFunctionTester {
  /** The number of individuals in the population to test. */
  public static final int NUM_INDIVIDUALS = 8;

  /**
   * Test method for
   * {@link jmona.example.ones.OnesSelectionFunction#select(java.util.Map, int)}
   * .
   */
  @Test
  public void testSelect() {
    final List<OnesIndividual> allIndividuals = new Vector<OnesIndividual>();

    short[] array;
    for (int i = 0; i < NUM_INDIVIDUALS; i++) {
      array = new short[] { (short) (i & (1 << 2)), (short) (i & (1 << 1)),
          (short) (i & (1 << 0)) };
      allIndividuals.add(new OnesIndividual(array));
    }

    final Map<OnesIndividual, Double> fitnesses = new HashMap<OnesIndividual, Double>();
    final OnesFitnessFunction fitnessFunction = new OnesFitnessFunction();

    for (final OnesIndividual individual : allIndividuals) {
      fitnesses.put(individual, fitnessFunction.fitness(individual));
    }

    final OnesSelectionFunction selectionFunction = new OnesSelectionFunction();

    for (final OnesIndividual individual : selectionFunction.select(fitnesses,
        NUM_INDIVIDUALS)) {
      assertTrue(allIndividuals.contains(individual));
    }

    for (final OnesIndividual individual : allIndividuals) {
      assertTrue(selectionFunction.select(fitnesses, NUM_INDIVIDUALS).contains(
          individual));
    }

    for (int i = NUM_INDIVIDUALS; i >= 0; --i) {
      assertEquals(i, selectionFunction.select(fitnesses, i).size());
    }

  }

}
