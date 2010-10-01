/**
 * BadAntNode.java
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
package jmona.example.anttrail.gp;

import jmona.example.anttrail.Ant;
import jmona.example.anttrail.nodes.AntTerminalNode;
import jmona.gp.ExecutionException;

/**
 * An AntNode which always throws an Exception on Execution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
class BadAntNode extends AntTerminalNode {

  /** The symbolic representation of this Node. */
  public static final String SYMBOL = "X";

  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param initialAnt
   *          The ant which this Node controls.
   */
  public BadAntNode(final Ant initialAnt) {
    super(initialAnt);
  }

  /**
   * Always throws an ExecutionException.
   * 
   * @throws ExecutionException
   *           Always throws this Exception.
   */
  @Override
  public void execute() throws ExecutionException {
    throw new ExecutionException();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public BadAntNode deepCopy() {
    return new BadAntNode(this.ant());
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  protected String symbol() {
    return SYMBOL;
  }

}
