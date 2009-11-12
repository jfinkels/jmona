/**
 * ExampleBinaryNode.java
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

import jmona.gp.impl.BinaryNode;

/**
 * An example BinaryNode for use in testing which utilizes the
 * ExampleBinaryOperation.
 * 
 * @author jfinkels
 */
public class ExampleBinaryNode extends BinaryNode<Integer> {
  /**
   * Instantiate this Node by providing an ExampleBinaryOperation object to the
   * constructor of the superclass.
   */
  public ExampleBinaryNode() {
    super(new ExampleBinaryOperation());
  }
}
