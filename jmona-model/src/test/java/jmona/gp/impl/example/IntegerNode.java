/**
 * IntegerNode.java
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

import jmona.gp.EvaluationException;
import jmona.gp.impl.AbstractTerminalNode;

/**
 * An example TerminalNode which contains an integer value.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class IntegerNode extends AbstractTerminalNode implements ExampleNode {

  /** The value of this Node. */
  private final int value;

  /**
   * Instantiate this Node with the specified value.
   * 
   * @param initialValue
   *          The value of this Node.
   */
  public IntegerNode(final int initialValue) {
    this.value = initialValue;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#deepCopy()
   */
  @Override
  public IntegerNode deepCopy() {
    final IntegerNode result = new IntegerNode(this.value);
    return result;
  }

  /**
   * Get the integer value of this Node.
   * 
   * @return The integer value of this Node.
   * @throws EvaluationException
   *           Never throws this Exception.
   * @see jmona.gp.EvaluatableNode#evaluate()
   */
  @Override
  public Integer evaluate() {
    return this.value;
  }

  /**
   * Get the value of this Node.
   * 
   * @return The value of this Node.
   */
  @Override
  public String toString() {
    return String.valueOf(this.value);
  }
}
