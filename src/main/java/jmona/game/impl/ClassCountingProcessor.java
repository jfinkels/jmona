/**
 * ClassCountingProcessor.java
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

import jfcommon.functional.Functional;
import jfcommon.functional.MappingException;
import jfcommon.functional.operators.ToClass;
import jmona.LoggingException;
import jmona.PopulationEvolutionContext;
import jmona.impl.ListUtils;
import jmona.impl.processing.LoggingProcessor;

/**
 * Count the number of objects of each class in an EvolutionContext.
 * 
 * @param <T>
 *          Classes of this type of object will be counted.
 * @param <E>
 *          The type of EvolutionContext to process.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ClassCountingProcessor<T, E extends PopulationEvolutionContext<T>>
    extends LoggingProcessor<T, E> {

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
   *           If there is a problem determining the classes of the individuals
   *           in the current population of the specified EvolutionContext.
   * @see jmona.impl.processing.PeriodicProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected String message(final E context) throws LoggingException {

    List<Class<?>> classes = null;
    try {
      classes = Functional.map(new ToClass<T>(), context.currentPopulation());
    } catch (final MappingException exception) {
      throw new LoggingException("Failed to determine classes of individuals.",
          exception);
    }

    return ListUtils.count(classes).toString();
  }

}
