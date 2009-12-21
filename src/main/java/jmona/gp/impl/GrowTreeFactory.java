/**
 * GrowTreeFactory.java
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
package jmona.gp.impl;

import jmona.InitializationException;
import jmona.gp.Node;

/**
 * A TreeFactory which uses the "grow" method to generate a Tree, that is,
 * selecting whether to create a terminal node or a function node at each depth.
 * 
 * @param <V>
 *          The type of value to which the created Tree evaluates.
 * @author jfinkels
 */
public class GrowTreeFactory<V> extends AbstractTreeFactory<V> {

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
  protected Node<V> createTree(final int currentDepth)
      throws InitializationException {
    Node<V> result = null;

    if (currentDepth <= 1 || Math.random() < this.probabilityTerminal) {
      result = this.terminalNodeFactory().createNode();
    } else {
      // create a function node
      result = this.functionNodeFactory().createNode();

      // determine its arity (that is, the number of children it needs)
      final int arity = result.arity();

      // add child trees
      Node<V> child = null;
      for (int i = 0; i < arity; ++i) {
        child = this.createTree(currentDepth - 1);
        child.setParent(result);
        result.children().add(child);
      }
    }

    return result;
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
