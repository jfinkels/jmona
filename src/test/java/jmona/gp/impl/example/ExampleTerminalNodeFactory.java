/**
 * ExampleTerminalNodeFactory.java
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

import jmona.Factory;
import jmona.InitializationException;
import jmona.gp.TerminalNode;

/**
 * An example TerminalNodeFactory.
 * 
 * @author jfinkels
 */
public class ExampleTerminalNodeFactory implements
    Factory<TerminalNode<Integer>> {

  /**
   * Creates an ExampleTerminalNode.
   * 
   * @return An ExampleTerminalNode.
   * @throws InitializationException
   *           Never throws this Exception.
   * @see jmona.Factory#createObject()
   */
  @Override
  public TerminalNode<Integer> createObject() throws InitializationException {
    return new ExampleTerminalNode();
  }

}
