/**
 * RandomUtils.java
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
package jmona.random;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;

/**
 * Utilities for random number generation.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public final class RandomUtils {

  /**
   * A RandomData object which has access to a (not cryptographically secure)
   * RandomNumberGenerator.
   */
  public static final RandomData RANDOM = new RandomDataImpl();

  /**
   * Return an element from the Collection chosen with uniform distribution over
   * all elements.
   * 
   * @param <T>
   *          The type of element in the Collection.
   * @param collection
   *          The Collection from which to randomly choose an element.
   * @return An element chosen with uniform probability over all elements.
   */
  public static <T> T choice(final Collection<T> collection) {
    return sample(collection, 1).get(0);
  }

  /**
   * Choose a random sublist from the specified collection with uniform
   * distribution without replacement.
   * 
   * @param <T>
   *          The type of element in the collection from which to choose.
   * @param collection
   *          The collection from which to choose a sublist.
   * @param numberToChoose
   *          The number of elements to choose from the specified collection.
   * @return A sublist of the requested size of elements chosen randomly from
   *         the specified collection without repeats.
   */
  public static <T> List<T> sample(
      final Collection<T> collection, final int numberToChoose) {
    return Arrays.asList((T[]) RANDOM.nextSample(collection,
        numberToChoose));
  }

  /** Instantiation disallowed except by subclasses. */
  protected RandomUtils() {
    // intentionally unimplemented
  }
}
