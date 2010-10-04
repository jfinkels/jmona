/**
 * ValueComparatorTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.impl.selection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import jmona.ComparisonException;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the ValueComparator class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ValueComparatorTester {

  /**
   * Test method for
   * {@link jmona.impl.selection.ValueComparator#ValueComparator(java.util.Map)}
   * .
   */
  @Test
  public void testValueComparator() {
    final ExampleIndividual individual1 = new ExampleIndividual(0);
    final ExampleIndividual individual2 = new ExampleIndividual(1);

    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();
    fitnesses.put(individual1, individual1.fitness());
    fitnesses.put(individual2, individual2.fitness());

    final ValueComparator<ExampleIndividual, Double> comparator = new ValueComparator<ExampleIndividual, Double>(
        fitnesses);

    assertEquals(0, comparator.compare(individual1, individual1));
    assertEquals(0, comparator.compare(individual2, individual2));
    assertTrue(comparator.compare(individual1, individual2) < 0);
    assertTrue(comparator.compare(individual2, individual1) > 0);

    final ExampleIndividual newIndividual = new ExampleIndividual();
    try {
      comparator.compare(newIndividual, individual1);
    } catch (final ComparisonException exception) {
      assertFalse(fitnesses.containsKey(newIndividual));
    }
  }

}
