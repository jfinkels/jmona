/**
 * SelectionFunction.java
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

import java.util.Map;


/**
 * A class which provides a single method which selects an Individual based on
 * fitness scores.
 * 
 * @param <T>
 *          The type of Individual which will be selected.
 * @author jfinkels
 */
public interface SelectionFunction<T> {
  /**
   * Select an Individual based on the specified mapping from individuals to
   * corresponding fitness scores.
   * 
   * @param fitnesses
   *          A mapping from Individuals to corresponding fitness scores.
   * @return An Individual chosen from the specified map.
   * @throws SelectionException
   *           If there is a problem during selection of an Individual.
   */
  T select(final Map<T, Double> fitnesses) throws SelectionException;
}
