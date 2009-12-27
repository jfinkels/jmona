/**
 * MonaMutationFunction.java
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

import jmona.MutationFunction;
import jmona.example.monalisa.ColoredPolygonMutationFunction;
import jmona.impl.Util;

/**
 * @author jfinkels
 */
public class MonaMutationFunction implements MutationFunction<MonaIndividual> {

  public MonaMutationFunction(final int initialWidth, final int initialHeight) {
    this.coloredPolygonMutationFunction = new ColoredPolygonMutationFunction(
        initialWidth, initialHeight);
  }

  private final ColoredPolygonMutationFunction coloredPolygonMutationFunction;

  /*
   * (non-Javadoc)
   * 
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final MonaIndividual individual) {
    this.coloredPolygonMutationFunction.mutate(Util
        .randomFromCollection(individual));
  }

}
