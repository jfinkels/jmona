/**
 * MonaIndividual.java
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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jmona.GeneIndividual;

/**
 * An individual containing a mapping from polygons to corresponding colors for
 * those polygons.
 * 
 * @author jfinkels
 */
public class MonaIndividual implements GeneIndividual<Map<Polygon, Color>> {

  /** The gene of this individual, a mapping from polygon to color. */
  private final Map<Polygon, Color> gene = new HashMap<Polygon, Color>();

  /**
   * Return a cloned copy of this individual (performing a deep copy on the
   * gene).
   * 
   * @return A cloned copy of this individual.
   */
  @Override
  public MonaIndividual deepCopy() {
    // create a clone
    final MonaIndividual result = new MonaIndividual();

    // iterate over each polygon, color pair in this gene
    Polygon currentPolygon = null, newPolygon = null;
    for (final Entry<Polygon, Color> entry : this.gene.entrySet()) {
      // get the current polygon
      currentPolygon = entry.getKey();

      // create a new polygon with the same values as the previous ones
      newPolygon = new Polygon(currentPolygon.xpoints.clone(),
          currentPolygon.ypoints.clone(), currentPolygon.npoints);

      // put the new polygon and color on the clone
      result.gene.put(newPolygon, entry.getValue());
    }

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * The gene of this Individual is a mapping from polygons to colors.
   * 
   * @return {@inheritDoc}
   */
  @Override
  public Map<Polygon, Color> gene() {
    return this.gene;
  }
}
