/**
 * EvaluatableNode.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.gp;

/**
 * A Node which can be evaluated (recursively) to an object of type T.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of value to which this node evaluates.
 * @since 0.3
 */
public interface EvaluatableNode<T> extends Node {
  /**
   * Evaluates this Node (by recursively evaluating its children) to an object
   * of type T.
   * 
   * @return The result of recursive evaluation of this Node.
   * @throws EvaluationException
   *           If there is a problem evaluating this Node or its children.
   */
  T evaluate() throws EvaluationException;
}
