/**
 * WorkerAnt.java
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
package jmona.aco.impl;

import java.util.List;
import java.util.Vector;

import jmona.aco.AlreadyVisitedException;
import jmona.aco.Ant;

/**
 * A default implementation of an Ant in the ant colony system.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class WorkerAnt implements Ant {
  /**
   * The List of vertices already visited by this Ant, including its current
   * vertex.
   */
  private List<Integer> memory = new Vector<Integer>();

  /**
   * Instantiates this Ant with the specified initial vertex.
   * 
   * @param initialVertex
   *          The initial vertex of this Ant.
   */
  public WorkerAnt(final int initialVertex) {
    this.moveTo(initialVertex);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  public List<Integer> memory() {
    return this.memory;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  public Integer currentVertex() {
    // get the last (most recently added) element of the list
    return this.memory.get(this.memory.size() - 1);
  }

  /**
   * {@inheritDoc}
   * 
   * @param newVertex
   *          {@inheritDoc}
   * @throws AlreadyVisitedException
   *           {@inheritDoc}
   */
  public void moveTo(final Integer newVertex) {
    if (this.memory.contains(newVertex)) {
      throw new AlreadyVisitedException("Vertex " + newVertex
          + " is already in memory (" + this.memory + ").");
    }

    // append the new vertex to the end of the list
    this.memory.add(newVertex);
  }

  /** {@inheritDoc} */
  public void reset() {
    // clear all but the first element of the list
    this.memory.subList(1, this.memory.size()).clear();
  }

}
