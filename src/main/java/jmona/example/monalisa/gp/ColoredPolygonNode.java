/**
 * ColoredPolygonNode.java
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
package jmona.example.monalisa.gp;

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.gp.EvaluatableNode;
import jmona.gp.EvaluationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.impl.AbstractNode;
import jmona.gp.impl.TreeUtils;

/**
 * A Node which contains a single ColoredPolygon object, and which adds its
 * ColoredPolygon to the list of ColoredPolygon objects to which its child Node
 * evaluates.
 * 
 * @author Jeffrey Finkelstein
 */
public class ColoredPolygonNode extends AbstractNode implements FunctionNode,
    TerminalNode, EvaluatableNode<List<ColoredPolygon>> {

  /** The ColoredPolygon contained in this Node. */
  private ColoredPolygon coloredPolygon = null;
  /** The children of this Node. */
  private List<Node> children = new Vector<Node>();

  /**
   * The index in the List of children of the single child of this Node, if it
   * exists.
   */
  public static final int CHILD_INDEX = 0;
  /** The "arity" of this Node. */
  public static final int ARITY = 1;

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

  /**
   * Gets the sole child of this Node.
   * 
   * This method assumes that the child Node can be cast to ColoredPolygonNode.
   * 
   * @return The sole child of this Node.
   */
  public ColoredPolygonNode child() {
    return (ColoredPolygonNode) this.children().get(CHILD_INDEX);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#children()
   */
  @Override
  public List<Node> children() {
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

    Node clonedChild = null;
    if (this.children.size() > 0) {
      clonedChild = this.children().get(CHILD_INDEX).deepCopy();

      TreeUtils.attachChildToParent(result, clonedChild);
    }

    return result;
  }

  /**
   * Evaluate this Node by adding its ColoredPolygon to the List of
   * ColoredPolygon objects returned by the evaluation of its child Node.
   * 
   * @return A List of ColoredPolygon, including the one contained in this Node
   *         and each of the ones contained in the descendants of this Node.
   * @throws EvaluationException
   *           If the child Node throws an EvaluationException.
   */
  @Override
  public List<ColoredPolygon> evaluate() throws EvaluationException {

    List<ColoredPolygon> result = null;

    // if this is a terminal node
    if (this.children().size() == 0) {
      result = new Vector<ColoredPolygon>();
    } else { // if this is a function node
      result = this.child().evaluate();
    }

    // add the colored polygon in this node to the list
    result.add(this.coloredPolygon);

    return result;
  }

  /**
   * Set the ColoredPolygon contained in this Node.
   * 
   * @param newColoredPolygon
   *          The ColoredPolygon contained in this Node.
   */
  public void setColoredPolygon(final ColoredPolygon newColoredPolygon) {
    this.coloredPolygon = newColoredPolygon;
  }
}
