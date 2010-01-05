/**
 * CoordinatePairTester.java
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import jmona.impl.Pair;

import org.junit.Test;

/**
 * Test class for the CoordinatePair class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CoordinatePairTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#add(jmona.example.anttrail.CoordinatePair)}
   * .
   */
  @Test
  public void testAdd() {
    final CoordinatePair pair = new CoordinatePair(1, 1);
    pair.add(new CoordinatePair(-1, 1));
    assertEquals(new CoordinatePair(0, 2), pair);
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#add(jmona.impl.Pair)}.
   */
  @Test
  public void testAddPairOfIntegerInteger() {
    final CoordinatePair pair = new CoordinatePair(0, 0);
    pair.add(new Pair<Integer, Integer>(1, 1));
    assertEquals(new CoordinatePair(1, 1), pair);
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#add(jmona.impl.Pair, jmona.example.anttrail.CoordinatePair)}
   * .
   */
  @Test
  public void testAddPairOfIntegerIntegerCoordinatePair() {
    final CoordinatePair pair = new CoordinatePair(1, 1);
    final CoordinatePair bounds = new CoordinatePair(2, 2);
    pair.add(new Pair<Integer, Integer>(-2, 2), bounds);
    assertEquals(new CoordinatePair(1, 1), pair);
    pair.add(new Pair<Integer, Integer>(1, 1), bounds);
    assertEquals(new CoordinatePair(0, 0), pair);
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#add(jmona.example.anttrail.CoordinatePair, jmona.example.anttrail.CoordinatePair)}
   * .
   */
  @Test
  public void testAddToroidal() {
    final CoordinatePair pair = new CoordinatePair(1, 1);
    final CoordinatePair bounds = new CoordinatePair(2, 2);

    pair.add(pair, bounds);

    assertEquals(new CoordinatePair(0, 0), pair);

    pair.add(new CoordinatePair(-1, -1), bounds);

    assertEquals(new CoordinatePair(1, 1), pair);

  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#CoordinatePair(java.lang.Integer, java.lang.Integer)}
   * .
   */
  @Test
  public void testCoordinatePair() {
    final CoordinatePair pair = new CoordinatePair(0, 1);
    assertEquals(0, pair.x());
    assertEquals(1, pair.y());
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final CoordinatePair pair = new CoordinatePair(1, 2);

    assertNotSame(pair.deepCopy(), pair);
    assertEquals(pair, pair.deepCopy());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    final CoordinatePair pair1 = new CoordinatePair(0, 0);
    final CoordinatePair pair2 = Orientation.east().toCoordinatePair();
    final CoordinatePair pair3 = Orientation.east().toCoordinatePair().sumWith(
        Orientation.south());
    final CoordinatePair pair4 = new CoordinatePair(1, 1);

    assertTrue(pair1.equals(pair1));
    assertTrue(pair2.equals(pair2));
    assertTrue(pair3.equals(pair3));
    assertTrue(pair4.equals(pair4));

    assertFalse(pair1.equals(pair2));
    assertFalse(pair1.equals(pair3));
    assertFalse(pair1.equals(pair4));

    assertFalse(pair2.equals(pair1));
    assertFalse(pair2.equals(pair3));
    assertFalse(pair2.equals(pair4));

    assertFalse(pair3.equals(pair1));
    assertFalse(pair3.equals(pair2));
    assertTrue(pair3.equals(pair4));

    assertFalse(pair4.equals(pair1));
    assertFalse(pair4.equals(pair2));
    assertTrue(pair4.equals(pair3));
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#hashCode()}.
   */
  @Test
  public void testHashCode() {
    final int x = 0xFFFF;
    final int y = 0x1111;

    final CoordinatePair pair = new CoordinatePair(x, y);

    final int hashcode = pair.hashCode();

    assertEquals(0xFFFF1111, hashcode);
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#origin()}.
   */
  @Test
  public void testOrigin() {
    assertEquals(new CoordinatePair(0, 0), CoordinatePair.origin());
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#rotateLeft()}.
   */
  @Test
  public void testRotateLeft() {
    final CoordinatePair pair = Orientation.north().toCoordinatePair();
    pair.rotateLeft();
    assertEquals(Orientation.west().toCoordinatePair(), pair);
    pair.rotateLeft();
    assertEquals(Orientation.south().toCoordinatePair(), pair);
    pair.rotateLeft();
    assertEquals(Orientation.east().toCoordinatePair(), pair);
    pair.rotateLeft();
    assertEquals(Orientation.north().toCoordinatePair(), pair);
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#rotateRight()}
   * .
   */
  @Test
  public void testRotateRight() {
    final CoordinatePair pair = Orientation.north().toCoordinatePair();
    pair.rotateRight();
    assertEquals(Orientation.east().toCoordinatePair(), pair);
    pair.rotateRight();
    assertEquals(Orientation.south().toCoordinatePair(), pair);
    pair.rotateRight();
    assertEquals(Orientation.west().toCoordinatePair(), pair);
    pair.rotateRight();
    assertEquals(Orientation.north().toCoordinatePair(), pair);
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#set(int, int)}
   * .
   */
  @Test
  public void testSet() {
    final CoordinatePair pair = new CoordinatePair(0, 0);
    pair.set(1, 1);

    assertEquals(new CoordinatePair(1, 1), pair);
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#setX(int)}.
   */
  @Test
  public void testSetX() {
    final CoordinatePair pair = new CoordinatePair(0, 0);
    pair.setX(1);

    assertEquals(1, pair.x());
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#setY(int)}.
   */
  @Test
  public void testSetY() {
    final CoordinatePair pair = new CoordinatePair(0, 0);
    pair.setY(1);

    assertEquals(1, pair.y());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#sumWith(jmona.example.anttrail.CoordinatePair)}
   * .
   */
  @Test
  public void testSumWith() {
    final CoordinatePair pair1 = new CoordinatePair(1, 1);
    final CoordinatePair pair2 = new CoordinatePair(-1, 1);

    assertEquals(new CoordinatePair(0, 2), pair1.sumWith(pair2));
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#sumWith(jmona.impl.Pair)}.
   */
  @Test
  public void testSumWithPairOfIntegerInteger() {
    final CoordinatePair pair = new CoordinatePair(0, 1);
    assertEquals(new CoordinatePair(1, 2), pair
        .sumWith(new Pair<Integer, Integer>(1, 1)));
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#sumWith(jmona.impl.Pair, jmona.example.anttrail.CoordinatePair)}
   * .
   */
  @Test
  public void testSumWithPairOfIntegerIntegerCoordinatePair() {
    final CoordinatePair pair = new CoordinatePair(1, 1);
    final CoordinatePair bounds = new CoordinatePair(2, 2);
    final Pair<Integer, Integer> toAdd = new Pair<Integer, Integer>(1, 1);
    assertEquals(new CoordinatePair(0, 0), pair.sumWith(toAdd, bounds));
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.CoordinatePair#toOrientation()}.
   */
  @Test
  public void testToOrientation() {
    final CoordinatePair pair = new CoordinatePair(1, 0);
    final Orientation orientation = pair.toOrientation();

    assertEquals(Orientation.east(), orientation);
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#x()}.
   */
  @Test
  public void testX() {
    final CoordinatePair pair = new CoordinatePair(0, 1);
    assertEquals(0, pair.x());
    assertEquals(1, pair.y());
  }

  /**
   * Test method for {@link jmona.example.anttrail.CoordinatePair#y()}.
   */
  @Test
  public void testY() {
    final CoordinatePair pair = new CoordinatePair(0, 1);
    assertEquals(0, pair.x());
    assertEquals(1, pair.y());
  }

}
