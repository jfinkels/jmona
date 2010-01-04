/**
 * MoveForwardNode.java
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
 * Node which moves the ant forward one cell when executed.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class MoveForwardNode extends AntTerminalNode {

  /** The symbol of the operation represented by this Node. */
  public static final String SYMBOL = "MOVE";

  /**
   * Instantiates this Node with access to the specified Ant.
   * 
   * @param initialAnt
   *          The Ant to control.
   */
  public MoveForwardNode(final Ant initialAnt) {
    super(initialAnt);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public MoveForwardNode deepCopy() {
    return new MoveForwardNode(this.ant());
  }

  /**
   * Moves the Ant forward one cell with respect to its current orientation.
   * 
   * @see jmona.example.anttrail.nodes.AntNode#execute()
   */
  @Override
  public void execute() {
    this.ant().advance();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.nodes.AntTerminalNode#symbol()
   */
  @Override
  protected String symbol() {
    return SYMBOL;
  }
}
