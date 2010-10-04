/**
 * MapUtils.java
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
package jmona.impl;

import java.util.Map;

/**
 * Utility class which provides static methods for operating on Maps.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public final class MapUtils {

  /**
   * Get the first value from the specified Map as returned by the iterator over
   * the Set of values in the Map.
   * 
   * @param <K>
   *          The type of keys in the specified Map.
   * @param <V>
   *          The type of values in the specified Map.
   * @param map
   *          The Map from which to retrieve the first value.
   * @return The first value from the specified Map.
   */
  public static <K, V> V firstValue(final Map<K, V> map) {
    return map.values().iterator().next();
  }

  /** Instantiation disallowed except by subclasses. */
  protected MapUtils() {
    // intentionally unimplemented
  }
}
