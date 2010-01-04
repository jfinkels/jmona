/**
 * Tree.java
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

import jmona.DeepCopyable;

/**
 * A Tree of Nodes, which is also an Individual.
 * 
 * @author Jeffrey Finkelstein
 */
public interface Tree extends DeepCopyable<Tree> {

  /**
   * Get a random Node in the Tree.
   * 
   * @return A random Node in the Tree.
   */
  Node randomNode();

  /**
   * Get the root Node of this Tree.
   * 
   * @return The root Node of this Tree.
   */
  Node root();

  /**
   * Set the root Node of this Tree.
   * 
   * @param newRoot
   *          The root Node of this Tree.
   */
  void setRoot(final Node newRoot);
}
