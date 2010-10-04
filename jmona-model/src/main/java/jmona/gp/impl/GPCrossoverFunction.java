/**
 * GPCrossoverFunction.java
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
package jmona.gp.impl;

import jmona.CrossoverFunction;
import jmona.gp.Tree;

/**
 * Perform crossover of random subtrees, chosen with uniform distribution over
 * each Node in a Tree.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GPCrossoverFunction implements CrossoverFunction<Tree> {

  /**
   * Perform a swap of random subtrees from the specified pair of parent Trees
   * to yield two new Trees.
   * 
   * @param parent1
   *          A Tree.
   * @param parent2
   *          Another Tree.
   * @see jmona.CrossoverFunction#crossover(Object, Object)
   */
  @Override
  public void crossover(final Tree parent1, final Tree parent2) {
    TreeUtils.swapNodes(parent1, parent1.randomNode(), parent2, parent2
        .randomNode());
  }

}
