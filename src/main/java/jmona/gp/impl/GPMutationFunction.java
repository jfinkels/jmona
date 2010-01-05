/**
 * GPMutationFunction.java
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

import jmona.Factory;
import jmona.InitializationException;
import jmona.MutationException;
import jmona.MutationFunction;
import jmona.gp.Tree;

/**
 * A MutationFunction which replaces a random Node in a Tree with a randomly
 * generated subtree.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GPMutationFunction implements MutationFunction<Tree> {

  /** The TreeFactory which will be used by the {@link #mutate(Tree)} method. */
  private Factory<Tree> treeFactory = null;

  /**
   * Replace a random Node from the specified Tree with a randomly generated
   * subtree.
   * 
   * @param individual
   *          The Tree in which to replace a random Node with a randomly
   *          generated subtree.
   * @throws MutationException
   *           If there is a problem generating a random subtree.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final Tree individual) throws MutationException {
    if (this.treeFactory == null) {
      throw new MutationException("TreeFactory has not been set.");
    }

    try {
      final Tree newTree = this.treeFactory.createObject();
      TreeUtils
          .replaceNode(individual, individual.randomNode(), newTree.root());
    } catch (final InitializationException exception) {
      throw new MutationException("Failed to generate a random subtree.",
          exception);
    }
  }

  /**
   * Set the TreeFactory which will be used by the {@link #mutate(Tree)} method.
   * 
   * @param newTreeFactory
   *          The TreeFactory which will be used by the {@code #mutate(Tree)}
   *          method.
   */
  public void setTreeFactory(final Factory<Tree> newTreeFactory) {
    this.treeFactory = newTreeFactory;
  }

}
