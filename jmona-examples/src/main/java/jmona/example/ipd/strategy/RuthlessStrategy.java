/**
 * RuthlessStrategy.java
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

/**
 * A strategy which always defects.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class RuthlessStrategy extends IPDStrategy implements
    DeepCopyable<RuthlessStrategy> {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public RuthlessStrategy deepCopy() {
    return new RuthlessStrategy();
  }

  /**
   * Always defect.
   * 
   * @return A defect Action.
   * @see jmona.example.ipd.strategy.IPDStrategy#nextAction()
   */
  @Override
  public Action nextAction() {
    return Action.DEFECT;
  }

}
