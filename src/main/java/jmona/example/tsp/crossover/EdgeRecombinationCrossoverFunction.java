/**
 * EdgeRecombinationCrossoverFunction.java
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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import jmona.Condition;
import jmona.CrossoverException;
import jmona.CrossoverFunction;
import jmona.MappingException;
import jmona.impl.Range;
import jmona.impl.Util;

import org.apache.log4j.Logger;

/**
 * Edge recombination crossover (also known as ERX) for tours in the traveling
 * salesman problem.
 * 
 * @author jfinkels
 */
// TODO ERX only yields one child
public class EdgeRecombinationCrossoverFunction implements
    CrossoverFunction<List<Integer>> {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(EdgeRecombinationCrossoverFunction.class);

  /**
   * Remove the specified city from the specified set of neighbors for each
   * city.
   * 
   * @param city
   *          The city to remove.
   * @param neighborSets
   *          The adjacency list as a mapping from city to a set of its neighbor
   *          cities.
   */
  protected static void removeFromAllNeighborSets(final int city,
      final Map<Integer, Set<Integer>> neighborSets) {

    // iterate over each set of neighbors
    for (final Set<Integer> neighbors : neighborSets.values()) {

      // remove the specified city from that set
      neighbors.remove(city);
    }
  }

  /**
   * Perform edge recombination crossover (also known as ERX) on the two
   * specified tours.
   * 
   * Edge recombination crossover works as follows. First, an adjacency list is
   * created, which maps each city to all of its neighbors in <em>either</em>
   * tour. Second, a new tour is created by repeatedly choosing the city with
   * the fewest neighbors as determined by the size of its adjacency list.
   * 
   * @param tour1
   *          A tour.
   * @param tour2
   *          Another tour.
   * @throws CrossoverException
   *           If there is a problem determining which cities satisfy the
   *           condition of having a specific number of neighbors.
   * @see jmona.CrossoverFunction#crossover(Object, Object)
   */
  @Override
  public void crossover(final List<Integer> tour1, final List<Integer> tour2)
      throws CrossoverException {

    // initialize the adjacency lists for cities in the tours
    final Map<Integer, Set<Integer>> neighborsBothTours = new HashMap<Integer, Set<Integer>>();

    // get the size of the tours
    final int size = tour1.size();

    LOG.debug("size of tour: " + size);

    // iterate over each city in tour 1
    Set<Integer> neighbors = null;
    for (final int i : new Range(size)) {

      // instantiate a new set of neighbor cities
      neighbors = new HashSet<Integer>();

      LOG.debug("  i +size - 1 % size: " + ((i + size - 1) % size));
      LOG.debug("  i + 1 % size: " + ((i + 1) % size));

      // add the neighbors of the current city in both tours
      // note: have to do (i + size - 1), because % operator does not work for
      // negative integers for some reason
      neighbors.add(tour1.get((i + size - 1) % size));
      neighbors.add(tour1.get((i + 1) % size));
      neighbors.add(tour2.get((i + size - 1) % size));
      neighbors.add(tour2.get((i + 1) % size));

      LOG.debug("  neighbors of city " + i + ": " + neighbors);

      // add this set to the adjacency list for both tours
      neighborsBothTours.put(i, neighbors);
    }

    LOG.debug("adjacency list: " + neighborsBothTours);

    // choose an initial city to add from a random parent
    int cityToAdd = 0;
    if (Util.RANDOM.nextBoolean()) {
      cityToAdd = tour1.get(0);
    } else {
      cityToAdd = tour2.get(0);
    }

    // create a list of all possible cities to add to the new tour
    final Set<Integer> allPossibleCities = new HashSet<Integer>();
    for (final int i : new Range(tour1.size())) {
      allPossibleCities.add(i);
    }
    LOG.debug("All possible cities: " + allPossibleCities);

    // create the comparator which counts the number of neighbors of each city
    final Comparator<Integer> neighborCountingComparator = new NeighborCountingComparator(
        neighborsBothTours);

    // create the condition which determines which cities have the same number
    // of neighbors
    final SameNumberOfNeighborsCondition sameNumberOfNeighbors = new SameNumberOfNeighborsCondition(
        neighborsBothTours);

    // create a new tour to contain the recombined individual
    final List<Integer> newTour = new Vector<Integer>();

    LOG.debug("city to add " + cityToAdd);

    // while the new tour is not complete
    int cityWithMinNeighbors = 0;
    int numberOfNeighbors = 0;
    List<Integer> citiesWithSameNumberOfNeighbors = null;
    while (newTour.size() < tour1.size()) {

      // get the neighbors of the city just added to the tour
      neighbors = neighborsBothTours.get(cityToAdd);

      LOG.debug("neighbors: " + neighbors);

      // if the set of neighbors is empty
      if (neighbors.size() == 0) {

        // choose a random city not already in the new tour
        cityToAdd = Util.randomFromCollection(allPossibleCities);

        LOG.debug("chose random city: " + cityToAdd);

      } else {

        // get the city with the minimum number of neighbors
        cityWithMinNeighbors = Collections.min(neighborsBothTours
            .get(cityToAdd), neighborCountingComparator);

        LOG.debug("chose city with min neighbors: " + cityWithMinNeighbors);
        
        // get the number of neighbors of that city
        numberOfNeighbors = neighborsBothTours.get(cityWithMinNeighbors).size();
        
        LOG.debug("  with number of neighbors: " + numberOfNeighbors);

        // set the condition with the number of neighbors to match
        sameNumberOfNeighbors.setNumberOfNeighborsToMatch(numberOfNeighbors);
        
        // get all cities with the same number of neighbors
        try {
          citiesWithSameNumberOfNeighbors = Util.filter(sameNumberOfNeighbors,
              allPossibleCities);
        } catch (final MappingException exception) {
          throw new CrossoverException(
              "Failed to filter cities by number of neighbors.", exception);
        }
        
        LOG.debug("all cities with that number of neighbors: " + citiesWithSameNumberOfNeighbors);
        
        // choose a random city from that list
        cityToAdd = Util.randomFromCollection(citiesWithSameNumberOfNeighbors);
        
        LOG.debug("randomly chose city to add: " + cityToAdd);
      }

      // add the chosen city to the new tour
      newTour.add(cityToAdd);

      LOG.debug("added city, new tour is now: " + newTour);

      // remove the city just added from the list of all possible cities
      allPossibleCities.remove(cityToAdd);

      LOG.debug("allPossibleCities: " + allPossibleCities);

      // remove that city from the adjacency list
      removeFromAllNeighborSets(cityToAdd, neighborsBothTours);

      LOG.debug("and removed " + cityToAdd + " from all neighbor sets");
    }

    // the edge recombination operator only generates one child...
    Collections.copy(tour1, newTour);
    Collections.copy(tour2, newTour);
  }

}
