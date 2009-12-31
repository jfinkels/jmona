/**
 * ColoredPolygonNode.java
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
package jmona.example.monalisa.gp;

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.gp.EvaluationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.impl.AbstractFunctionNode;
import jmona.gp.impl.AbstractNode;

/**
 * A Node which contains a single ColoredPolygon object, and which adds its
 * ColoredPolygon to the list of ColoredPolygon objects to which its child Node
 * evaluates.
 * 
 * @author Jeffrey Finkelstein
 */
public class ColoredPolygonNode extends AbstractNode<List<ColoredPolygon>>
    implements FunctionNode<List<ColoredPolygon>>,
    TerminalNode<List<ColoredPolygon>> {

  /** The ColoredPolygon contained in this Node. */
  private ColoredPolygon coloredPolygon = null;

  /**
   * Set the ColoredPolygon contained in this Node.
   * 
   * @param newColoredPolygon
   *          The ColoredPolygon contained in this Node.
   */
  public void setColoredPolygon(final ColoredPolygon newColoredPolygon) {
    this.coloredPolygon = newColoredPolygon;
  }

  /** The children of this Node. */
  private List<Node<List<ColoredPolygon>>> children = new Vector<Node<List<ColoredPolygon>>>();

  /**
   * Evaluate this Node by adding its ColoredPolygon to the List of
   * ColoredPolygon objects returned by the evaluation of its child Node.
   * 
   * @return A List of ColoredPolygon, including the one contained in this Node
   *         and each of the ones contained in the descendants of this Node.
   * @throws EvaluationException
   *           If the child Node throws an EvaluationException.
   */
  public List<ColoredPolygon> evaluate() throws EvaluationException {

    List<ColoredPolygon> result = null;

    // if this is a terminal node
    if (this.children().size() == 0) {
      result = new Vector<ColoredPolygon>();
    } else { // if this is a function node
      result = this.children().get(CHILD_INDEX).evaluate();
    }

    // add the colored polygon in this node to the list
    result.add(this.coloredPolygon);

    return result;
  }

  /**
   * The index in the List of children of the single child of this Node, if it
   * exists.
   */
  public static final int CHILD_INDEX = 0;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#arity()
   */
  @Override
  public int arity() {
    return ARITY;
  }

  /** The "arity" of this Node. */
  public static final int ARITY = 1;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#children()
   */
  @Override
  public List<Node<List<ColoredPolygon>>> children() {
    return this.children;
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
  public ColoredPolygonNode deepCopy() throws CopyingException {
    final ColoredPolygonNode result = new ColoredPolygonNode();

    result.setColoredPolygon(this.coloredPolygon.deepCopy());

    Node<List<ColoredPolygon>> clonedChild = null;
    if (this.children.size() > 0) {
      clonedChild = this.children().get(CHILD_INDEX).deepCopy();

      AbstractFunctionNode.attachChildToParent(result, clonedChild);
    }

    return result;
  }
}
