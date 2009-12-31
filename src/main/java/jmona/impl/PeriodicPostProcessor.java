/**
 * PeriodicPostProcessor.java
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
import jmona.PostProcessor;
import jmona.ProcessingException;

/**
 * A PostProcessor which repeatedly performs some processing after a specified
 * number of generations only.
 * 
 * @param <T>
 *          The type of Individual in the EvolutionContext which this class
 *          processes.
 * @author Jeffrey Finkelstein
 */
public abstract class PeriodicPostProcessor<T extends DeepCopyable<T>>
    implements PostProcessor<T> {

  /**
   * The number of times that the {@link #process(EvolutionContext)} method has
   * been called.
   */
  private int counter = 0;
  /** The number of generations between processing. */
  private int period = 1;

  /**
   * If this method has not been called <code>period</code> number of times,
   * then do nothing. Otherwise call the
   * {@link #processAtInterval(EvolutionContext)} method.
   * 
   * @param evolutionContext
   *          {@inheritDoc}
   * @throws ProcessingException
   *           {@inheritDoc}
   * @see jmona.PostProcessor#process(jmona.EvolutionContext)
   */
  @Override
  public synchronized void process(final EvolutionContext<T> evolutionContext)
      throws ProcessingException {
    this.counter += 1;

    if (this.counter >= this.period) {
      this.processAtInterval(evolutionContext);
      this.counter = 0;
    }
  }

  /**
   * Concrete subclasses of this class should implement this method with the
   * processing that should be done after every <code>period</code> number of
   * steps.
   * 
   * @param evolutionContext
   *          The EvolutionContext to be processed.
   * @throws ProcessingException
   *           If there is a problem processing the EvolutionContext.
   */
  protected abstract void processAtInterval(
      final EvolutionContext<T> evolutionContext) throws ProcessingException;

  /**
   * Set the period at which to process.
   * 
   * @param newPeriod
   *          The period at which to process.
   */
  public synchronized void setPeriod(final int newPeriod) {
    this.period = newPeriod;
  }
}
