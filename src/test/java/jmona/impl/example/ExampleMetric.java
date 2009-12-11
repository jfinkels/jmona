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
 * @author jfinkels
 */
public class ExampleMetric implements Metric<ExampleIndividual> {

  /* (non-Javadoc)
   * @see jmona.Metric#measure(java.lang.Object, java.lang.Object)
   */
  @Override
  public double measure(final ExampleIndividual element1, final ExampleIndividual element2) {
    return Math.abs(element1.fitness() - element2.fitness());
  }

}
