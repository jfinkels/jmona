/**
 * CycleCrossoverFunction.java
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
package jmona.example.tsp.crossover;

import java.util.List;
import java.util.Vector;

import jmona.CrossoverFunction;
import jmona.impl.ListUtils;
import jmona.impl.mutable.MutableInteger;
import jmona.random.RandomUtils;

/**
 * Cycle crossover (also known as CX) on tours in the traveling salesman
 * problem.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class CycleCrossoverFunction implements
    CrossoverFunction<List<MutableInteger>> {

  /**
   * <p>
   * Perform the cycle crossover function (also known as CX) on the specified
   * tours by swapping a cycle chosen at random from the cycle decomposition of
   * the permutation defined by the mapping between the two specified tours.
   * </p>
   * 
   * <p>
   * Formally, let {@code tour1} and {@code tour2} be sequences of size
   * <em>n</em> consisting of the integers <em>{0,1,2,&hellip;,n-1}</em>. Define
   * the permutation <em>&sigma;</em> by
   * <em>&sigma;(n)={@code tour2.get(tour1.indexOf(n))}</em>. Since
   * <em>&sigma;</em> is injective by definition, and since the sizes of {@code
   * tour1} and {@code tour2} are equal, <em>&sigma;</em> is a bijection.
   * Therefore it is a permutation with a unique decomposition into a product of
   * disjoint cycles. Choose a cycle at random, and swap each of the numbers
   * contained in that cycle between {@code tour1} and {@code tour2}.
   * </p>
   * 
   * <p>
   * Swapping the integers in this way ensures that any integers in a tour
   * before the crossover must be in the tour after the crossover.
   * </p>
   * 
   * @param tour1
   *          A tour to crossover.
   * @param tour2
   *          Another tour to crossover.
   * @see jmona.CrossoverFunction#crossover(Object, Object)
   */
  @Override
  public void crossover(final List<MutableInteger> tour1,
      final List<MutableInteger> tour2) {
    final List<Integer> cycleIndices = new Vector<Integer>();

    // choose a random initial index of a city in the tour
    int tour1index = RandomUtils.randomData().nextInt(0, tour1.size() - 1);

    // add that index to the cycle indices
    cycleIndices.add(tour1index);

    // get the city in tour2 at that index
    MutableInteger tour2city = tour2.get(tour1index);

    // get the index of that city in tour1
    tour1index = tour1.indexOf(tour2city);

    // if tour1index = initial index, stop
    while (tour1index != cycleIndices.get(0)) {

      // add that index to the cycle indices
      cycleIndices.add(tour1index);

      // get the city in tour2 at that index
      tour2city = tour2.get(tour1index);

      // get the index of that city in tour1
      tour1index = tour1.indexOf(tour2city);
    }

    // swap the cities at each of the indices of the determined cycle
    for (final int index : cycleIndices) {
      ListUtils.swap(tour1, tour2, index);
    }
  }
}
