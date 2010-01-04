/**
 * FitnessComparatorTester.java
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
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the FitnessComparator class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class FitnessComparatorTester {

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessComparator#setFitnessFunction(java.util.Map)}.
   */
  @Test
  public void testSetFitnesses() {
    final FitnessComparator<ExampleIndividual> comparator = new FitnessComparator<ExampleIndividual>();
    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();

    final ExampleIndividual individual1 = new ExampleIndividual(1);
    final ExampleIndividual individual2 = new ExampleIndividual(0);
    final ExampleIndividual individual3 = new ExampleIndividual(2);

    fitnesses.put(individual1, individual1.fitness());
    fitnesses.put(individual2, individual2.fitness());
    fitnesses.put(individual3, individual3.fitness());

    comparator.setFitnessFunction(new ExampleFitnessFunction());

    assertEquals(0, comparator.compare(individual1, individual1));
    assertTrue(comparator.compare(individual1, individual2) > 0);
    assertTrue(comparator.compare(individual1, individual3) < 0);

    assertTrue(comparator.compare(individual2, individual1) < 0);
    assertEquals(0, comparator.compare(individual2, individual2));
    assertTrue(comparator.compare(individual2, individual3) < 0);

    assertTrue(comparator.compare(individual3, individual1) > 0);
    assertTrue(comparator.compare(individual3, individual2) > 0);
    assertEquals(0, comparator.compare(individual3, individual3));

  }

}
