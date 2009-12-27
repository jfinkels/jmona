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
package jmona.example.monalisa.ga;

import java.util.Vector;

import jmona.CopyingException;
import jmona.Individual;
import jmona.example.monalisa.ColoredPolygon;
import jmona.impl.Util;

/**
 * An individual containing a List of ColoredPolygon objects.
 * 
 * @author jfinkels
 */
public class MonaIndividual extends Vector<ColoredPolygon> implements
    Individual {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 7107356979311051797L;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws CopyingException
   *           {@inheritDoc}
   */
  @Override
  public MonaIndividual deepCopy() throws CopyingException {
    final MonaIndividual result = new MonaIndividual();

    result.addAll(Util.deepCopy(this));

    return result;
  }
}
