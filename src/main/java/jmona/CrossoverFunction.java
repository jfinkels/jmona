/**
 * CrossoverFunction.java
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
package jmona;

/**
 * A class which provides a single method which performs a crossover (in place)
 * between genes of Individuals.
 * 
 * @param <T>
 *          The type of Individual whose genes will be crossed over.
 * @author jfinke
 */
public interface CrossoverFunction<T extends Individual> {

  /**
   * Crossover the genes of the specified individual (in-place).
   * 
   * @param individual1
   *          An Individual.
   * @param individual2
   *          Another Individual.
   * @throws CrossoverException
   *           If there is a problem performing crossover on the two specified
   *           Individuals.
   */
  void crossover(final T individual1, final T individual2)
      throws CrossoverException;
}
