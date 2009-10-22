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
 * A class which provides a single method which performs a crossover between
 * genes of individuals in the specified population.
 * 
 * @param <T>
 *          The type of Individual whose genes will be crossed over.
 * @author jfinke
 */
public interface CrossoverFunction<T extends Individual> {

  /**
   * Crossover the genes of the specified parents, resulting in a pair of
   * children with the crossed over genes.
   * 
   * @param parents
   *          The parents whose genes will be crossed to yield children.
   * @return A pair of individuals having the crossed over genes of the parents.
   */
  Pair<T, T> crossover(final Pair<T, T> parents);
}
