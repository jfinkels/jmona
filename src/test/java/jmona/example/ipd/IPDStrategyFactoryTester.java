/**
 * IPDStrategyFactoryTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jmona.example.ipd.IPDStrategyFactory;
import jmona.example.ipd.strategy.CooperativeStrategy;
import jmona.example.ipd.strategy.IPDStrategy;
import jmona.example.ipd.strategy.PavlovStrategy;
import jmona.example.ipd.strategy.RandomStrategy;
import jmona.example.ipd.strategy.RuthlessStrategy;
import jmona.example.ipd.strategy.TitForTatStrategy;
import jmona.exceptions.InitializationException;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the IPDStrategyFactory class.
 * 
 * @author jfinkels
 */
public class IPDStrategyFactoryTester {

  /** Number of tests to run. */
  public static final int NUM_TESTS = 10000;

  /**
   * Test method for
   * {@link jmona.example.ipd.IPDStrategyFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {
    final IPDStrategyFactory factory = new IPDStrategyFactory();
    final Set<Class<? extends IPDStrategy>> newStrategyClasses = new HashSet<Class<? extends IPDStrategy>>();
    newStrategyClasses.add(TitForTatStrategy.class);

    factory.setStrategyClasses(newStrategyClasses);
    IPDStrategy strategy = null;
    try {
      for (int i = 0; i < NUM_TESTS; ++i) {
        strategy = factory.createObject();
        assertTrue(strategy instanceof TitForTatStrategy);
      }
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    newStrategyClasses.add(PavlovStrategy.class);
    newStrategyClasses.add(RuthlessStrategy.class);
    newStrategyClasses.add(CooperativeStrategy.class);
    newStrategyClasses.add(RandomStrategy.class);

    final Map<Class<? extends IPDStrategy>, Integer> numSelections = new HashMap<Class<? extends IPDStrategy>, Integer>();

    try {
      for (int i = 0; i < NUM_TESTS; ++i) {
        strategy = factory.createObject();
        if (numSelections.containsKey(strategy.getClass())) {
          numSelections.put(strategy.getClass(), numSelections.get(strategy
              .getClass()) + 1);
        } else {
          numSelections.put(strategy.getClass(), 1);
        }
      }
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    final int expectedSelections = NUM_TESTS / newStrategyClasses.size();
    final double delta = expectedSelections * 0.10;

    for (final Integer actualSelections : numSelections.values()) {
      assertEquals(expectedSelections, actualSelections, delta);
    }
  }

}
