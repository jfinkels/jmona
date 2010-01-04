/**
 * AbstractTerminalNode.java
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

import java.util.List;

import jmona.gp.Node;
import jmona.gp.TerminalNode;

/**
 * A terminal Node representing an element from the terminal set.
 * 
 * A terminal Node has no children, and the {@link #children()} method always
 * returns {@code null}.
 * 
 * @author Jeffrey Finkelstein
 */
public abstract class AbstractTerminalNode extends AbstractNode implements
    TerminalNode {

  /** The "arity" of this Node. */
  public static final int ARITY = 0;

  /**
   * Always returns {@value #ARITY}; a terminal node has no children.
   * 
   * @return 0
   * @see jmona.gp.Node#arity()
   */
  @Override
  public int arity() {
    return ARITY;
  }

  /**
   * Always return {@code null}; a terminal node has no children.
   * 
   * @return Always returns {@code null}.
   * @see jmona.gp.Node#children()
   */
  @Override
  public List<Node> children() {
    return null;
  }

}
