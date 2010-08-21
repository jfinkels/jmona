/**
 * ClassCountingPostProcessor.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.game.impl;

import java.util.List;

import jmona.EvolutionContext;
import jmona.LoggingException;
import jmona.MappingException;
import jmona.PopulationEvolutionContext;
import jmona.functional.Functional;
import jmona.functional.operators.ToClass;
import jmona.impl.ListUtils;
import jmona.impl.postprocessing.LoggingPostProcessor;

/**
 * Count the number of objects of each class in an EvolutionContext.
 * 
 * @param <T>
 *          Classes of this type of object will be counted.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ClassCountingPostProcessor<T> extends LoggingPostProcessor<T> {

  /**
   * Count the number of objects of each class in the specified
   * EvolutionContext.
   * 
   * @param context
   *          The context from which to get the population of objects whose
   *          classes will be counted.
   * @return The string representation of a map from class to number of objects
   *         of that class in the specified EvolutionContext.
   * @throws LoggingException
   *           If the specified EvolutionContext is not a
   *           PopulationEvolutionContext, or if there is a problem determining
   *           the classes of the individuals in the current population of the
   *           specified EvolutionContext.
   * @see jmona.impl.postprocessing.PeriodicPostProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected String message(final EvolutionContext<T> context)
      throws LoggingException {
    if (!(context instanceof PopulationEvolutionContext<?>)) {
      throw new LoggingException(
          "Cannot get population from the EvolutionContext unless it is a PopulationEvolutionContext. Class of EvolutionContext is "
              + context.getClass());
    }

    List<Class<?>> classes = null;
    try {
      classes = Functional.map(new ToClass<T>(),
          ((PopulationEvolutionContext<T>) context).currentPopulation());
    } catch (final MappingException exception) {
      throw new LoggingException("Failed to determine classes of individuals.",
          exception);
    }

    return ListUtils.count(classes).toString();
  }

}
