/**
 * DefaultAntTester.java
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

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class DefaultAntTester {

  /** The height of the trail. */
  public static final int HEIGHT = 2;
  private static final transient Logger LOG = Logger
      .getLogger(DefaultAntTester.class);
  /** The width of the trail. */
  public static final int WIDTH = 2;
  /** The DefaultAnt under test. */
  private DefaultAnt ant = null;
  /** The locations on the trail containing food. */
  private boolean[][] foodAt = null;

  /** The Trail on which the Ant lives. */
  private Trail trail = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.foodAt = new boolean[HEIGHT][WIDTH];

    this.foodAt[0][1] = true;
    this.foodAt[1][1] = true;
    this.foodAt[1][0] = true;

    this.trail = new Trail(this.foodAt);
    this.ant = new DefaultAnt(this.trail);
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#advance()}.
   */
  @Test
  public void testAdvance() {
    this.ant.advance();
    assertEquals(new CoordinatePair(1, 0), this.ant.location());

    this.ant.advance();
    assertEquals(new CoordinatePair(0, 0), this.ant.location());

    this.ant.turnRight();
    this.ant.advance();
    assertEquals(new CoordinatePair(0, 1), this.ant.location());

    this.ant.advance();
    assertEquals(new CoordinatePair(0, 0), this.ant.location());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#consumeFood()}.
   */
  @Test
  public void testConsumeFood() {
    this.foodAt[0][0] = true;
    this.trail = new Trail(this.foodAt);
    assertEquals(0, this.ant.foodEaten());
    this.ant.consumeFood();
    assertEquals(1, this.ant.foodEaten());
    this.ant.consumeFood();
    assertEquals(1, this.ant.foodEaten());
    LOG.debug("END OF TEST CONSUME FOOD");
    LOG.debug(" LOCATION " + this.ant.location());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.DefaultAnt#DefaultAnt(jmona.example.anttrail.Trail)}
   * .
   */
  @Test
  public void testDefaultAnt() {

  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#foodEaten()}.
   */
  @Test
  public void testFoodEaten() {
    assertEquals(0, this.ant.foodEaten());
    this.ant.advance();
    assertEquals(1, this.ant.foodEaten());
    this.ant.turnRight();
    this.ant.advance();
    assertEquals(2, this.ant.foodEaten());
    this.ant.turnRight();
    this.ant.advance();
    assertEquals(3, this.ant.foodEaten());
    this.ant.advance();
    assertEquals(3, this.ant.foodEaten());
    LOG.debug(" END OF TEST FOOD EATEN");
    LOG.debug("LOCATION: " + this.ant.location());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#isFoodAhead()}.
   */
  @Test
  public void testIsFoodAhead() {
    LOG.debug("IS FOOD AHEAD?");
    LOG.debug("location: " + this.ant.location());
    assertTrue(this.ant.isFoodAhead());
    this.ant.advance();
    assertFalse(this.ant.isFoodAhead());
    LOG.debug("/IS FOOD AHEAD?");
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#location()}.
   */
  @Test
  public void testLocation() {
    assertEquals(new CoordinatePair(0, 0), this.ant.location());
    this.ant.advance();
    assertEquals(new CoordinatePair(1, 0), this.ant.location());
    this.ant.advance();
    assertEquals(new CoordinatePair(0, 0), this.ant.location());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#maxSteps()}.
   */
  @Test
  public void testMaxSteps() {
    assertEquals(DefaultAnt.DEFAULT_MAX_STEPS, this.ant.maxSteps());
    final int newMaxSteps = 10;
    this.ant.setMaxSteps(newMaxSteps);
    assertEquals(newMaxSteps, this.ant.maxSteps());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#movesMade()}.
   */
  @Test
  public void testMovesMade() {
    assertEquals(0, this.ant.movesMade());
    this.ant.advance();
    assertEquals(1, this.ant.movesMade());
    this.ant.turnLeft();
    assertEquals(2, this.ant.movesMade());
    this.ant.turnRight();
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#orientation()}.
   */
  @Test
  public void testOrientation() {
    assertEquals(DefaultAnt.INITIAL_ORIENTATION, this.ant.orientation());
    this.ant.turnRight();
    assertEquals(DefaultAnt.INITIAL_ORIENTATION.rotatedRight(), this.ant
        .orientation());
    this.ant.turnLeft();
    this.ant.turnLeft();
    assertEquals(DefaultAnt.INITIAL_ORIENTATION.rotatedLeft(), this.ant
        .orientation());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#reset()}.
   */
  @Test
  public void testReset() {
    this.ant.advance();
    this.ant.turnLeft();

    assertEquals(1, this.ant.foodEaten());
    assertEquals(2, this.ant.movesMade());
    assertEquals(Orientation.north(), this.ant.orientation());
    assertEquals(new CoordinatePair(1, 0), this.ant.location());

    this.ant.reset();

    assertEquals(0, this.ant.foodEaten());
    assertEquals(0, this.ant.movesMade());
    assertEquals(Orientation.east(), this.ant.orientation());
    assertEquals(CoordinatePair.origin(), this.ant.location());

  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#setMaxSteps(int)}.
   */
  @Test
  public void testSetMaxSteps() {
    assertEquals(DefaultAnt.DEFAULT_MAX_STEPS, this.ant.maxSteps());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#turnLeft()}.
   */
  @Test
  public void testTurnLeft() {
    this.ant.turnLeft();
    assertEquals(Orientation.north(), this.ant.orientation());
    this.ant.turnLeft();
    assertEquals(Orientation.west(), this.ant.orientation());
    this.ant.turnLeft();
    assertEquals(Orientation.south(), this.ant.orientation());
    this.ant.turnLeft();
    assertEquals(Orientation.east(), this.ant.orientation());
  }

  /**
   * Test method for {@link jmona.example.anttrail.DefaultAnt#turnRight()}.
   */
  @Test
  public void testTurnRight() {
    this.ant.turnRight();
    assertEquals(Orientation.south(), this.ant.orientation());
    this.ant.turnRight();
    assertEquals(Orientation.west(), this.ant.orientation());
    this.ant.turnRight();
    assertEquals(Orientation.north(), this.ant.orientation());
    this.ant.turnRight();
    assertEquals(Orientation.east(), this.ant.orientation());
  }

}
