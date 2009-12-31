/**
 * ExampleTreeFactory.java
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

import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.impl.AbstractFunctionNode;
import jmona.gp.impl.AbstractTreeFactory;
import jmona.impl.Range;

/**
 * An example TreeFactory.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleTreeFactory extends AbstractTreeFactory<Integer> {

  /**
   * Create a complete binary Tree of the specified depth.
   * 
   * @param currentDepth
   *          The depth of the Tree.
   * @return A complete binary Tree of the specified depth.
   * @see jmona.gp.impl.AbstractTreeFactory#createTree(int)
   */
  @Override
  protected Node<Integer> createTree(final int currentDepth) {

    Node<Integer> result = null;
    Node<Integer> child = null;
    if (currentDepth <= 1) {
      result = new ExampleTerminalNode();
    } else {
      result = new ExampleBinaryNode();

      for (final int i : new Range(result.arity())) {
        child = this.createTree(currentDepth - 1);

        AbstractFunctionNode.attachChildToParent(
            (FunctionNode<Integer>) result, child);
        // result.children().add(child);
        // child.setParent(result);
      }
    }

    return result;
  }
}
