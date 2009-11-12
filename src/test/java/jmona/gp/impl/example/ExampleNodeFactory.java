/**
 * ExampleNodeFactory.java
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
package jmona.gp.impl.example;

import java.util.HashSet;
import java.util.Set;

import jmona.gp.Node;
import jmona.gp.impl.AbstractNodeFactory;

/**
 * An example AbstractNodeFactory which creates IntegerNode objects.
 * 
 * @author jfinkels
 */
public class ExampleNodeFactory extends
    AbstractNodeFactory<Integer, Node<Integer>> {
  /** The set of Classes from which this factory creates instances. */
  private final Set<Class<Node<Integer>>> nodeClasses = new HashSet<Class<Node<Integer>>>();

  /**
   * Instantiate this factory so that it only creates nodes of type IntegerNode.
   * 
   * @throws ClassNotFoundException
   *           If the IntegerNode class cannot be found.
   */
  public ExampleNodeFactory() throws ClassNotFoundException {
    nodeClasses.add((Class<Node<Integer>>) Class
        .forName("jmona.gp.impl.example.ExampleTerminalNode"));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.impl.AbstractNodeFactory#nodeClasses()
   */
  @Override
  protected Set<Class<Node<Integer>>> nodeClasses() {
    return this.nodeClasses;
  }

}
