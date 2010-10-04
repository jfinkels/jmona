/**
 * PheromoneUpdateStrategy.java
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
package jmona.aco;

/**
 * An object provided to an AntColonyEvolutionContext which defines the amount
 * of pheromone each ant leaves on an edge as it moves from one city to the next
 * (in the {@link #pheromoneToAddSingleEdge(double)} method) and on each edge in its
 * tour after the tour is complete (in the {@link #pheromoneToAddFullCycle(double)}
 * method).
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public interface PheromoneUpdateStrategy {
  /**
   * Gets the pheromone quantity index, a value which signifies the relative
   * amount of pheromone that each ant leaves on edges in its tour.
   * 
   * @return The pheromone quantity index, a value which signifies the relative
   *         amount of pheromone that each ant leaves on edges in its tour.
   */
  int pheromoneQuantityIndex();

  /**
   * Gets the amount of pheromone to add to each edge of a tour with the
   * specified total distance.
   * 
   * @param totalDistance
   *          The total distance of the tour.
   * @return The amount of pheromone to add to each edge of a tour with the
   *         specified total distance.
   */
  double pheromoneToAddFullCycle(final double totalDistance);

  /**
   * Gets the amount of pheromone to add to each edge as it is encountered by an
   * Ant making its tour.
   * 
   * @param edgeDistance
   *          The distance between the two vertices incident to the edge.
   * @return The amount of pheromone to add to each edge as it is encountered by
   *         an Ant making its tour.
   */
  double pheromoneToAddSingleEdge(final double edgeDistance);

  /**
   * Sets the pheromone quantity index, a value which signifies the relative
   * amount of pheromone that each ant leaves on edges in its tour.
   * 
   * @param newPheromoneQuantityIndex
   *          The pheromone quantity index, a value which signifies the relative
   *          amount of pheromone that each ant leaves on edges in its tour.
   */
  void setPheromoneQuantityIndex(final int newPheromoneQuantityIndex);
}
