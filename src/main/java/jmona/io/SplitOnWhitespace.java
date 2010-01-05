/**
 * SplitLines.java
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
package jmona.io;

import jmona.Function;

/**
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class SplitOnWhitespace implements Function<String, String[]> {
  /** A regular expression matching any whitespace character. */
  public static final String WHITESPACE = "\\s+";


  /* (non-Javadoc)
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public String[] execute(final String input) {
    return input.trim().split(WHITESPACE);
  }

}
