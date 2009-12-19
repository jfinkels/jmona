/**
 * ClassCountingPostProcessor.java
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

import java.util.HashMap;
import java.util.Map;

import jmona.EvolutionContext;
import jmona.game.Strategy;
import jmona.impl.PeriodicPostProcessor;

import org.apache.log4j.Logger;

/**
 * Count the number of each subclass of Strategy and output the result.
 * 
 * @param <S>
 *          The type of Strategy to count.
 * @author jfinkels
 */
public class ClassCountingPostProcessor<S extends Strategy> extends
    PeriodicPostProcessor<S> {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(ClassCountingPostProcessor.class);

  /**
   * Count the number of each subclass of Strategy and output the result.
   * 
   * @param context
   *          The context from which to get the population of Strategy objects
   *          to count.
   * @see jmona.impl.PeriodicPostProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(final EvolutionContext<S> context) {
    final Map<Class<S>, Integer> results = new HashMap<Class<S>, Integer>();

    Class<S> clazz = null;
    for (final S strategy : context.currentPopulation()) {
      clazz = (Class<S>) strategy.getClass();

      if (results.containsKey(clazz)) {
        results.put(clazz, results.get(clazz) + 1);
      } else {
        results.put(clazz, 1);
      }
    }

    LOG.info(results);
  }

}
