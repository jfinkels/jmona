/**
 * ValueComparator.java
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
package jmona.impl.selection;

import java.util.Comparator;
import java.util.Map;

import jmona.ComparisonException;

/**
 * A comparator which sorts elements of type K by comparing the values to which
 * they map given in a Map specified in the constructor for this class..
 * 
 * @author Jeffrey Finkelstein
 * @param <K>
 *          The type of element to be compared, and the type of key in the Map.
 * @param <V>
 *          The type of value in the Map.
 * @since 0.5
 */
public class ValueComparator<K, V extends Comparable<V>> implements
    Comparator<K> {

  /** The Map whose values are used to compare the keys. */
  private final Map<K, V> map;

  /**
   * Instantiates this Comparator using the values in the specified Map to
   * compare elements of type K.
   * 
   * @param initialMap
   *          The Map whose values are used to compare elements of type K.
   */
  public ValueComparator(final Map<K, V> initialMap) {
    this.map = initialMap;
  }

  /**
   * Compares the specified objects by comparing the values to which they map,
   * as given by the Map specified in the constructor of this Comparator.
   * 
   * If the values are the same in the Map specified in the constructor, then
   * this method returns the result of comparing the hash codes of the specified
   * objects.
   * 
   * If either of the specified objects are not in the Map, this method throws a
   * ComparisonException.
   * 
   * @param object1
   *          An object.
   * @param object2
   *          Another object.
   * @return The result of comparing the values
   * @throws ComparisonException
   *           If either of the specified objects does not exist in the Map
   *           given in the constructor of this class.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final K object1, final K object2) {
    if (!this.map.containsKey(object1) || !this.map.containsKey(object2)) {
      throw new ComparisonException(
          "One of the objects to compare does not exist in the Map whose values are used to compare these objects.");
    }

    final int result = this.map.get(object1).compareTo(this.map.get(object2));

    if (result != 0) {
      return result;
    }

    return object1.hashCode() - object2.hashCode();
  }

}
