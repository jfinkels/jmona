/**
 * LoggingPostProcessor.java
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

import jmona.DeepCopyable;
import jmona.EvolutionContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * A PostProcessor which periodically logs information about an
 * EvolutionContext.
 * 
 * Set the logging level by setting the {@link #loggingLevel} property.
 * 
 * @param <T>
 *          The type of individual in the EvolutionContext about which to log
 *          information.
 * @author jfinkels
 */
public abstract class LoggingPostProcessor<T extends DeepCopyable<T>> extends
    PeriodicPostProcessor<T> {

  /** The default level at which to log information. */
  public static final Level DEFAULT_LOGGING_LEVEL = Level.INFO;
  /** The default format of the message to log. */
  public static final String DEFAULT_MESSAGE_FORMAT = "Generation %d: %s";
  /** The Logger for this class. */
  private final Logger logger = Logger.getLogger(this.getClass());
  /** The level at which to log information. */
  private Level loggingLevel = DEFAULT_LOGGING_LEVEL;

  /**
   * Log the specified message.
   * 
   * @param message
   *          The message to log.
   */
  protected void log(final String message) {
    this.log(message, null);
  }

  /**
   * Log the specified message and the specified throwable cause of the message.
   * 
   * @param message
   *          The message to log.
   * @param throwable
   *          The cause of the message.
   */
  protected void log(final String message, final Throwable throwable) {
    this.logger.log(this.loggingLevel, message, throwable);
  }

  /**
   * Get the message to log based on the specified EvolutionContext.
   * 
   * @param context
   *          The EvolutionContext from which to get information.
   * @return The message to log.
   */
  protected abstract String message(final EvolutionContext<T> context);

  /**
   * Log a message.
   * 
   * @param context
   *          The EvolutionContext containing the current population.
   * @see jmona.PostProcessor#process(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(final EvolutionContext<T> context) {
    this.log(String.format(DEFAULT_MESSAGE_FORMAT, context.currentGeneration(),
        this.message(context)));
  }

  /**
   * Set the level at which to log information.
   * 
   * @param newLoggingLevel
   *          The level at which to log information.
   */
  public void setLoggingLevel(final Level newLoggingLevel) {
    this.loggingLevel = newLoggingLevel;
  }
}
