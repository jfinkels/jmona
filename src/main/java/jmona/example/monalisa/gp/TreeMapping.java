/**
 * TreeMapping.java
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

import java.awt.image.BufferedImage;
import java.util.List;

import jmona.SingleInputFunction;
import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.MonaMapping;
import jmona.exceptions.MappingException;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;

/**
 * A function which maps a Tree which evaluates to a List of ColoredPolygon
 * objects to the BufferedImage which the polygons represent.
 * 
 * @author jfinkels
 */
public class TreeMapping implements
    SingleInputFunction<Tree<List<ColoredPolygon>>, BufferedImage> {

  /**
   * A mapping from a List of ColoredPolygon objects to a BufferedImage which
   * the polygons represent.
   */
  private final MonaMapping monaMapping;

  /**
   * Instantiate this mapping with the the specified width and height for the
   * output image.
   * 
   * @param initialWidth
   *          The width of the output image.
   * @param initialHeight
   *          The height of the output image.
   */
  public TreeMapping(final int initialWidth, final int initialHeight) {
    this.monaMapping = new MonaMapping(initialWidth, initialHeight);
  }

  /**
   * Map the specified Tree to the Image which the ColoredPolygon objects
   * contained in its Nodes represent.
   * 
   * @param tree
   *          A Tree which evaluates to a List of ColoredPolygon objects.
   * @return The BufferedImage which is represented by the ColoredPolygon
   *         objects in the Node objects of the specified Tree.
   * @throws MappingException
   *           If the tree fails to evaluate.
   * @see jmona.SingleInputFunction#execute(java.lang.Object)
   */
  @Override
  public BufferedImage execute(final Tree<List<ColoredPolygon>> tree)
      throws MappingException {
    try {
      return this.monaMapping.execute(tree.evaluate());
    } catch (final EvaluationException exception) {
      throw new MappingException("Failed to evaluate tree.", exception);
    }
  }

}
