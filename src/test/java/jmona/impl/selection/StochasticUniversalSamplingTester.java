/**
 * StochasticUniversalSamplingTester.java
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmona.SelectionException;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the StochasticUniversalSampling class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class StochasticUniversalSamplingTester {

  /**
   * Test method for
   * {@link jmona.impl.selection.StochasticUniversalSampling#select(java.util.Map, int)}
   * .
   */
  @Test
  public void testSelect() {
    final StochasticUniversalSampling<ExampleIndividual> selectionFunction = new StochasticUniversalSampling<ExampleIndividual>();
    
    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();
    
    List<ExampleIndividual> selection = null;

    try {
      selection = selectionFunction.select(fitnesses, 1);
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }
    
    assertNull(selection);
    
    ExampleIndividual individual = new ExampleIndividual(0.0);
    fitnesses.put(individual, individual.fitness());

    try {
      selection = selectionFunction.select(fitnesses, 1);
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }

    assertEquals(selection.size(), 1);
    assertSame(selection.get(0), individual);
    fail("Not yet implemented");
  }

}
