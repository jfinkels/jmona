/**
 * ExampleStrategy.java
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
package jmona.game.impl.example;

import jmona.DeepCopyable;
import jmona.game.Strategy;
import jmona.random.RandomUtils;

/**
 * An example strategy with a score property.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ExampleStrategy implements Strategy, DeepCopyable<ExampleStrategy> {

  /** The score of this strategy. */
  private final double score;

  /** Instantiate this Strategy with a random score between 0 and 1. */
  public ExampleStrategy() {
    this(RandomUtils.nextDouble());
  }

  /**
   * Instantiate this Strategy with a score.
   * 
   * @param initialScore
   *          The score of this Strategy.
   */
  public ExampleStrategy(final double initialScore) {
    this.score = initialScore;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public ExampleStrategy deepCopy() {
    return new ExampleStrategy(this.score);
  }

  /**
   * {@inheritDoc}
   * 
   * @see jmona.game.Strategy#reset()
   */
  @Override
  public void reset() {
    // intentionally unimplemented
  }

  /**
   * Get the score of this Strategy.
   * 
   * @return The score of this Strategy.
   */
  public double score() {
    return this.score;
  }

}
