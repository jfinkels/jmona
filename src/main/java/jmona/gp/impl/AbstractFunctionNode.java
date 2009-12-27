/**
 * AbstractFunctionNode.java
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

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.impl.Util;

/**
 * A base class for an inner Node representing an element from the function set.
 * 
 * @param <V>
 *          The type of value to which this Node evaluates.
 * @author jfinkels
 */
public abstract class AbstractFunctionNode<V> extends AbstractNode<V> implements
    FunctionNode<V> {

  /**
   * Attach the specified child Node to the specified parent Node by setting the
   * child's parent to the specified parent Node and by adding the child Node to
   * the specified parent's List of child Nodes.
   * 
   * @param <T>
   *          The type of value to which the specified Nodes evaluate.
   * @param parent
   *          The parent Node.
   * @param child
   *          The child Node.
   */
  public static <T> void attachChildToParent(final FunctionNode<T> parent,
      final Node<T> child) {
    parent.children().add(child);
    child.setParent(parent);
  }

  /**
   * Helper method for performing a deep copy on the specified List of children
   * Nodes, and attaching them to the specified parent.
   * 
   * @param <T>
   *          The type of value to which the specified Nodes evaluate.
   * @param clonedParent
   *          The parent of the specified children.
   * @param childrenToCopy
   *          The List of children to copy and attach to the specified parent
   *          Node.
   * @throws CopyingException
   *           If there is a problem copying one of the children.
   */
  protected static <T> void deepCopyChildren(
      final FunctionNode<T> clonedParent, final List<Node<T>> childrenToCopy)
      throws CopyingException {

    // copy each child node
    final List<Node<T>> copiedChildren = Util.deepCopy(childrenToCopy);
    
    // iterate over each cloned child Node
    for (final Node<T> clonedChild : copiedChildren) {

      // attach the cloned child to the cloned parent
      attachChildToParent(clonedParent, clonedChild);
    }
  }

  /**
   * Children of this Node. The size of this List must equal the "arity" of this
   * Node.
   */
  private List<Node<V>> children = new Vector<Node<V>>();

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#children()
   */
  @Override
  public List<Node<V>> children() {
    return this.children;
  }

}
