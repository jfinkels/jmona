/**
 * PartiallyMatchedCrossoverFunction.java
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
package jmona.example.ga.tsp.crossover;

import jmona.CrossoverFunction;
import jmona.example.ga.tsp.Tour;
import jmona.impl.Util;

import org.apache.log4j.Logger;

/**
 * @author jfinkels
 */
public class PartiallyMatchedCrossoverFunction implements CrossoverFunction<Tour> {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(PartiallyMatchedCrossoverFunction.class);

  /*
   * (non-Javadoc)
   * 
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final Tour tour1, final Tour tour2) {

    // choose a random start and end point for the slice
    final int start = Util.RANDOM.nextInt(tour1.size());
    final int end = start + Util.RANDOM.nextInt(tour1.size() - start);

    LOG.debug("(start, end) = (" + start + ", " + end + ")");

    // instantiate two child tours
    final Tour child1 = new Tour();
    final Tour child2 = new Tour();

    // add the sublist in between the start and end points to the children
    child1.addAll(tour1.subList(start, end));
    child2.addAll(tour2.subList(start, end));

    LOG.debug("Child 1: " + child1);
    LOG.debug("Child 2: " + child2);

    
  }

}
