/**
 * CalcFunctionNode.java
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
package jmona.example.calc.nodes;

import jmona.CopyingException;
import jmona.Function;
import jmona.example.calc.operations.UniformBinaryOperation;
import jmona.gp.EvaluationException;
import jmona.gp.impl.BinaryNode;
import jmona.gp.impl.TreeUtils;

/**
 * A function node which represents a binary operation on the functions returned
 * by its children.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CalcFunctionNode extends BinaryNode implements CalcNode {

  /** The operation performed by this Node on its children. */
  private final UniformBinaryOperation<Function<Double, Double>> operation;

  /**
   * Instantiate this Node with the specified operation to perform on its
   * children.
   * 
   * @param initialOperation
   *          The operation to perform on the children of this Node.
   */
  public CalcFunctionNode(
      final UniformBinaryOperation<Function<Double, Double>> initialOperation) {
    this.operation = initialOperation;
  }

  /**
   * Evaluate this Node by executing the operation contained in this Node on the
   * evaluation of each of the two child Nodes.
   * 
   * @return The result of executing the operation contained in this Node on the
   *         evaluation of each of the two child Nodes.
   * @throws EvaluationException
   *           If evaluating a child Node results in an EvaluationException.
   */
  @Override
  public Function<Double, Double> evaluate() throws EvaluationException {
    return this.operation.operate(this.left().evaluate(), this.right()
        .evaluate());
  }

  /**
   * Gets the left child of this Node.
   * 
   * This method assumes that the left child Node can be cast to CalcNode.
   * 
   * @return The left child of this Node.
   */
  public CalcNode left() {
    return (CalcNode) this.children().get(LEFT_CHILD_INDEX);
  }

  /**
   * Gets the right child of this Node.
   * 
   * This method assumes that the right child Node can be cast to CalcNode.
   * 
   * @return The right child of this Node.
   */
  public CalcNode right() {
    return (CalcNode) this.children().get(RIGHT_CHILD_INDEX);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws CopyingException
   *           {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public CalcFunctionNode deepCopy() throws CopyingException {
    // not copying the operation because it doesn't have any state
    final CalcFunctionNode result = new CalcFunctionNode(this.operation);

    // deep copy the children and attach them to the clone
    TreeUtils.deepCopyChildren(result, this.children());

    return result;
  }

  /**
   * Get the infix representation of the operation in this Node.
   * 
   * @return The infix representation of the operation this Node.
   */
  @Override
  public String toString() {
    return "(" + this.left().toString() + this.operation.toString()
        + this.right().toString() + ")";
  }
}
