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
package jmona.example.monalisa.ga;

import jmona.CrossoverFunction;
import jmona.example.monalisa.ColoredPolygon;
import jmona.impl.Util;

/**
 * A crossover function which swaps a random ColoredPolygon in a pair of
 * MonaIndividuals.
 * 
 * @author jfinkels
 */
public class MonaCrossoverFunction implements CrossoverFunction<MonaIndividual> {

  /**
   * Swap a random ColoredPolygon in the specified Individuals.
   * 
   * @param individual1
   *          A MonaIndividual.
   * @param individual2
   *          Another MonaIndividual
   * 
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final MonaIndividual individual1,
      final MonaIndividual individual2) {

    // choose a random polygon in each of the individuals
    final ColoredPolygon polygon1 = Util.randomFromCollection(individual1);
    final ColoredPolygon polygon2 = Util.randomFromCollection(individual2);

    // remove the polygons from their respective individuals
    individual1.remove(polygon1);
    individual2.remove(polygon2);

    // add the polygons to the opposite individuals
    individual1.add(polygon2);
    individual2.add(polygon1);
  }

}
