/**
 * LineReader.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * A utility class which provides a method for reading all lines at once from a
 * file.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class LineReader {

  /**
   * Returns all lines from the specified File as a List of String objects.
   * 
   * @param file
   *          The file from which to read.
   * @return A List of each line of the file as a String.
   * @throws IOException
   *           If the specified file does not exist, or if there is a problem
   *           reading from the file.
   */
  public static List<String> readLines(final File file) throws IOException {
    final List<String> result = new Vector<String>();

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));

      String currentLine = reader.readLine();

      while (currentLine != null) {
        result.add(currentLine);
        currentLine = reader.readLine();
      }
    } finally {
      reader.close();
    }

    return result;
  }

  /** Instantiation disallowed, except by this class and subclasses. */
  protected LineReader() {
    // intentionally unimplemented
  }
}
