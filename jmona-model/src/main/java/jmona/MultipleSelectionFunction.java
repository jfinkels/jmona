/**
 * MultipleSelectionFunction.java
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
package jmona;

import java.util.List;
import java.util.Map;

/**
 * A SelectionFunction in which multiple individuals can be selected
 * simultaneously.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual which will be selected.
 * @since 0.5
 */
public interface MultipleSelectionFunction<T> extends SelectionFunction<T> {

  /**
   * Selects the specified number of individuals from the specified mapping from
   * individual to fitness.
   * 
   * @param fitnesses
   *          The mapping from individual to fitness.
   * @param numberToSelect
   *          The number of individuals to select.
   * @return An individual chosen from the specified Map.
   * @throws SelectionException
   *           If there is a problem during selection of an individual.
   */
  List<T> select(final Map<T, Double> fitnesses, final int numberToSelect)
      throws SelectionException;
}
