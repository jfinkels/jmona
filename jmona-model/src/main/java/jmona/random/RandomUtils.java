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

import java.util.ArrayList;
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

  /** A RandomData object which has access to a RandomNumberGenerator. */
  private static RandomData randomData = new RandomDataImpl();

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
   * Returns an element from the specified array chosen with uniform
   * distribution over all elements of the array.
   * 
   * @param <T>
   *          The type of element in the array.
   * @param array
   *          The array from which to randomly choose an element.
   * @return An element chosen with uniform probability over all elements in the
   *         array.
   */
  public static <T> T choice(final T[] array) {
    return choice(Arrays.asList(array));
  }

  /**
   * Returns a uniformly random {@code boolean}.
   * 
   * @return A random boolean.
   */
  public static boolean nextBoolean() {
    return randomData.nextInt(0, 1) == 0;
  }

  /**
   * Returns a uniformly random {@code double} between 0 and 1, exclusive (this
   * is a convenience method for
   * {@code RandomData.randomData().nextUniform(0,1)}).
   * 
   * @return A uniformly random {@code double} between 0 and 1, exclusive.
   */
  public static double nextDouble() {
    return randomData.nextUniform(0, 1);
  }

  /**
   * Gets the static RandomData object stored in this class.
   * 
   * @return The static RandomData object stored in this class.
   */
  public static synchronized RandomData randomData() {
    return randomData;
  }

  /**
   * Choose with uniform distribution (without replacement) a random subset
   * (returned as a List) from the specified collection.
   * 
   * For a sample <em>with</em> replacement, see the
   * {@link #sampleWithReplacement(Collection, int)} method.
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
  @SuppressWarnings("unchecked")
  public static <T> List<T> sample(final Collection<T> collection,
      final int numberToChoose) {
    return Arrays.asList((T[]) randomData
        .nextSample(collection, numberToChoose));
  }

  /**
   * Choose with uniform distribution (with replacement) a random subset
   * (returned as a List) from the specified collection.
   * 
   * For a sample <em>without</em> replacement, see the
   * {@link #sample(Collection, int)} method.
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
  public static <T> List<T> sampleWithReplacement(
      final Collection<T> collection, final int numberToChoose) {
    final List<T> result = new ArrayList<T>();

    for (int i = 0; i < numberToChoose; ++i) {
      result.add(choice(collection));
    }

    return result;
  }

  /**
   * Sets the static RandomData object stored in this class.
   * 
   * @param newRandomData
   *          The static RandomData object stored in this class.
   */
  public static synchronized void setRandomData(final RandomData newRandomData) {
    randomData = newRandomData;
  }

  /** Instantiation disallowed except by subclasses. */
  protected RandomUtils() {
    // intentionally unimplemented
  }
}
