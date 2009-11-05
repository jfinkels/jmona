/**
 * DefaultTree.java
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

import java.util.List;
import java.util.Vector;

import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.impl.Util;

/**
 * A base class for a Tree.
 * 
 * @param <V>
 *          The type of value to which this Tree evaluates.
 * @author jfinkels
 */
public class DefaultTree<V> implements Tree<V> {

  /** The root Node of this Tree. */
  private Node<V> root = null;

  /**
   * Instantiate this Tree with the specified root Node.
   * 
   * @param initialRoot
   *          The root Node of this Tree.
   */
  public DefaultTree(final Node<V> initialRoot) {
    this.root = initialRoot;
  }

  /**
   * {@inheritDoc}
   * 
   * @param input
   *          {@inheritDoc}
   * @see jmona.gp.Tree#evaluate(java.lang.Object[])
   */
  @Override
  public V evaluate(final Object... input) throws EvaluationException {
    return this.root.evaluate(input);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#randomNode()
   */
  @Override
  public Node<V> randomNode() {
    final List<Node<V>> allNodes = new Vector<Node<V>>();

    int i = 0;
    allNodes.add(this.root);

    Node<V> currentNode = null;
    while (i < allNodes.size()) {
      currentNode = allNodes.get(i);
      allNodes.addAll(currentNode.children());
      i += 1;
    }

    return allNodes.get(Util.RANDOM.nextInt(allNodes.size()));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#root()
   */
  @Override
  public Node<V> root() {
    return this.root;
  }
}
