/**
 * ExampleBinaryNode.java
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
package jmona.gp.impl.example;

import jmona.CopyingException;
import jmona.gp.EvaluationException;
import jmona.gp.impl.BinaryNode;
import jmona.gp.impl.TreeUtils;

/**
 * An example BinaryNode.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleBinaryNode extends BinaryNode implements ExampleNode {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws CopyingException
   *           {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public ExampleBinaryNode deepCopy() throws CopyingException {
    final ExampleBinaryNode result = new ExampleBinaryNode();

    TreeUtils.deepCopyChildren(result, this.children());

    return result;
  }

  /**
   * Get the difference between the evaluation of the two child Nodes.
   * 
   * @return The difference between the evaluation of the two child Nodes.
   * @throws EvaluationException
   *           If there is a problem evaluating the child Nodes.
   * @see jmona.gp.impl.example.ExampleNode#evaluate()
   */
  @Override
  public int evaluate() throws EvaluationException {
    final ExampleNode leftChild = (ExampleNode) this.children().get(
        LEFT_CHILD_INDEX);
    final ExampleNode rightChild = (ExampleNode) this.children().get(
        RIGHT_CHILD_INDEX);
    return leftChild.evaluate() - rightChild.evaluate();
  }
}
