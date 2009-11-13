/**
 * GPMutationFunction.java
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
import jmona.MutationException;
import jmona.MutationFunction;
import jmona.gp.Tree;
import jmona.gp.TreeFactory;

/**
 * A MutationFunction which replaces a random Node in a Tree with a randomly
 * generated subtree.
 * 
 * @param <V>
 *          The type of value to which a Tree Individual evaluates.
 * @author jfinkels
 */
public class GPMutationFunction<V> implements MutationFunction<Tree<V>> {

  /** The TreeFactory which will be used by the {@link #mutate(Tree)} method. */
  private TreeFactory<V> treeFactory = null;

  /**
   * Set the TreeFactory which will be used by the {@link #mutate(Tree)} method.
   * 
   * @param newTreeFactory
   *          The TreeFactory which will be used by the {@code #mutate(Tree)}
   *          method.
   */
  public void setTreeFactory(final TreeFactory<V> newTreeFactory) {
    this.treeFactory = newTreeFactory;
  }

  /**
   * Replace a random Node from the specified Tree with a randomly generated
   * subtree.
   * 
   * @param individual
   *          The Tree in which to replace a random Node with a randomly
   *          generated subtree.
   * @throws MutationException
   *           If there is a problem generating a random subtree.
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final Tree<V> individual) throws MutationException {
    try {
      final Tree<V> newTree = this.treeFactory.createIndividual();
      Util.replaceNode(individual, individual.randomNode(), newTree.root());
    } catch (final InitializationException exception) {
      throw new MutationException("Failed to generate a random subtree.",
          exception);
    }
  }

}
