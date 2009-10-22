/**
 * Individual.java
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
 * An individual in a population.
 * 
 * @author jfinke
 */
public interface Individual {
  /**
   * Get a cloned copy of this individual.
   * 
   * @param <T>
   *          A class implementing the Individual interface.
   * @return A cloned copy of this individual.
   */
  // TODO do i have to make this generic? this leaves many unchecked conversions
  <T extends Individual> T copy();
}
