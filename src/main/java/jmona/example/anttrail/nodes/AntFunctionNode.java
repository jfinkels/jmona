/**
 * AntFunctionNode.java
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
import jmona.gp.impl.AbstractFunctionNode;

/**
 * A function node used in the ant trail evolution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public abstract class AntFunctionNode extends AbstractFunctionNode implements
    AntNode {

  /** The Ant with which this Node interacts. */
  private final Ant ant;

  /**
   * Instantiate this node with access to the specified Ant.
   * 
   * @param initialAnt
   *          The Ant with which this node interacts.
   */
  public AntFunctionNode(final Ant initialAnt) {
    this.ant = initialAnt;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public Ant ant() {
    return this.ant;
  }

  /**
   * The symbol of the operation represented by this Node.
   * 
   * @return The symbol of the operation represented by this Node.
   */
  protected abstract String symbol();

  /**
   * Get the String representation of this Node.
   * 
   * @return The String representation of this Node.
   */
  @Override
  public String toString() {
    return this.symbol() + "(" + this.children().get(0) + ", "
        + this.children().get(1) + ")";
  }
}
