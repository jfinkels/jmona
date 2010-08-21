/**
 * Functional.java
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
package jmona.functional;

import java.util.List;
import java.util.Vector;

import jmona.Condition;
import jmona.Function;
import jmona.MappingException;

/**
 * Utility class exposing methods borrowed from functional programming.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public final class Functional {

  /**
   * Determines whether every element in the specified Iterable is true (similar
   * to Python's <a
   * href="http://docs.python.org/library/functions.html#all">all()</a> built-in
   * function).
   * 
   * @param iterable
   *          The iterable to check for any false values.
   * @return Whether every value in the specified iterable is true.
   */
  public static boolean all(final Iterable<Boolean> iterable) {

    for (final boolean element : iterable) {
      if (!element) {
        return false;
      }
    }

    return true;
  }

  /** An instance of the identity condition, which always returns its input. */
  public static final Condition<Boolean> IDENTITY_CONDITION = new Condition<Boolean>() {
    /**
     * Returns the specified input.
     * 
     * @param input
     *          The value to return.
     * @return The specified input.
     * @see jmona.Function#execute(java.lang.Object)
     */
    @Override
    public Boolean execute(final Boolean input) {
      return input;
    }
  };

  /**
   * Determines whether any element in the specified Iterable is true (similar
   * to Python's <a
   * href="http://docs.python.org/library/functions.html#any">any()</a> built-in
   * function).
   * 
   * @param iterable
   *          The iterable to check for any true values.
   * @return Whether any value in the specified iterable is true.
   * @throws MappingException
   *           If there is a problem determining the truth value of any of the
   *           elements of the iterable.
   */
  public static boolean any(final Iterable<Boolean> iterable)
      throws MappingException {
    return any(iterable, IDENTITY_CONDITION);
  }

  /**
   * Determines whether any element in the specified Iterable is true under the
   * specified condition.
   * 
   * @param <T>
   *          The type of element in the iterable.
   * @param iterable
   *          The iterable to check for any true values.
   * @return Whether any value in the specified iterable is true.
   * @throws MappingException
   *           If there is a problem determining the truth value of any of the
   *           elements of the iterable.
   */
  public static <T> boolean any(final Iterable<T> iterable,
      final Condition<T> condition) throws MappingException {
    return !filter(condition, iterable).isEmpty();
  }

  /**
   * Gets a list containing references to all elements of the specified input
   * Iterable which satisfy the specified condition (similar to Python's
   * built-in
   * 
   * <code><a href="http://docs.python.org/library/functions.html#filter">filter</a></code>
   * function).
   * 
   * @param <E>
   *          The type of element contained in the specified input List.
   * @param iterable
   *          The List to filter by the given condition.
   * @param condition
   *          The condition by which to filter the specified input List.
   * @return A List containing references to only those elements of the
   *         specified input List which satisfy the given condition.
   * @throws MappingException
   *           If there is a problem executing the condition function.
   */
  public static <E> List<E> filter(final Condition<E> condition,
      final Iterable<E> iterable) throws MappingException {
    final List<E> result = new Vector<E>();

    for (final E element : iterable) {
      if (condition.execute(element)) {
        result.add(element);
      }
    }

    return result;
  }

  /**
   * Apply the specified function to each element of the specified Iterable
   * (similar to Python's built-in
   * <code><a href="http://docs.python.org/library/functions.html#map">map</a></code>
   * function).
   * 
   * @param <T>
   *          The type of the domain of the specified function, that is the type
   *          of element over which the specified Iterable object iterates.
   * @param <V>
   *          The type of the codomain of the specified function.
   * @param function
   *          The function to apply to each element of the specified Iterable.
   * @param iterable
   *          The object over which to iterate.
   * @return A List containing the results of applying the specified function to
   *         each element of the iteration in turn.
   * @throws MappingException
   *           If there is a problem applying the specified function to an
   *           element of the iteration.
   */
  public static <T, V> List<V> map(final Function<T, V> function,
      final Iterable<T> iterable) throws MappingException {
    final List<V> result = new Vector<V>();

    for (final T element : iterable) {
      result.add(function.execute(element));
    }

    return result;
  }

  /**
   * Returns the sum of the doubles over which the specified iterable iterates.
   * 
   * @param iterable
   *          The iterable over which to sum the doubles.
   * @return The sum of the doubles over which the specified iterable iterates.
   */
  public static double sumDouble(final Iterable<Double> iterable) {
    double result = 0;

    for (final Double d : iterable) {
      result += d;
    }

    return result;
  }

  /**
   * Returns the sum of the integers over which the specified iterable iterates.
   * 
   * @param iterable
   *          The iterable over which to sum the integers.
   * @return The sum of the integers over which the specified iterable iterates.
   */
  public static int sumInteger(final Iterable<Integer> iterable) {
    int result = 0;

    for (final Integer i : iterable) {
      result += i;
    }

    return result;
  }

  /**
   * Returns the sum of each of the bytes over which the specified iterable
   * iterates.
   * 
   * @param iterable
   *          The iterable over which to sum the bytes.
   * @return The sum of the bytes over which the specified iterable iterates.
   */
  public static <N extends Number> int sumBytes(final Iterable<N> iterable) {
    int sum = 0;

    for (final Number n : iterable) {
      sum += n.byteValue();
    }

    return sum;
  }

  /**
   * Returns a List of Pairs, where the <em>i</em>-th Pair contains the
   * <em>i</em>-th element from each of the iterables (similar to Python's
   * built-in
   * <code><a href="http://docs.python.org/library/functions.html#zip">zip</a></code>
   * function).
   * 
   * The size of the returned List is equal to the minimum of the "sizes" of the
   * input iterables (that is, the number of elements over which they iterate).
   * 
   * @param <S>
   *          The type of elements in the left iterable.
   * @param <T>
   *          The type of elements in the right iterable.
   * @param iterable1
   *          An iterable over elements of type S.
   * @param iterable2
   *          An iterable over elements of type T.
   * @return A List of Pairs, where the <em>i</em>-th Pair contains the
   *         <em>i</em>-th element from each of the iterables, and whose size is
   *         the minimum of the sizes of the input iterables.
   */
  // public static <S, T> List<Pair<S, T>> zip(final Iterable<S> iterable1,
  // final Iterable<T> iterable2) {
  // final List<Pair<S, T>> result = new ArrayList<Pair<S, T>>();
  //
  // final Iterator<S> it1 = iterable1.iterator();
  // final Iterator<T> it2 = iterable2.iterator();
  //
  // while (it1.hasNext() && it2.hasNext()) {
  // result.add(new Pair<S, T>(it1.next(), it2.next()));
  // }
  //
  // return result;
  // }

  /** Instantiation disallowed except by subclasses. */
  protected Functional() {
    // intentionally unimplemented
  }

}
