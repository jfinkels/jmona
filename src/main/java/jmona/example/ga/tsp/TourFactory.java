/**
 * TourFactory.java
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
package jmona.example.ga.tsp;

import java.util.HashSet;
import java.util.Set;

import jmona.IndividualFactory;
import jmona.impl.Util;

/**
 * A factory which generates random Tours.
 * 
 * @author jfinkels
 */
public class TourFactory implements IndividualFactory<Tour> {

  /** The number of cities in generated Tours. */
  private final int numberOfCities;

  /**
   * Instantiate this factory with the specified number of cities in each
   * generated Tour.
   * 
   * @param initialNumberOfCities
   *          The number of cities in each generated Tour.
   */
  public TourFactory(final int initialNumberOfCities) {
    this.numberOfCities = initialNumberOfCities;
  }

  /**
   * Generate a random Tour of length specified in the constructor.
   * 
   * A Tour of length <em>l</em> will have cities numbered sequentially from
   * <em>0</em> to <em>l-1</em>, inclusive.
   * 
   * @return A randomly generated Tour.
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public Tour createIndividual() {

    final Set<Integer> cities = new HashSet<Integer>();

    for (int i = 0; i < this.numberOfCities; ++i) {
      cities.add(i);
    }

    final Tour tour = new Tour();

    Integer chosenCity = null;
    while (!cities.isEmpty()) {
      chosenCity = Util.randomFromSet(cities);
      cities.remove(chosenCity);
      tour.add(chosenCity);
    }

    return tour;
  }

}
