/**
 * CalcFunctionNode.java
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
package jmona.example.calc.nodes;

import jmona.gp.impl.AbstractTerminalNode;

/**
 * A base class for TerminalNode objects in the Calc evolution.
 * 
 * @author Jeffrey Finkelstein
 */
public abstract class AbstractCalcTerminalNode extends AbstractTerminalNode
    implements CalcNode {
  /** The symbol which represents this Node. */
  private final String symbol;

  /**
   * Instantiate this node with the specified symbol which represents this Node.
   * 
   * @param initialSymbol
   *          The symbol which represents this Node.
   */
  protected AbstractCalcTerminalNode(final String initialSymbol) {
    this.symbol = initialSymbol;
  }

  /**
   * Get the symbol which represents this Node.
   * 
   * @return The symbol which represents this Node.
   */
  public String toString() {
    return this.symbol;
  }

}
