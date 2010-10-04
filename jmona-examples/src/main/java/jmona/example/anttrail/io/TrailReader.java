/**
 * TrailReader.java
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
package jmona.example.anttrail.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jfcommon.functional.IterableString;
import jmona.example.anttrail.Trail;
import jmona.io.LineReader;

/**
 * A utility class for reading a Trail from a File.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class TrailReader {

  /**
   * Get a Trail from the specified File.
   * 
   * A trail is assumed to be the same width and height as the number of lines
   * in the file. Any non-whitespace character in the file is considered to be a
   * location in the trail containing food.
   * 
   * @param file
   *          The file containing a representation of the Trail.
   * @return The Trail represented by the specified File.
   * @throws IOException
   *           If there is a problem reading from the specified File.
   */
  public static final Trail fromFile(final File file) throws IOException {

    // get all lines from the file
    final List<String> lines = LineReader.readLines(file);

    // get the width of the map
    final int width = lines.size();

    // assume the map is square
    final boolean[][] foodAt = new boolean[width][width];

    // iterate over each line in the file
    int row = 0;
    int column = 0;
    for (final String line : lines) {

      // set the current column to 0
      column = 0;

      // iterate over each character in the current line
      for (final char character : new IterableString(line)) {

        // food is anything except whitespace
        if (!Character.isSpaceChar(character)) {
          foodAt[row][column] = true;
        }

        // increment the column number
        column += 1;
      }

      // increment the row number
      row += 1;
    }

    return new Trail(foodAt);
  }

  /** Instantiation disallowed except by subclasses. */
  protected TrailReader() {
    // intentionally unimplemented
  }
}
