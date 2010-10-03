/**
 * SplitOnColonTester.java
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

import static org.junit.Assert.assertArrayEquals;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * Test class for the SplitOnColon class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class SplitOnColonTester {

  /** A String. */
  public static final String PIECE1 = "Hello,";
  /** A String. */
  public static final String PIECE2 = "zombie";
  /** A String. */
  public static final String PIECE3 = "Shakespeare.";
  /** An array of strings to join with colon. */
  public static final String[] PIECES = new String[] { PIECE1, PIECE2, PIECE3 };

  /**
   * Test method for {@link jmona.io.SplitOnColon#execute(java.lang.String)}.
   */
  @Test
  public void testExecute() {
    final String string = StringUtils.join(PIECES, ':');
    assertArrayEquals(PIECES, new SplitOnColon().execute(string));

  }

}
