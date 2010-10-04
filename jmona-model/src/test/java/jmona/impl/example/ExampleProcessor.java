/**
 * ExampleProcessor.java
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
package jmona.impl.example;

import jmona.EvolutionContext;
import jmona.impl.processing.PeriodicProcessor;

/**
 * A Processor which simply increments a counter.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ExampleProcessor extends
    PeriodicProcessor<ExampleIndividual, ExampleEvolutionContext> {

  /**
   * The number of times {@link #processAtInterval(ExampleEvolutionContext)} has
   * been called.
   */
  private int count = 0;

  /**
   * Gets the number of times
   * {@link #processAtInterval(ExampleEvolutionContext)} has been called.
   * 
   * @return The number of times {@link #processAtInterval(EvolutionContext)}
   *         has been called.
   */
  public int count() {
    return this.count;
  }

  /**
   * Increment the counter property of this object.
   * 
   * @param evolutionContext
   *          This parameter is ignored.
   * @see jmona.impl.processing.PeriodicProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(
      final ExampleEvolutionContext evolutionContext) {
    this.count += 1;
  }

}
