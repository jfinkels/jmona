/**
 * IndividualFactory.java
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
 * An object which provides a single factory method for generating an
 * Individual.
 * 
 * @param <T>
 *          The type of Individual to generate.
 * @author jeff
 */
public interface IndividualFactory<T extends Individual> {
  /**
   * Create a new instance of an Individual of type T.
   * 
   * @return A new instance of an Individual of type T.
   * @throws InitializationException
   *           If there is a problem creating an Individual.
   */
  T createIndividual() throws InitializationException;
}
