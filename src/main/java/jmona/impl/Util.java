/**
 * TreeUtils.java
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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import jmona.CopyingException;
import jmona.DeepCopyable;
import jmona.functional.Range;

/**
 * Utility class containing useful static utility methods.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public final class Util {

  /** Random number generator. */
  // TODO allow custom random number generators
  public static final Random RANDOM = new Random();

  /**
   * Perform a deep copy on the specified Collection of deep copyable elements.
   * 
   * This method runs in <em>O(n)</em> time.
   * 
   * @param <E>
   *          The type of deep copyable element in the Collection to be deep
   *          copied.
   * @param collection
   *          The Collection of elements on which to perform a deep copy.
   * @return A List of the copied elements from the specified Collection.
   * @throws CopyingException
   *           If there is a problem copying the elements of the Collection.
   */
  public static <E extends DeepCopyable<E>> List<E> deepCopy(
      final Collection<E> collection) throws CopyingException {

    final List<E> result = new Vector<E>();

    for (final E element : collection) {
      result.add((E) element.deepCopy());
    }

    return result;
  }

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

  /**
   * Get a random integer between {@code min} and {@code max-1}, inclusive.
   * 
   * @param min
   *          The minimum of the range from which to choose a random integer.
   * @param max
   *          The maximum of the range from which to choose a random integer.
   * @return A random integer between the specified minimum and maximum.
   */
  public static int randomBetween(final int min, final int max) {
    return Util.RANDOM.nextInt(max - min) + min;
  }

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
  public static <T> T randomFromCollection(final Collection<T> collection) {
    // generate the random index which defines which element to choose
    int selection = RANDOM.nextInt(collection.size());

    // iterate over all elements of the set until the selection has been reached
    for (final T element : collection) {
      if (0 == selection--) {
        return element;
      }
    }

    return null;
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
   * @throws IllegalArgumentException
   *           If the specified number of elements to choose is greater than the
   *           size of the specified collection.
   */
  public static <T> List<T> randomWithoutReplacement(
      final Collection<T> collection, final int numberToChoose) {

    // if the requested number to choose is greater than the size of the
    // collection, throw an IllegalArgumentException
    if (numberToChoose > collection.size()) {
      throw new IllegalArgumentException();
    }

    // if the requested number to choose is exactly equal to the size of the
    // collection, simply return a list containing references to all elements in
    // the collection
    if (numberToChoose == collection.size()) {
      return new Vector<T>(collection);
    }

    // TODO if numberToChoose > (collection.size() / 2), then remove elements

    // create a list to contain the randomly chosen elements
    final List<T> result = new Vector<T>();

    // get a list of references to all possible choices
    final List<T> allPossibleChoices = new Vector<T>(collection);

    // while the result list size is not yet the requested size
    T randomElement = null;
    while (result.size() < numberToChoose) {

      // choose a random element from the list of all possible choices
      randomElement = Util.randomFromCollection(allPossibleChoices);

      // remove that element from the list of all possible choices so it won't
      // be chosen again
      allPossibleChoices.remove(randomElement);

      // add that randomly chosen element to the result list
      result.add(randomElement);
    }

    return result;
  }

  /**
   * Swap the element at the specified index between the two specified Lists.
   * 
   * @param list2
   *          A List.
   * @param list1
   *          Another List.
   * @param index
   *          The index of the element to swap.
   * @param <E>
   *          The type of element in the List to swap.
   */
  public static <E> void swap(final List<E> list1, final List<E> list2,
      final int index) {

    final E temp = list1.get(index);
    list1.set(index, list2.get(index));
    list2.set(index, temp);

  }

  /**
   * Swap each element in the specified Lists between the specified start and
   * end indices.
   * 
   * @param list1
   *          A List.
   * @param list2
   *          Another List.
   * @param start
   *          The index of the first element to swap.
   * @param end
   *          The index of the last element to swap.
   * @param <E>
   *          The type of elements in the List to swap.
   */
  public static <E> void swap(final List<E> list1, final List<E> list2,
      final int start, final int end) {
    for (final int i : new Range(start, end)) {
      swap(list1, list2, i);
    }
  }

  /** Instantiation disallowed. */
  protected Util() {
    // intentionally unimplemented
  }
}
