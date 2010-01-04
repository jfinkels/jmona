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

/**
 * A Node with three children which represent inputs to a TernaryOperation.
 * 
 * @author Jeffrey Finkelstein
 */
public abstract class TernaryNode extends AbstractFunctionNode {

  /** The "arity" of this Node. */
  public static final int ARITY = 3;
  /** The index of the left child within the List of children. */
  public static final int LEFT_CHILD_INDEX = 0;
  /** The index of the middle child within the List of children. */
  public static final int MIDDLE_CHILD_INDEX = 1;
  /** The index of the right child within the List of children. */
  public static final int RIGHT_CHILD_INDEX = 2;

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

}
