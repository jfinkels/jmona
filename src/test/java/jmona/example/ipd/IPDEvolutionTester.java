/**
 * IPDEvolutionTester.java
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
package jmona.example.ipd;

import java.util.HashMap;
import java.util.Map;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.DeepCopyable;
import jmona.EvolutionException;
import jmona.PopulationEvolutionContext;
import jmona.game.Strategy;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for the iterated prisoner's dilemma evolution.
 * 
 * @param <S>
 *          The type of Strategy in the evolution.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class IPDEvolutionTester<S extends DeepCopyable<S> & Strategy> {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(IPDEvolutionTester.class);

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<S> completionCondition = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private PopulationEvolutionContext<S> context = null;

  /** Test method for an iterated prisoner's dilemma evolution. */
  @SuppressWarnings("unchecked")
  @Test
  @DirtiesContext
  public final void testIPDEvolution() {
    final Map<Class<S>, Integer> results = new HashMap<Class<S>, Integer>();

    try {
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();

        for (final S strategy : this.context.currentPopulation()) {
          if (results.containsKey(strategy.getClass())) {
            results.put((Class<S>) strategy.getClass(), results.get(strategy
                .getClass()) + 1);
          } else {
            results.put((Class<S>) strategy.getClass(), 1);
          }
        }

        LOG.debug(results);

        results.clear();
      }
    } catch (final CompletionException exception) {
      TestUtils.fail(exception);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

  }
}
