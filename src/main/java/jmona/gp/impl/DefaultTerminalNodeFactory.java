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

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import jmona.InitializationException;
import jmona.gp.TerminalNode;
import jmona.gp.TerminalNodeFactory;
import jmona.impl.Util;

/**
 * A default factory for creating TerminalNode objects which have constructors
 * with no arguments.
 * 
 * @param <V>
 *          The type of value to which created Nodes evaluate.
 * @author jfinkels
 */
public class DefaultTerminalNodeFactory<V> implements TerminalNodeFactory<V> {

  /** The set of TerminalNode classes. */
  private final Set<Class<TerminalNode<V>>> nodeClasses;

  /**
   * Instantiate this class with the specified set of TerminalNode classes.
   * 
   * @param initialNodeClasses
   *          The set of TerminalNode classes.
   */
  public DefaultTerminalNodeFactory(
      final Set<Class<TerminalNode<V>>> initialNodeClasses) {
    this.nodeClasses = initialNodeClasses;
  }

  /**
   * Create a Node by randomly selecting a class from {@link #nodeClasses()}
   * with uniform distribution, then calling the constructor with no arguments
   * in that class.
   * 
   * For Nodes with constructors with required arguments, this method must be
   * reimplemented.
   * 
   * @return A Node of type T.
   * @throws InitializationException
   *           If a new instance of a class cannot be reflectively instantiated.
   * @see jmona.gp.NodeFactory#createNode()
   */
  @Override
  public TerminalNode<V> createNode() throws InitializationException {
    final Class<? extends TerminalNode<V>> nodeClass = Util
        .randomFromCollection(this.nodeClasses());

    TerminalNode<V> result = null;
    try {
      result = nodeClass.getConstructor().newInstance();
    } catch (final IllegalArgumentException exception) {
      throw new InitializationException(exception);
    } catch (final SecurityException exception) {
      throw new InitializationException(exception);
    } catch (final InstantiationException exception) {
      throw new InitializationException(exception);
    } catch (final IllegalAccessException exception) {
      throw new InitializationException(exception);
    } catch (final InvocationTargetException exception) {
      throw new InitializationException(exception);
    } catch (final NoSuchMethodException exception) {
      throw new InitializationException(exception);
    }

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.impl.AbstractNodeFactory#nodeClasses()
   */
  protected Set<Class<TerminalNode<V>>> nodeClasses() {
    return this.nodeClasses;
  }

}
