/**
 * FunctionNodeFactory.java
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

import jmona.InitializationException;

/**
 * A factory for creating FunctionNode objects.
 * 
 * @param <V>
 *          The type of value to which a function node created by this factory
 *          evaluates.
 * @author jfinkels
 */
public interface FunctionNodeFactory<V> {
  /**
   * Create a FunctionNode.
   * 
   * @return A FunctionNode with evaluation type V.
   * @throws InitializationException
   *           If there is a problem creating a Node.
   */
  FunctionNode<V> createNode() throws InitializationException;
}
