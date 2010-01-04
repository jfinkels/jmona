/**
 * IfFoodAheadNode.java
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
package jmona.example.anttrail.nodes;

import jmona.CopyingException;
import jmona.example.anttrail.Ant;
import jmona.gp.impl.TreeUtils;

/**
 * Node which executes the left child if there is food in the cell ahead of the
 * ant, otherwise executes the right child.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class IfFoodAheadNode extends BinaryAntNode {

  /** The symbol of the operation represented by this Node. */
  public static final String SYMBOL = "IFFOODAHEAD";

  /**
   * Instantiate this Node with the specified ant to control.
   * 
   * @param initialAnt
   *          The ant to control.
   */
  public IfFoodAheadNode(final Ant initialAnt) {
    super(initialAnt);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws CopyingException
   *           {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public IfFoodAheadNode deepCopy() throws CopyingException {
    final IfFoodAheadNode result = new IfFoodAheadNode(this.ant());

    TreeUtils.deepCopyChildren(result, this.children());

    return result;
  }

  /**
   * Executes the left child Node if the ant has food in the cell directly in
   * front of it, otherwise executes the right child Node.
   * 
   * @see jmona.example.anttrail.nodes.AntNode#execute()
   */
  @Override
  public void execute() {
    if (this.ant().isFoodAhead()) {
      this.left().execute();
    } else {
      this.right().execute();
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.nodes.AntFunctionNode#symbol()
   */
  @Override
  protected String symbol() {
    return SYMBOL;
  }
}
