/**
 * BaseDefaultTreeFactory.java
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
package jmona.gp.impl;

import jmona.InitializationException;

/**
 * A base class for a TreeFactory which creates an instance of a DefaultTree
 * object.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public abstract class BaseDefaultTreeFactory extends AbstractTreeFactory {

  /**
   * Create a Tree by instantiating a DefaultTree and assigning its root to be a
   * tree created by the {@link #createTree(int)} method.
   * 
   * @return A DefaultTree instance of depth at most {@link #maxDepth()}.
   * @throws InitializationException
   *           If there is a problem invoking the constructor of the Tree class.
   * @see jmona.Factory#createObject()
   */
  @Override
  public DefaultTree createObject() throws InitializationException {
    return new DefaultTree(this.createTree(this.maxDepth()));
  }

}
