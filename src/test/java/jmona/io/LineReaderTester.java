/**
 * LineReaderTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the LineReader class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class LineReaderTester {
  /** The location of the file containing the lines to read. */
  public static final String FILENAME = "src/test/resources/jmona/io/testfile.txt";
  /** The file containing the lines to read. */
  public static final File TESTFILE = new File(FILENAME);

  /**
   * Test method for {@link jmona.io.LineReader#readLines(java.io.File)}.
   */
  @Test
  public void testReadLines() {
    List<String> result = null;
    try {
      result = LineReader.readLines(TESTFILE);
    } catch (final IOException exception) {
      Util.fail(exception);
    }

    assertEquals("Hello, world!", result.get(0));
    assertEquals("My name is HAL.", result.get(1));
    assertTrue(result.get(2).isEmpty());
    assertEquals("What's yours?", result.get(3));

  }

  /**
   * Test method for {@link jmona.io.LineReader#LineReader()}.
   */
  @Test
  public void testLineReader() {
    new LineReader();
  }

}
