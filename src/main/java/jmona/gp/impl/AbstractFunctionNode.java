/**
 * AbstractFunctionNode.java
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
package jmona.gp.impl;

import java.util.List;
import java.util.Vector;

import jmona.gp.FunctionNode;
import jmona.gp.Node;

/**
 * A base class for an inner Node in a Tree, representing an element from the
 * function set.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public abstract class AbstractFunctionNode extends AbstractNode implements
    FunctionNode {

  /**
   * Children of this Node. The size of this List must equal the "arity" of this
   * Node.
   */
  private List<Node> children = new Vector<Node>();

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#children()
   */
  @Override
  public List<Node> children() {
    return this.children;
  }

}
