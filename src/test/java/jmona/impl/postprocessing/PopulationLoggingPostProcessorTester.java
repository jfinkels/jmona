/**
 * PopulationLoggingPostProcessorTester.java
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
package jmona.impl.postprocessing;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the PopulationLoggingPostProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PopulationLoggingPostProcessorTester {

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.PopulationLoggingPostProcessor#message(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testMessage() {
    final ExampleIndividual individual1 = new ExampleIndividual();
    final ExampleIndividual individual2 = new ExampleIndividual();

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    ExampleEvolutionContext context = new ExampleEvolutionContext(population);

    final PopulationLoggingPostProcessor<ExampleIndividual, ExampleEvolutionContext> processor = new PopulationLoggingPostProcessor<ExampleIndividual, ExampleEvolutionContext>();

    final String result = processor.message(context);

    assertTrue(result.contains(individual1.toString()));
    assertTrue(result.contains(individual2.toString()));
  }

}
