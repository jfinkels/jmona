/**
 * Util.java
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
package jmona.impl;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Utility class containing objects and methods necessary for randomness.
 * 
 * @author jfinke
 */
public class Util {
  /** Random number generator. */
  public static final Random RANDOM = new Random();

  /**
   * Return an element from the set chosen with uniform distribution over all
   * elements.
   * 
   * @param <T>
   *          The type of element in the Set.
   * @param set
   *          The set from which to randomly choose an element.
   * @return An element chosen with uniform probability over all elements, or
   *         {@code null} if something crazy happened.
   */
  public static final <T> T randomFromSet(final Set<T> set) {
    int selection = RANDOM.nextInt(set.size());
    final Iterator<T> iterator = set.iterator();
    T element = null;
    while (iterator.hasNext()) {
      element = iterator.next();
      if (selection == 0) {
        return element;
      } else {
        selection -= 1;
      }
    }
    return null;
  }

  /** Instantiation disallowed. */
  protected Util() {
    // intentionally unimplemented
  }
}
