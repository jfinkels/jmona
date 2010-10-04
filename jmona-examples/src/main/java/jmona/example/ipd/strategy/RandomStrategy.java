/**
 * RandomStrategy.java
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
package jmona.example.ipd.strategy;

import jmona.DeepCopyable;
import jmona.random.RandomUtils;

/**
 * A strategy which chooses to cooperate or defect randomly.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class RandomStrategy extends IPDStrategy implements
    DeepCopyable<RandomStrategy> {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public RandomStrategy deepCopy() {
    return new RandomStrategy();
  }

  /**
   * Return either a cooperate action or a defect action, chosen randomly with
   * uniform probability.
   * 
   * @return A cooperate action or a defect action, chosen randomly with uniform
   *         probability.
   * @see jmona.example.ipd.strategy.IPDStrategy#nextAction()
   */
  @Override
  public Action nextAction() {
    Action result = null;
    if (RandomUtils.randomData().nextInt(0, 1) == 0) {
      result = Action.COOPERATE;
    } else {
      result = Action.DEFECT;
    }
    return result;
  }

}
