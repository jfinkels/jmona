/**
 * IPDStrategyFactory.java
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

import java.util.HashSet;
import java.util.Set;

import jmona.Factory;
import jmona.InitializationException;
import jmona.example.ipd.strategy.CooperativeStrategy;
import jmona.example.ipd.strategy.IPDStrategy;
import jmona.example.ipd.strategy.PavlovStrategy;
import jmona.example.ipd.strategy.RandomStrategy;
import jmona.example.ipd.strategy.RuthlessStrategy;
import jmona.example.ipd.strategy.TitForTatStrategy;
import jmona.random.RandomUtils;

/**
 * A factory which creates IPDStrategy objects at random.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class IPDStrategyFactory implements Factory<IPDStrategy> {

  /**
   * Each of the possible strategies from which to choose when creating a new
   * strategy.
   */
  private static enum Strategy {
    /** The cooperative strategy. */
    COOPERATIVE,
    /** The Pavlov strategy. */
    PAVLOV,
    /** The random strategy. */
    RANDOM,
    /** The ruthless strategy. */
    RUTHLESS,
    /** The tit-for-tat strategy. */
    TIT_FOR_TAT
  }

  /** The Set of Classes from which an IPDStrategy will be instantiated. */
  private final Set<Strategy> strategies = new HashSet<Strategy>();

  /**
   * Instantiate this factory by initializing the set of all possible strategies
   * to contain all possible strategies.
   */
  public IPDStrategyFactory() {
    this.strategies.add(Strategy.COOPERATIVE);
    this.strategies.add(Strategy.PAVLOV);
    this.strategies.add(Strategy.RANDOM);
    this.strategies.add(Strategy.RUTHLESS);
    this.strategies.add(Strategy.TIT_FOR_TAT);
  }

  /**
   * Create a Strategy chosen randomly from all IPDStrategy subclasses.
   * 
   * @return A Strategy chosen randomly from all IPDStrategy subclasses.
   * @throws InitializationException
   *           If a Strategy cannot be created given only its class.
   * @see jmona.Factory#createObject()
   */
  @Override
  public IPDStrategy createObject() throws InitializationException {
    IPDStrategy result = null;

    switch (RandomUtils.choice(this.strategies)) {
    case COOPERATIVE:
      result = new CooperativeStrategy();
      break;
    case PAVLOV:
      result = new PavlovStrategy();
      break;
    case RANDOM:
    default:
      result = new RandomStrategy();
      break;
    case RUTHLESS:
      result = new RuthlessStrategy();
      break;
    case TIT_FOR_TAT:
      result = new TitForTatStrategy();
      break;
    }

    return result;
  }
}
