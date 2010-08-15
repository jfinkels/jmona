/**
 * ClassCountingPostProcessorTester.java
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

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.LoggingException;
import jmona.game.impl.example.ExampleStrategy;
import jmona.test.Util;
import joptsimple.internal.Strings;

import org.junit.Test;

/**
 * Test class for the ClassCountingPostProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
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
    final EvolutionContext<ExampleStrategy> context = new TwoPlayerGameEvolutionContext<ExampleStrategy>(
        population);

    // instantiate the processor with the context initialized above
    final ClassCountingPostProcessor<ExampleStrategy> processor = new ClassCountingPostProcessor<ExampleStrategy>();

    String result = Strings.EMPTY;

    try {
      result = processor.message(context);
    } catch (final LoggingException exception) {
      Util.fail(exception);
    }

    final Map<Class<ExampleStrategy>, Integer> counts = new HashMap<Class<ExampleStrategy>, Integer>();
    counts.put(ExampleStrategy.class, 2);

    assertEquals(counts.toString(), result);
  }

}
