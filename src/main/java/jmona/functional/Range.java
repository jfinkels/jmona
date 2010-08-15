/**
 * Range.java
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
package jmona.functional;

/**
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class Range extends AbstractRange<Integer> {

  /**
   * @param terminalInteger
   */
  public Range(int terminalInteger) {
    super(terminalInteger);
  }

  /**
   * @param initialInteger
   * @param terminalInteger
   */
  public Range(int initialInteger, int terminalInteger) {
    super(initialInteger, terminalInteger);
  }

  /**
   * @param initialInteger
   * @param terminalInteger
   * @param incrementInteger
   */
  public Range(int initialInteger, int terminalInteger, int incrementInteger) {
    super(initialInteger, terminalInteger, incrementInteger);
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.functional.AbstractRange#getValue(int)
   */
  @Override
  protected Integer getValue(final int current) {
    return current;
  }

}
