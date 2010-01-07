/**
 * Ant.java
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

import java.util.List;

/**
 * The agent which moves among vertices (cities) in a graph.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public interface Ant {
  /**
   * Gets this Ant's current vertex.
   * 
   * @return This Ant's current vertex.
   */
  Integer currentVertex();

  /**
   * Gets the Set of vertices which this Ant has already visited, including its
   * current vertex.
   * 
   * @return The Set of vertices which this Ant has already visited, including
   *         its current vertex.
   */
  List<Integer> memory();

  /**
   * Move this Ant to the specified new vertex.
   * 
   * @param newVertex
   *          The vertex to which to move this Ant.
   * @throws AlreadyVisitedException
   *           If the specified vertex has already been visited.
   */
  void moveTo(final Integer newVertex);

  /**
   * Reset the memory of this Ant so that it only knows about its initial
   * vertex.
   */
  void reset();
}
