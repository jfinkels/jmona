/**
 * GrowTreeFactory.java
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

import jmona.InitializationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.random.RandomUtils;

/**
 * A TreeFactory which uses the "grow" method to generate a Tree, that is,
 * selecting whether to create a terminal node or a function node at each depth.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GrowTreeFactory extends BaseDefaultTreeFactory {

  /** The default probability of creating a terminal node within the Tree. */
  public static final double DEFAULT_PROBABILITY_TERMINAL = 0.1;
  /** The probability of creating a terminal node within the Tree. */
  private double probabilityTerminal = DEFAULT_PROBABILITY_TERMINAL;

  /**
   * Create a Tree using the "grow" method.
   * 
   * @param currentDepth
   *          The current depth in the Tree.
   * @return A Node with branches no deeper than the specified depth.
   * @throws InitializationException
   *           {@inheritDoc}
   * @see jmona.gp.impl.AbstractTreeFactory#createTree(int)
   */
  // TODO more thorough documentation for this method
  @Override
  protected Node createTree(final int currentDepth)
      throws InitializationException {

    if (currentDepth <= 1
        || RandomUtils.nextDouble() < this.probabilityTerminal) {
      return this.terminalNodeFactory().createObject();
    } else {
      // create a function node
      final FunctionNode result = this.functionNodeFactory().createObject();

      // add child trees
      Node child = null;
      final int arity = result.arity();
      for (int i = 0; i < arity; ++i) {
        child = this.createTree(currentDepth - 1);

        TreeUtils.attachChildToParent(result, child);
      }

      // return the function node
      return result;
    }

  }

  /**
   * Set the probability of creating a terminal node within the Tree.
   * 
   * @param newProbabilityTerminal
   *          The probability of creating a terminal node within the Tree.
   */
  public void setProbabilityTerminal(final double newProbabilityTerminal) {
    this.probabilityTerminal = newProbabilityTerminal;
  }

}
