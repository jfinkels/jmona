/**
 * RandomUtils.java
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
package jmona.random;

import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;

/**
 * Utilities for random number generation.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public final class RandomUtils {

  /**
   * A RandomData object which has access to a (not cryptographically secure)
   * RandomNumberGenerator.
   */
  public static final RandomData RANDOM = new RandomDataImpl();

  /** Instantiation disallowed except by subclasses. */
  protected RandomUtils() {
    // intentionally unimplemented
  }
}
