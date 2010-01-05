/**
 * ExampleTernaryNode.java
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
import jmona.MappingException;
import jmona.functional.Functional;
import jmona.gp.EvaluationException;
import jmona.gp.impl.TernaryNode;

/**
 * An example TernaryNode.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleTernaryNode extends TernaryNode implements ExampleNode {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public ExampleTernaryNode deepCopy() throws CopyingException {
    return new ExampleTernaryNode();
  }

  /**
   * Gets the sum of the evaluation of the child Nodes.
   * 
   * @return The sum of the evaluation of the child Nodes.
   * @throws EvaluationException
   *           If there is a problem evaluating the child Nodes.
   * @see jmona.gp.impl.example.ExampleNode#evaluate()
   */
  @Override
  public int evaluate() throws EvaluationException {
    try {
      return Functional.sum(Functional.map(new ExampleNodeEvaluator(), this
          .children()));
    } catch (final MappingException exception) {
      throw new EvaluationException();
    }
  }

}
