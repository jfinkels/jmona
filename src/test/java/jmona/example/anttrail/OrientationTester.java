/**
 * OrientationTester.java
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
package jmona.example.anttrail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the Orientation class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class OrientationTester {

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final Orientation clone = Orientation.east().deepCopy();
    assertEquals(Orientation.east().left(), clone.left());
    assertEquals(Orientation.east().right(), clone.right());
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#east()}.
   */
  @Test
  public void testEast() {
    assertEquals(new Orientation((short) 1, (short) 0), Orientation.east());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.Orientation#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertEquals(Orientation.east(), new Orientation((short) 1, (short) 0));
    assertEquals(new Orientation((short) 1, (short) 1), new Orientation(
        (short) 1, (short) 1));
    assertFalse(Orientation.north().equals(Orientation.south()));
    assertFalse(new Orientation((short) 1, (short) 0).equals(new Orientation(
        (short) 1, (short) 1)));
    assertFalse(new Orientation((short) 0, (short) 1).equals(new Orientation(
        (short) 1, (short) 1)));
    assertFalse(new Orientation((short) 0, (short) 0).equals(new Object()));
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#hashCode()}.
   */
  @Test
  public void testHashCode() {
    final short x = 0x7F7F;
    final short y = 0x1111;

    final Orientation pair = new Orientation(x, y);

    final int hashcode = pair.hashCode();

    assertEquals(0x7F7F1111, hashcode);
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#north()}.
   */
  @Test
  public void testNorth() {
    assertEquals(new Orientation((short) 0, (short) -1), Orientation.north());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.Orientation#Orientation(Short, Short)}.
   */
  @Test
  public void testOrientation() {
    final Orientation pair = new Orientation((short) 0, (short) 1);
    assertEquals(0, pair.left().intValue());
    assertEquals(1, pair.right().intValue());
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#rotatedLeft()}.
   */
  @Test
  public void testRotatedLeft() {
    assertEquals(Orientation.west(), Orientation.north().rotatedLeft());
    assertEquals(Orientation.south(), Orientation.west().rotatedLeft());
    assertEquals(Orientation.east(), Orientation.south().rotatedLeft());
    assertEquals(Orientation.north(), Orientation.east().rotatedLeft());
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#rotatedRight()}.
   */
  @Test
  public void testRotatedRight() {
    assertEquals(Orientation.east(), Orientation.north().rotatedRight());
    assertEquals(Orientation.south(), Orientation.east().rotatedRight());
    assertEquals(Orientation.west(), Orientation.south().rotatedRight());
    assertEquals(Orientation.north(), Orientation.west().rotatedRight());
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#setLeft(Short)}.
   */
  @Test
  public void testSetLeftInteger() {
    final Orientation orientation = Orientation.east();
    try {
      orientation.setLeft((short) 0);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#setRight(Short)}.
   */
  @Test
  public void testSetRightInteger() {
    final Orientation orientation = Orientation.east();
    try {
      orientation.setRight((short) 0);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#south()}.
   */
  @Test
  public void testSouth() {
    assertEquals(new Orientation((short) 0, (short) 1), Orientation.south());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.Orientation#toCoordinatePair()}.
   */
  @Test
  public void testToCoordinatePair() {
    final CoordinatePair pair = Orientation.east().toCoordinatePair();
    assertTrue(pair instanceof CoordinatePair);
    assertEquals(1, pair.x());
    assertEquals(0, pair.y());
  }

  /**
   * Test method for {@link jmona.example.anttrail.Orientation#west()}.
   */
  @Test
  public void testWest() {
    assertEquals(new Orientation((short) -1, (short) 0), Orientation.west());
  }

}
