/**
 * SplitOnColon.java
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

import jfcommon.functional.Function;

/**
 * Splits a string on colon characters (similar to Python's built-in <a href
 * ="http://docs.python.org/library/stdtypes.html#str.split">string.split()</a>
 * method, when called with the argument ':').
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class SplitOnColon implements Function<String, String[]> {

  /**
   * Returns an array of words in the specified input String, split on colons.
   * 
   * @param input
   *          The String to split on colons.
   * @return The words of the specified input String split on colons.
   * @see jfcommon.functional.Function#execute(java.lang.Object)
   */
  @Override
  public String[] execute(final String input) {
    return input.trim().split(":");
  }

}
