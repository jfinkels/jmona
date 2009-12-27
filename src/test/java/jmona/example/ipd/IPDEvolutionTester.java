/**
 * IPDEvolutionTester.java
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
package jmona.example.ipd;

import java.util.HashMap;
import java.util.Map;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.example.ipd.strategy.IPDStrategy;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test class for the iterated prisoner's dilemma evolution.
 * 
 * @author jfinkels
 */
@ContextConfiguration
public class IPDEvolutionTester extends AbstractJUnit4SpringContextTests {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(IPDEvolutionTester.class);

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<IPDStrategy> completionCondition = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private EvolutionContext<IPDStrategy> context = null;

  /** Test method for an iterated prisoner's dilemma evolution. */
  @Test
  @DirtiesContext
  public final void testIPDEvolution() {
    final Map<Class<? extends IPDStrategy>, Integer> results = new HashMap<Class<? extends IPDStrategy>, Integer>();

    try {
      while (!this.completionCondition.isSatisfied(this.context)) {
        this.context.stepGeneration();

        for (final IPDStrategy strategy : this.context.currentPopulation()) {
          if (results.containsKey(strategy.getClass())) {
            results.put(strategy.getClass(),
                results.get(strategy.getClass()) + 1);
          } else {
            results.put(strategy.getClass(), 1);
          }
        }

        LOG.debug(results);

        results.clear();
      }
    } catch (final CompletionException exception) {
      Util.fail(exception);
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

  }
}
