/**
 * TernaryNode.java
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

import jmona.CopyingException;
import jmona.gp.EvaluationException;
import jmona.gp.Node;

/**
 * A Node with three children which represent inputs to a TernaryOperation.
 * 
 * @param <V>
 *          The type of value to which this Node evaluates.
 * @author jfinkels
 */
public class TernaryNode<V> extends AbstractFunctionNode<V> {

  /** The "arity" of this Node. */
  public static final int ARITY = 3;
  public static final int LEFT_CHILD_INDEX = 0;
  public static final int MIDDLE_CHILD_INDEX = 1;
  public static final int RIGHT_CHILD_INDEX = 2;
  /** The ternary operation which this Node represents. */
  private TernaryOperation<V, V, V, V> operation = null;

  /**
   * Instantiate this Node with the specified operation.
   * 
   * @param initialOperation
   *          The operation to perform on the children of this Node.
   */
  public TernaryNode(final TernaryOperation<V, V, V, V> initialOperation) {
    this.operation = initialOperation;
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
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws CopyingException
   *           {@inheritDoc}
   * @see jmona.gp.Node#deepCopy()
   */
  @Override
  public TernaryNode<V> deepCopy() throws CopyingException {
    // instantiate a new binary node with the same operation (the operation
    // doesn't need to be copied because it acts like a static class; it
    // maintains no state
    final TernaryNode<V> result = new TernaryNode<V>(this.operation);

    // copy the children and attach them to the copy of this Node
    deepCopyChildren(result, this.children());

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws EvaluationException
   *           {@inheritDoc}
   * @see jmona.gp.Node#evaluate()
   */
  @Override
  public V evaluate() throws EvaluationException {
    return this.operation.operate(this.left().evaluate(), this.middle()
        .evaluate(), this.right().evaluate());
  }

  /**
   * Convenience method for {@code children().get(0)}.
   * 
   * @return The element at index 0 in the List of child Nodes.
   * @see #children()
   */
  protected Node<V> left() {
    return this.children().get(LEFT_CHILD_INDEX);
  }

  /**
   * Convenience method for {@code children().get(1)}.
   * 
   * @return The element at index 1 in the List of child Nodes.
   * @see #children()
   */
  protected Node<V> middle() {
    return this.children().get(MIDDLE_CHILD_INDEX);
  }

  /**
   * Convenience method for {@code children().get(2)}.
   * 
   * @return The element at index 2 in the List of child Nodes.
   * @see #children()
   */
  protected Node<V> right() {
    return this.children().get(RIGHT_CHILD_INDEX);
  }
}
