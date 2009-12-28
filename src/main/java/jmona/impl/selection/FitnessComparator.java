/**
 * FitnessComparator.java
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
package jmona.impl.selection;

import java.util.Comparator;
import java.util.Map;

import jmona.exceptions.FitnessException;

/**
 * @author jfinkels
 */
public class FitnessComparator<T> implements Comparator<T> {

  private Map<T, Double> fitnesses = null;
  
  public void setFitnesses(final Map<T, Double> newFitnesses) {
    this.fitnesses = newFitnesses;
  }
  
  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final T object1, final T object2) {
    return this.fitnesses.get(object1).compareTo(this.fitnesses.get(object2));
  }

}
