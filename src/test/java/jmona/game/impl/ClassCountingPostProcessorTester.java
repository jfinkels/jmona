/**
 * ClassCountingPostProcessorTester.java
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
package jmona.game.impl;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.game.impl.example.ExampleStrategy;

import org.junit.Test;

/**
 * Test class for the ClassCountingPostProcessor class.
 * 
 * @author Jeffrey Finkelstein
 */
public class ClassCountingPostProcessorTester {

  /**
   * Test method for
   * {@link jmona.game.impl.ClassCountingPostProcessor#processAtInterval(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testProcessAtInterval() {

    // initialize a new population
    final List<ExampleStrategy> population = new Vector<ExampleStrategy>();

    // add two ExampleStrategy objects to the population
    population.add(new ExampleStrategy());
    population.add(new ExampleStrategy());

    // instantiate a context with the population initialized above
    final EvolutionContext<ExampleStrategy> context = new GameEvolutionContext<ExampleStrategy>(
        population);

    // instantiate the processor with the context initialized above
    final ClassCountingPostProcessor<ExampleStrategy> processor = new ClassCountingPostProcessor<ExampleStrategy>();

    // process the context
    processor.processAtInterval(context);

    // TODO assert that the output string showed exactly two ExampleStrategys
  }

}
