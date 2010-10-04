/**
 * TrailReaderTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import jfcommon.test.TestUtils;
import jmona.example.anttrail.CoordinatePair;
import jmona.example.anttrail.Trail;

import org.junit.Test;

/**
 * Test class for the TrailReader class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class TrailReaderTester {

  /** The location of the file to read. */
  public static final String FILENAME = "src/test/resources/jmona/example/anttrail/trails/example.trail";
  /** The file to read. */
  public static final File TESTFILE = new File(FILENAME);

  /**
   * Test method for
   * {@link jmona.example.anttrail.io.TrailReader#fromFile(java.io.File)}.
   */
  @Test
  public void testFromFile() {
    Trail trail = null;
    try {
      trail = TrailReader.fromFile(TESTFILE);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(new CoordinatePair(2, 2), trail.bounds());
    assertFalse(trail.foodAt(new CoordinatePair(0, 0)));
    assertTrue(trail.foodAt(new CoordinatePair(0, 1)));
    assertTrue(trail.foodAt(new CoordinatePair(1, 0)));
    assertTrue(trail.foodAt(new CoordinatePair(1, 1)));
    
    new TrailReader();
  }

}
