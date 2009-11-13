/**
 * TreeFactory.java
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jmona.InitializationException;
import jmona.gp.FunctionNodeFactory;
import jmona.gp.Node;
import jmona.gp.TerminalNodeFactory;
import jmona.gp.Tree;
import jmona.gp.TreeFactory;

/**
 * A base class for a TreeFactory which has a maximum depth.
 * 
 * @param <V>
 *          The type of value to which created Trees evaluate.
 * @author jfinkels
 */
public abstract class AbstractTreeFactory<V> implements TreeFactory<V> {

  /** The default maximum depth of a generated Tree. */
  public static final int DEFAULT_MAX_DEPTH = 3;
  /** The factory which creates FunctionNode objects. */
  private FunctionNodeFactory<V> functionNodeFactory = null;
  /** The maximum depth of a generated Tree. */
  private int maxDepth = DEFAULT_MAX_DEPTH;
  /** The factory which creates TerminalNode objects. */
  private TerminalNodeFactory<V> terminalNodeFactory = null;

  /**
   * Create a Tree by instantiating a Tree of class specified in the
   * {@link #treeClass} property, and assigning its root to be a tree created by
   * the {@link #createTree(int)} method.
   * 
   * The tree constructor must have a single argument of type Node.
   * 
   * @throws InitializationException
   *           If there is a problem invoking the constructor of the Tree class.
   * @return A Tree of depth at most {@link #maxDepth}.
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public Tree<V> createIndividual() throws InitializationException {
    // TODO I don't want this to be tied to a specified implementation of Tree
    return new DefaultTree<V>(this.createTree(this.maxDepth));
  }

  /**
   * Create a subtree of Node objects given the specified current (recursive)
   * depth into the overall Tree.
   * 
   * @param currentDepth
   *          The current (recursive) depth into the overall Tree.
   * @return A Node with branches of depth at most {@code currentDepth - 1}.
   * @throws InitializationException
   *           If there is a problem creating this subtree.
   */
  protected abstract Node<V> createTree(final int currentDepth)
      throws InitializationException;

  /**
   * Get the factory which creates FunctionNode objects.
   * 
   * @return The factory which creates FunctionNode objects.
   */
  public FunctionNodeFactory<V> functionNodeFactory() {
    return this.functionNodeFactory;
  }

  /**
   * Get the maximum depth of any generated Tree.
   * 
   * @return The maximum depth of any generated Tree.
   */
  public int maxDepth() {
    return this.maxDepth;
  }

  /**
   * Set the factory which creates FunctionNode objects.
   * 
   * @param newFunctionNodeFactory
   *          The factory which creates FunctionNode objects.
   */
  public void setFunctionNodeFactory(
      final FunctionNodeFactory<V> newFunctionNodeFactory) {
    this.functionNodeFactory = newFunctionNodeFactory;
  }

  /**
   * Set the maximum depth of a generated Tree.
   * 
   * @param newMaxDepth
   *          The maximum depth of a generated Tree.
   */
  public void setMaxDepth(final int newMaxDepth) {
    this.maxDepth = newMaxDepth;
  }

  /**
   * Set the factory which creates TerminalNode objects.
   * 
   * @param newTerminalNodeFactory
   *          The factory which creates TerminalNode objects.
   */
  public void setTerminalNodeFactory(
      final TerminalNodeFactory<V> newTerminalNodeFactory) {
    this.terminalNodeFactory = newTerminalNodeFactory;
  }

  /**
   * Get the factory which creates TerminalNode objects.
   * 
   * @return The factory which creates TerminalNode objects.
   */
  public TerminalNodeFactory<V> terminalNodeFactory() {
    return this.terminalNodeFactory;
  }
}
