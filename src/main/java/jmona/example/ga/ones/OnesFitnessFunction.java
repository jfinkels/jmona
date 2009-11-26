/**
 * OnesFitnessFunction.java
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
package jmona.example.ga.ones;

import jmona.FitnessFunction;

/**
 * A fitness function which gives higher fitness to individuals with a greater
 * number of ones in their genes.
 * 
 * @author jfinke
 */
public class OnesFitnessFunction implements FitnessFunction<OnesIndividual> {

  /**
   * Get the number of ones in the gene of the specified individual.
   * 
   * @param individual
   *          The individual whose fitness is to be determined.
   * @return The number of ones in the gene of the specified individual.
   * @see jmona.FitnessFunction#fitness(jmona.Individual)
   */
  @Override
  public double fitness(final OnesIndividual individual) {
    double result = 0.0;

    for (final short bit : individual.gene()) {
      result += bit;
    }

    return result;
  }

}
