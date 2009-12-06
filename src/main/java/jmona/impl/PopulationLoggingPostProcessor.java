/**
 * OnesPostProcessor.java
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
package jmona.impl;

import jmona.EvolutionContext;
import jmona.Individual;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * A PostProcessor which simply logs the current population.
 * 
 * Set the logging level by setting the {@link #loggingLevel} property.
 * 
 * @param <T>
 *          The type of Individual in the EvolutionContext whose population will
 *          be logged.
 * @author jfinkels
 */
public class PopulationLoggingPostProcessor<T extends Individual> extends
    PeriodicPostProcessor<T> {

  /**
   * The default level at which to log the population from the EvolutionContext.
   */
  public static final Level DEFAULT_LOGGING_LEVEL = Level.INFO;
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(PopulationLoggingPostProcessor.class);
  /**
   * The level at which to log the population from the EvolutionContext.
   */
  private Level loggingLevel = DEFAULT_LOGGING_LEVEL;

  /**
   * Output the current population.
   * 
   * @param evolutionContext
   *          The EvolutionContext containing the current population.
   * @see jmona.PostProcessor#process(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(final EvolutionContext<T> evolutionContext) {
    LOG.log(this.loggingLevel, "Generation "
        + evolutionContext.currentGeneration() + ": "
        + evolutionContext.currentPopulation());
  }

  /**
   * Set the level at which to log the current population from the
   * EvolutionContext.
   * 
   * @param newLoggingLevel
   *          The level at which to log the current population from the
   *          EvolutionContext.
   */
  public void setLoggingLevel(final Level newLoggingLevel) {
    this.loggingLevel = newLoggingLevel;
  }

}
