/**
 * BinaryAntNode.java
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
package jmona.example.anttrail.nodes;

import jmona.example.anttrail.Ant;

/**
 * An AntNode with two children.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public abstract class BinaryAntNode extends AntFunctionNode {

  /** The "arity" of this Node. */
  public static final int ARITY = 2;
  /** The index of the left child Node in the List of child Nodes. */
  public static final int LEFT_CHILD_INDEX = 0;
  /** The index of the right child Node in the List of child Nodes. */
  public static final int RIGHT_CHILD_INDEX = 1;

  /**
   * Instantiate this AntFunctionNode with the specified DefaultAnt to control.
   * 
   * @param initialAnt
   *          The Ant to control.
   */
  public BinaryAntNode(final Ant initialAnt) {
    super(initialAnt);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#arity()
   */
  @Override
  public int arity() {
    return ARITY;
  }

  /**
   * Gets the left child of this Node.
   * 
   * Assumes the left child of this Node can be cast to AntNode.
   * 
   * @return The left child of this Node.
   */
  protected AntNode left() {
    return (AntNode) this.children().get(LEFT_CHILD_INDEX);
  }

  /**
   * Gets the right child of this Node.
   * 
   * Assumes the right child of this Node can be cast to AntNode.
   * 
   * @return The right child of this Node.
   */
  protected AntNode right() {
    return (AntNode) this.children().get(RIGHT_CHILD_INDEX);
  }

}
