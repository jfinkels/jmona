/**
 * Processor.java
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
package jmona;

/**
 * An object which performs some processing on an EvolutionContext.
 * 
 * @param <T>
 *          The type of individual in the EvolutionContext to process.
 * @param <E>
 *          The type of EvolutionContext which this class processes.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public interface Processor<T, E extends EvolutionContext<T>> {

  /**
   * Perform some action on the specified EvolutionContext.
   * 
   * @param evolutionContext
   *          The EvolutionContext to process.
   * @throws ProcessingException
   *           If there is a problem processing the specified EvolutionContext.
   */
  void process(final E evolutionContext) throws ProcessingException;
}
