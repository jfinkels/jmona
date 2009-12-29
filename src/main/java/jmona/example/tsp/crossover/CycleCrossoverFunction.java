/**
 * CycleCrossoverFunction.java
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
package jmona.example.tsp.crossover;

import java.util.List;
import java.util.Vector;

import jmona.CrossoverFunction;
import jmona.impl.Util;

/**
 * CX, by Oliver, et al.
 * 
 * @author jfinkels
 */
// TODO documentation
public class CycleCrossoverFunction implements CrossoverFunction<List<Integer>> {

  /**
   * Swap a cycle chosen at random from the cycle decomposition of the
   * permutation defined by the mapping between the two specified tours.
   * 
   * Formally, let {@code tour1} and {@code tour2} be sequences of size
   * <em>n</em> consisting of the integers <em>{0,1,2,&hellip;,n-1}</em>. Define
   * the permutation <em>&sigma;</em> by
   * <em>&sigma;(n)={@code tour2.get(tour1.indexOf(n))}</em>. Since
   * <em>&sigma;</em> is injective by definition, and since the sizes of {@code
   * tour1} and {@code tour2} are equal, <em>&sigma;</em> is a bijection.
   * Therefore it is a permutation with a unique decomposition into a product of
   * disjoint cycles. Choose a cycle at random, and swap each of the numbers
   * contained in that cycle between {@code tour1} and {@code tour2}.
   * 
   * Swapping the integers in this way ensures that any integers in a tour
   * before the crossover must be in the tour after the crossover.
   * 
   * @param tour1
   *          A tour to crossover.
   * @param tour2
   *          Another tour to crossover.
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final List<Integer> tour1, final List<Integer> tour2) {
    final List<Integer> cycleIndices = new Vector<Integer>();

    // choose a random initial index of a city in the tour
    int tour1index = Util.RANDOM.nextInt(tour1.size());

    // add that index to the cycle indices
    cycleIndices.add(tour1index);

    // get the city in tour2 at that index
    int tour2city = tour2.get(tour1index);

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
      Util.swap(tour1, tour2, index);
    }
  }
}
