/**
 * ExampleBadTree.java
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

import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.impl.DefaultTree;

/**
 * An example Tree which throws an EvaluationException when the
 * {@link #evaluate()} method is called.
 * 
 * @param <V>
 *          The type of value to which this Tree evaluates.
 * @author jfinkels
 */
public class ExampleBadTree<V> extends DefaultTree<V> {

  /**
   * Instantiate this Tree by calling the corresponding constructor of the
   * superclass.
   * 
   * @param initialRoot
   *          The root of this Tree.
   */
  public ExampleBadTree(final Node<V> initialRoot) {
    super(initialRoot);
  }

  /**
   * Always throws an EvauationException.
   * 
   * @return Never returns.
   * @throws EvaluationException
   *           Always throws this Exception.
   */
  @Override
  public V evaluate() throws EvaluationException {
    throw new EvaluationException();
  }

}
