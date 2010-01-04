/**
 * FullTreeFactory.java
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

import jmona.PropertyNotWritableException;

/**
 * A factory which creates a Tree of Nodes using the "full" method, that is,
 * each branch of the tree has the maximum depth, and the nodes at each depth
 * less than the maximum are chosen from the function set, while the Nodes at
 * maximum depth are chosen from the terminal set.
 * 
 * @author Jeffrey Finkelstein
 */
public class FullTreeFactory extends GrowTreeFactory {
  /**
   * Instantiate this TreeFactory by setting the probability of generating a
   * terminal node within a Tree at a depth less than the maximum depth to 0.
   */
  public FullTreeFactory() {
    super.setProbabilityTerminal(0);
  }

  /**
   * Always throws an Exception; cannot set the probability of generating a
   * terminal node within a Tree at a depth less than the maximum depth in this
   * class.
   * 
   * @param newProbabilityTerminal
   *          {@inheritDoc}
   * @throws PropertyNotWritableException
   *           Always throws this Exception.
   */
  @Override
  public void setProbabilityTerminal(final double newProbabilityTerminal) {
    throw new PropertyNotWritableException(
        "The probability of generating a TerminalNode must be 0.");
  }
}
