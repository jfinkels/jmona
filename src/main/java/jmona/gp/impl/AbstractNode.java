/**
 * AbstractNode.java
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

import jmona.gp.Node;

/**
 * A base class for a Node with a parent Node and a value.
 * 
 * @param <V>
 *          The type of value to which this Node evaluates.
 * @author Jeffrey Finkelstein
 */
public abstract class AbstractNode<V> implements Node<V> {

  /** The parent of this Node. */
  private Node<V> parent = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#parent()
   */
  @Override
  public Node<V> parent() {
    return this.parent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newParent
   *          {@inheritDoc}
   * @see jmona.gp.Node#setParent(Node)
   */
  @Override
  public void setParent(final Node<V> newParent) {
    this.parent = newParent;
  }
}
