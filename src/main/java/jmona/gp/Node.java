/**
 * Node.java
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
package jmona.gp;

import java.util.List;

import jmona.DeepCopyable;

/**
 * A Node in a Tree.
 * 
 * @author Jeffrey Finkelstein
 */
public interface Node extends DeepCopyable<Node> {
  /**
   * Get the "arity" of this Node, that is, how many children it has.
   * 
   * @return The "arity" of this Node.
   */
  int arity();

  /**
   * Get a List of the children of this Node. The size of this List must be
   * exactly equal to the "arity" of this Node.
   * 
   * @return The children of this Node.
   */
  List<Node> children();

  /**
   * Get the parent Node of this Node.
   * 
   * @return The parent Node of this Node.
   */
  Node parent();

  /**
   * Set the parent of this Node.
   * 
   * @param newParent
   *          The parent of this Node.
   */
  void setParent(final Node newParent);
}
