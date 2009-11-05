/**
 * BinaryNode.java
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

import jmona.gp.EvaluationException;
import jmona.gp.Node;

/**
 * A Node with two children which represent inputs to a BinaryOperation.
 * 
 * @param <V>
 *          The type of value to which this Node evaluates.
 * @author jfinkels
 */
public class BinaryNode<V> extends AbstractFunctionNode<V> {

  /** The "arity" of this Node. */
  public static final int ARITY = 2;

  /** The binary operation which this Node represents. */
  private BinaryOperation<V, V> operation = null;

  /**
   * Instantiate this Node with the specified operation.
   * 
   * @param initialOperation
   *          The operation to perform on the children of this Node.
   */
  public BinaryNode(final BinaryOperation<V, V> initialOperation) {
    this.operation = initialOperation;
  }

  /**
   * {@inheritDoc}
   * 
   * @param input
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @throws EvaluationException
   *           {@inheritDoc}
   * @see jmona.gp.Node#evaluate()
   */
  @Override
  public V evaluate(final Object... input) throws EvaluationException {
    return this.operation.operate(this.left().evaluate(input), this.right()
        .evaluate(input));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#getArity()
   */
  @Override
  public int getArity() {
    return ARITY;
  }

  /**
   * Convenience method for {@code children().get(0)}.
   * 
   * @return The element at index 0 in the List of child Nodes.
   * @see #children()
   */
  public Node<V> left() {
    return this.children().get(0);
  }

  /**
   * Convenience method for {@code children().get(1)}.
   * 
   * @return The element at index 1 in the List of child Nodes.
   * @see #children()
   */
  public Node<V> right() {
    return this.children().get(1);
  }
}
