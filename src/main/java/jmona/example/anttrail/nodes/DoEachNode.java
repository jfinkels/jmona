/**
 * DoEachNode.java
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
import jmona.gp.ExecutionException;
import jmona.gp.impl.TreeUtils;

/**
 * An AntNode which repeatedly executes each of its child AntNodes in order.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class DoEachNode extends AntFunctionNode {

  /** The symbol of the operation represented by this Node. */
  public static final String SYMBOL = "DO";
  /** The "arity" of this Node. */
  private final int arity;

  /**
   * Instantiate this Node with the specified Ant to control and the specified
   * number of child Nodes.
   * 
   * @param initialAnt
   *          The ant which this Node controls.
   * @param initialArity
   *          The "arity" of this Node.
   */
  public DoEachNode(final Ant initialAnt, final int initialArity) {
    super(initialAnt);
    this.arity = initialArity;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#arity()
   */
  @Override
  public int arity() {
    return this.arity;
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
  public DoEachNode deepCopy() throws CopyingException {
    final DoEachNode result = new DoEachNode(this.ant(), this.arity);

    TreeUtils.deepCopyChildren(result, this.children());

    return result;
  }

  /**
   * Unconditionally execute of the children of this Node in the order that they
   * appear in this Node's List of children.
   * 
   * @throws ExecutionException
   *           {@inheritDoc}
   * @see jmona.example.anttrail.nodes.AntNode#execute()
   */
  @Override
  public void execute() throws ExecutionException {

    // until the ant has exceeded its maximum number of allowed steps
    int i = 0;
    while (this.ant().movesMade() < this.ant().maxSteps()) {

      // execute the current child
      ((AntNode) this.children().get(i)).execute();

      // get the index of the next child
      i = (i + 1) % this.children().size();
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
