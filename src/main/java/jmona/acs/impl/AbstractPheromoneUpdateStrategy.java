/**
 * AbstractPheromoneUpdateStrategy.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.acs.impl;

import jmona.acs.PheromoneUpdateStrategy;

/**
 * A base class for PheromoneUpdateStrategy classes.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public abstract class AbstractPheromoneUpdateStrategy implements
    PheromoneUpdateStrategy {

  /** The default pheromone quantity index. */
  public static final int DEFAULT_TRAIL_QUANTITY_INDEX = 100;
  /**
   * The constant which is returned when no pheromone is to be added to an edge.
   */
  public static final double NO_PHEROMONE = 0;
  /** The pheromone quantity index. */
  private int pheromoneQuantityIndex = DEFAULT_TRAIL_QUANTITY_INDEX;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.acs.PheromoneUpdateStrategy#pheromoneQuantityIndex()
   */
  @Override
  public int pheromoneQuantityIndex() {
    return this.pheromoneQuantityIndex;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newPheromoneQuantityIndex
   *          {@inheritDoc}
   * @see jmona.acs.PheromoneUpdateStrategy#setPheromoneQuantityIndex(int)
   */
  @Override
  public void setPheromoneQuantityIndex(final int newPheromoneQuantityIndex) {
    this.pheromoneQuantityIndex = newPheromoneQuantityIndex;
  }

}
