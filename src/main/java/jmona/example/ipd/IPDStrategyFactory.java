/**
 * IPDStrategyFactory.java
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

import java.util.Set;

import jmona.IndividualFactory;
import jmona.InitializationException;
import jmona.example.ipd.strategy.IPDStrategy;
import jmona.impl.Util;

/**
 * A factory which creates IPDStrategy objects at random.
 * 
 * @author jfinkels
 */
public class IPDStrategyFactory implements IndividualFactory<IPDStrategy> {

  /** The number of strategies from which to choose. */
  public static final int NUMBER_OF_STRATEGIES = 5;
  /** The Set of Classes from which an IPDStrategy will be instantiated. */
  private Set<Class<? extends IPDStrategy>> strategyClasses = null;

  /**
   * Create a Strategy chosen randomly from all IPDStrategy subclasses.
   * 
   * @return A Strategy chosen randomly from all IPDStrategy subclasses.
   * @throws InitializationException
   *           If a Strategy cannot be created given only its class.
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public IPDStrategy createIndividual() throws InitializationException {

    final Class<? extends IPDStrategy> resultClass = Util
        .randomFromCollection(this.strategyClasses);

    try {
      return resultClass.newInstance();
    } catch (final InstantiationException exception) {
      throw new InitializationException("Failed to create a Strategy.",
          exception);
    } catch (final IllegalAccessException exception) {
      throw new InitializationException("Failed to create a Strategy.",
          exception);
    }
  }

  /**
   * Set the Set of Classes from which an IPDStrategy will be instantiated.
   * 
   * @param newStrategyClasses
   *          The Set of Classes from which an IPDStrategy will be instantiated.
   */
  public void setStrategyClasses(
      final Set<Class<? extends IPDStrategy>> newStrategyClasses) {
    this.strategyClasses = newStrategyClasses;
  }

}
