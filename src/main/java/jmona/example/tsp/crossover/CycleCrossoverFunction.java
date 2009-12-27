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
import jmona.example.tsp.Tour;
import jmona.impl.Util;

/**
 * CX, by Oliver, et al.
 * 
 * @author jfinkels
 */
// TODO documentation
public class CycleCrossoverFunction implements CrossoverFunction<Tour> {

  /*
   * (non-Javadoc)
   * 
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final Tour tour1, final Tour tour2) {
    // choose a random initial index of a city in the tour
    int index = Util.RANDOM.nextInt(tour1.size());
    final int initialCity = tour1.get(index);

    final List<Integer> cycle = new Vector<Integer>();

    index = tour1.indexOf(tour2.get(index));
    int currentCity = tour1.get(index);
    cycle.add(currentCity);

    while (currentCity != initialCity) {
      index = tour1.indexOf(tour2.get(index));
      currentCity = tour1.get(index);
      cycle.add(currentCity);
    }

    for (final int city : cycle) {
      swap(tour1, tour2, tour1.indexOf(city));
    }
  }

  /**
   * Swap the cities at the specified index in each of the specified Tours.
   * 
   * @param tour1
   *          A Tour.
   * @param tour2
   *          Another Tour.
   * @param index
   *          The index of the city to swap in each of the specified Tours.
   */
  protected static void swap(final Tour tour1, final Tour tour2, final int index) {
    int temp = tour1.get(index);
    tour1.set(index, tour2.get(index));
    tour2.set(index, temp);
  }

}
