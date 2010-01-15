/**
 * WorkerAntVector.java
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

import java.util.Vector;

import jmona.functional.Range;

/**
 * A Vector of WorkerAnt objects, which initially places one WorkerAnt on each
 * vertex.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class WorkerAntVector extends Vector<WorkerAnt> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -4951012312707300676L;

  /**
   * Instantiates this Vector with one WorkerAnt initially on each vertex.
   * 
   * @param numberOfVertices
   *          The number of vertices in the graph on which the WorkerAnts live.
   */
  public WorkerAntVector(final int numberOfVertices) {
    for (final int i : new Range(numberOfVertices)) {
      this.add(new WorkerAnt(i));
    }
  }
}
