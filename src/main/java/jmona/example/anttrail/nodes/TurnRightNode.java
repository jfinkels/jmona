/**
 * TurnRightNode.java
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
 * Node which turns an Ant right (clockwise) by 90&deg;.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class TurnRightNode extends AntTerminalNode {

  /** The symbol of the operation represented by this Node. */
  public static final String SYMBOL = "RIGHT";

  /**
   * Instantiates this Node with the specified Ant to control.
   * 
   * @param initialAnt
   *          The Ant to control.
   */
  public TurnRightNode(final Ant initialAnt) {
    super(initialAnt);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public TurnRightNode deepCopy() {
    return new TurnRightNode(this.ant());
  }

  /**
   * Turn the Ant right (clockwise) by 90&deg;.
   * 
   * @see jmona.example.anttrail.nodes.AntNode#execute()
   */
  @Override
  public void execute() {
    this.ant().turnRight();
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
