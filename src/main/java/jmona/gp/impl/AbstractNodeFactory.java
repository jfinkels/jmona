/**
 * AbstractNodeFactory.java
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
import jmona.gp.Node;
import jmona.gp.NodeFactory;
import jmona.impl.Util;

/**
 * A base NodeFactory for Nodes which have constructors with no arguments.
 * 
 * Nodes which have constructors which have required arguments must reimplement
 * NodeFactory.
 * 
 * @param <V>
 *          The type of value to which created Nodes evaluate.
 * @param <T>
 *          The type of Node which this factory creates.
 * @author jfinkels
 */
public abstract class AbstractNodeFactory<V, T extends Node<V>> implements
    NodeFactory<V, T> {

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
  public T createNode() throws InitializationException {
    final Class<T> nodeClass = Util.randomFromSet(this.nodeClasses());

    T result = null;
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
   * Get the Set of classes of Nodes which can be created by this factory.
   * 
   * @return The Set of classes of Nodes which can be created by this factory.
   */
  protected abstract Set<Class<T>> nodeClasses();

}