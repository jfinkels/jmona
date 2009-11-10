/**
 * AbstractNode.java
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

import jmona.gp.Node;
import jmona.gp.Tree;

/**
 * A base class for a Node with a parent Node and a value.
 * 
 * @param <V>
 *          The type of value to which this Node evaluates.
 * @author jfinkels
 */
public abstract class AbstractNode<V> implements Node<V> {

  /** The parent of this Node. */
  private Node<V> parent = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#parent()
   */
  @Override
  public Node<V> parent() {
    return this.parent;
  }

  /**
   * Replace this Node with the specified new Node in the Set of child Nodes of
   * its parent.
   * 
   * @param newNode
   *          The Node with which to replace this Node.
   * @see jmona.gp.Node#replaceThisNodeWith(Node)
   */
  @Override
  public void replaceThisNodeWith(final Node<V> newNode) {
    // iterate over all siblings of this Node (including this Node)
    final List<Node<V>> siblings = this.parent.children();
    for (int i = 0; i < siblings.size(); ++i) {
      if (siblings.get(i) == this) {
        // remove this Node from the list of siblings
        siblings.remove(i);

        // insert the new node where this Node used to be
        siblings.add(i, newNode);

        // make the parent of the new Node equal to the parent of this Node
        newNode.setParent(this.parent);

        return;
      }
    }
  }

  /**
   * Replace this Node with the specified new subtree in the Set of child Nodes
   * of its parent.
   * 
   * @param newTree
   *          The subtree with which to replace this Node.
   * @see jmona.gp.Node#replaceThisNodeWith(Tree)
   */
  @Override
  public void replaceThisNodeWith(final Tree<V> newTree) {
    this.replaceThisNodeWith(newTree.root());
  }

  /**
   * {@inheritDoc}
   * 
   * @param newParent
   *          {@inheritDoc}
   * @see jmona.gp.Node#setParent(Node)
   */
  @Override
  public void setParent(final Node<V> newParent) {
    this.parent = newParent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param otherNode
   *          {@inheritDoc}
   * @see jmona.gp.Node#swapWith(Node)
   */
  @Override
  public void swapWith(final Node<V> otherNode) {
    // get the parent of the other node before make any changes to it
    final Node<V> otherParent = otherNode.parent();

    List<Node<V>> siblings = null;
    
    // if this node is not the root
    if (this.parent != null) {

      // get the siblings of this node
      siblings = this.parent.children();

      // iterate over all siblings of this Node (including this Node)
      for (int i = 0; i < siblings.size(); ++i) {
        
        // if we have found this node in the list of siblings
        if (siblings.get(i) == this) {
        
          // remove this Node from the list of its siblings
          siblings.remove(i);

          // insert the other Node where this Node used to be
          siblings.add(i, otherNode);

          break;
        }
      }
    }
    
    // set the parent of the other Node equal to the parent of this Node
    otherNode.setParent(this.parent);

    // if the other node was not the root
    if (otherParent != null) {

      // get the siblings of the other node
      siblings = otherParent.children();

      // iterate over all siblings of the other Node (including that Node)
      for (int i = 0; i < siblings.size(); ++i) {
        
        // if we have found the other node in the list of its siblings
        if (siblings.get(i) == otherNode) {

          // remove the other Node from the list of its siblings
          siblings.remove(i);

          // insert this Node where the other Node used to be
          siblings.add(i, this);

          break;
        }
      }
    } 
    
    // set the parent of this Node equal to the parent of the other Node
    this.setParent(otherParent);
  }
}
