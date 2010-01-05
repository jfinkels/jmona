/**
 * UniformPopulationCompletionConditionTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.game.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the UniformPopulationCompletionCondition class.
 * 
 * @author Jeffrey Finkelstein
 */
public class UniformPopulationCompletionConditionTester {

  /**
   * Test method for
   * {@link jmona.game.impl.UniformPopulationCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testIsSatisfied() {
    final UniformPopulationCompletionCondition<ExampleIndividual> criteria = new UniformPopulationCompletionCondition<ExampleIndividual>();

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(new ExampleIndividual());
    population.add(new ExampleIndividual());

    final ExampleEvolutionContext context = new ExampleEvolutionContext(
        population);

    assertTrue(criteria.isSatisfied(context));
    population.add(new ExampleIndividual() {
    });
    assertFalse(criteria.isSatisfied(context));

  }
}
