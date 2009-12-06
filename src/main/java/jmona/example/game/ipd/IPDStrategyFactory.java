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
package jmona.example.game.ipd;

import jmona.IndividualFactory;
import jmona.InitializationException;
import jmona.example.game.ipd.strategy.CooperativeStrategy;
import jmona.example.game.ipd.strategy.IPDStrategy;
import jmona.example.game.ipd.strategy.PavlovStrategy;
import jmona.example.game.ipd.strategy.RandomStrategy;
import jmona.example.game.ipd.strategy.RuthlessStrategy;
import jmona.example.game.ipd.strategy.TitForTatStrategy;
import jmona.impl.Util;

/**
 * @author jfinkels
 */
public class IPDStrategyFactory implements IndividualFactory<IPDStrategy> {

  public static final int NUMBER_OF_STRATEGIES = 5;

  /*
   * (non-Javadoc)
   * 
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public IPDStrategy createIndividual() throws InitializationException {

    IPDStrategy result = null;

    // TODO do this in a way so that its not hardcoded
    final int choice = Util.RANDOM.nextInt(NUMBER_OF_STRATEGIES);

    switch (choice) {
    case 0:
      result = new CooperativeStrategy();
      break;
    case 1:
      result = new PavlovStrategy();
      break;
    case 2:
      result = new TitForTatStrategy();
      break;
    case 3:
      result = new RuthlessStrategy();
      break;
    case 4:
      result = new RandomStrategy();
      break;
    default:
      result = new RandomStrategy();
      break;
    }

    return result;
  }

}
