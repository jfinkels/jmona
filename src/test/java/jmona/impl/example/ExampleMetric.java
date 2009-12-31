/**
 * ExampleMetric.java
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
package jmona.impl.example;

import jmona.Metric;

/**
 * Measures the distance between the fitnesses of ExampleIndividual objects.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleMetric implements Metric<ExampleIndividual> {

  /**
   * Measure the Euclidean distance between the fitnesses of the specified
   * ExampleIndividual objects.
   * 
   * @param individual1
   *          An ExampleIndividual.
   * @param individual2
   *          Another ExampleIndividual.
   * @return The distance between the two specified ExampleIndividuals.
   * @see jmona.Metric#measure(java.lang.Object, java.lang.Object)
   */
  @Override
  public double measure(final ExampleIndividual individual1,
      final ExampleIndividual individual2) {
    return Math.abs(individual1.fitness() - individual2.fitness());
  }

}
