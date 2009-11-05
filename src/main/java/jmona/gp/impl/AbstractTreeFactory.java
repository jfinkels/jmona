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
import java.util.Set;

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
  /** The maximum depth of a generated Tree. */
  private int maxDepth = DEFAULT_MAX_DEPTH;

  private Class<Tree<V>> treeClass = null;

  public Class<Tree<V>> treeClass() {
    return this.treeClass;
  }

  public void setTreeClass(final Class<Tree<V>> newTreeClass) {
    this.treeClass = newTreeClass;
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
   * Set the maximum depth of a generated Tree.
   * 
   * @param newMaxDepth
   *          The maximum depth of a generated Tree.
   */
  public void setMaxDepth(final int newMaxDepth) {
    this.maxDepth = newMaxDepth;
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public Tree<V> createIndividual() throws InitializationException {
    Tree<V> result = null;
    try {
      final Constructor<Tree<V>> constructor = this.treeClass
          .getConstructor(Node.class);
      result = constructor.newInstance(this.createTree(this.maxDepth));
    } catch (final SecurityException exception) {
      throw new InitializationException(exception);
    } catch (final NoSuchMethodException exception) {
      throw new InitializationException(exception);
    } catch (final IllegalArgumentException exception) {
      throw new InitializationException(exception);
    } catch (final InstantiationException exception) {
      throw new InitializationException(exception);
    } catch (final IllegalAccessException exception) {
      throw new InitializationException(exception);
    } catch (final InvocationTargetException exception) {
      throw new InitializationException(exception);
    }

    return result;
  }

  private TerminalNodeFactory<V> terminalNodeFactory = null;
  private FunctionNodeFactory<V> functionNodeFactory = null;

  public void setTerminalNodeFactory(
      final TerminalNodeFactory<V> newTerminalNodeFactory) {
    this.terminalNodeFactory = newTerminalNodeFactory;
  }

  public TerminalNodeFactory<V> terminalNodeFactory() {
    return this.terminalNodeFactory;
  }

  public FunctionNodeFactory<V> functionNodeFactory() {
    return this.functionNodeFactory;
  }

  public void setFunctionNodeFactory(
      final FunctionNodeFactory<V> newFunctionNodeFactory) {
    this.functionNodeFactory = newFunctionNodeFactory;
  }

  protected abstract Node<V> createTree(final int currentDepth)
      throws InitializationException;

}
