/**
 * AbstractTreeFactory.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import jmona.Factory;
import jmona.InitializationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.Tree;

/**
 * A base class for a TreeFactory which has a maximum depth.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public abstract class AbstractTreeFactory implements Factory<Tree> {

  /** The default maximum depth of a generated Tree. */
  public static final int DEFAULT_MAX_DEPTH = 3;
  /** The factory which creates FunctionNode objects. */
  private Factory<FunctionNode> functionNodeFactory = null;
  /** The maximum depth of a generated Tree. */
  private int maxDepth = DEFAULT_MAX_DEPTH;
  /** The factory which creates TerminalNode objects. */
  private Factory<TerminalNode> terminalNodeFactory = null;

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
  protected abstract Node createTree(final int currentDepth)
      throws InitializationException;

  /**
   * Get the factory which creates FunctionNode objects.
   * 
   * @return The factory which creates FunctionNode objects.
   */
  public Factory<FunctionNode> functionNodeFactory() {
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
      final Factory<FunctionNode> newFunctionNodeFactory) {
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
      final Factory<TerminalNode> newTerminalNodeFactory) {
    this.terminalNodeFactory = newTerminalNodeFactory;
  }

  /**
   * Get the factory which creates TerminalNode objects.
   * 
   * @return The factory which creates TerminalNode objects.
   */
  public Factory<TerminalNode> terminalNodeFactory() {
    return this.terminalNodeFactory;
  }
}
