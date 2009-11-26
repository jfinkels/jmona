/**
 * MonaCrossoverFunction.java
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
package jmona.example.ga.monalisa;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Map.Entry;

import jmona.CrossoverFunction;
import jmona.impl.Util;

/**
 * A crossover function which swaps a random polygon/color pair in two specified
 * parent Individuals.
 * 
 * @author jeff
 */
public class MonaCrossoverFunction implements CrossoverFunction<MonaIndividual> {

  /**
   * Swap a random polygon/color pair in the parent Individuals.
   * 
   * @param parent1
   *          A MonaIndividual.
   * @param parent2
   *          Another MonaIndividual
   * 
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final MonaIndividual parent1,
      final MonaIndividual parent2) {

    // get a random polygon/color pair from the left child
    int counter = Util.RANDOM.nextInt(parent1.gene().size());
    Polygon leftPolygon = null;
    Color leftColor = null;
    for (final Entry<Polygon, Color> entry : parent1.gene().entrySet()) {
      if (counter == 0) {
        leftPolygon = entry.getKey();
      } else {
        counter -= 1;
      }
    }
    leftColor = parent1.gene().remove(leftPolygon);

    // get a random polygon/color pair from the right child
    counter = Util.RANDOM.nextInt(parent2.gene().size());
    Polygon rightPolygon = null;
    Color rightColor = null;
    for (final Entry<Polygon, Color> entry : parent2.gene().entrySet()) {
      if (counter == 0) {
        rightPolygon = entry.getKey();
      } else {
        counter -= 1;
      }
    }
    rightColor = parent2.gene().remove(rightPolygon);

    // swap the polygons/colors
    parent1.gene().put(rightPolygon, rightColor);
    parent2.gene().put(leftPolygon, leftColor);
  }

}
