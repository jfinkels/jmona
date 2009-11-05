/**
 * DefaultTerminalNodeFactory.java
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

import java.util.Set;

import jmona.gp.FunctionNode;
import jmona.gp.FunctionNodeFactory;

/**
 * A default factory for creating FunctionNode objects which have constructors
 * with no arguments.
 * 
 * @param <V>
 *          The type of value to which created Nodes evaluate.
 * @author jfinkels
 */
public class DefaultFunctionNodeFactory<V> extends
    AbstractNodeFactory<V, FunctionNode<V>> implements FunctionNodeFactory<V> {

  /** The set of FunctionNode classes. */
  private final Set<Class<FunctionNode<V>>> nodeClasses;

  /**
   * Instantiate this class with the specified set of FunctionNode classes.
   * 
   * @param initialNodeClasses
   *          The set of FunctionNode classes.
   */
  public DefaultFunctionNodeFactory(
      final Set<Class<FunctionNode<V>>> initialNodeClasses) {
    this.nodeClasses = initialNodeClasses;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.impl.AbstractNodeFactory#nodeClasses()
   */
  @Override
  protected Set<Class<FunctionNode<V>>> nodeClasses() {
    return this.nodeClasses;
  }

}
