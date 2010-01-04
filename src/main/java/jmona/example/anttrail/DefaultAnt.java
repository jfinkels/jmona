/**
 * DefaultAnt.java
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

import java.util.HashSet;
import java.util.Set;

/**
 * An DefaultAnt which can move on a Trail and consume food.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class DefaultAnt implements Ant {
  /** The default maximum number of steps allowed for this Ant. */
  public static final int DEFAULT_MAX_STEPS = 200;
  /** The initial location of this Ant. */
  public static final CoordinatePair INITIAL_LOCATION = CoordinatePair.origin();
  /** The initial orientation of this Ant. */
  public static final Orientation INITIAL_ORIENTATION = Orientation.east();
  /** The Set of coordinates at which this Ant has eaten food. */
  private final Set<CoordinatePair> foodEaten = new HashSet<CoordinatePair>();
  /** The initial location of the Ant on the Trail. */
  private CoordinatePair location = null;
  /** The maximum number of steps allowed for this Ant. */
  private int maxSteps = DEFAULT_MAX_STEPS;
  /**
   * The total number of moves made by this Ant, including moves forward and
   * turns left and right.
   */
  private int movesMade = 0;
  /** The initial orientation of the Ant on the Trail. */
  private Orientation orientation = null;
  /** The Trail with which this Ant interacts. */
  private final Trail trail;

  /**
   * Instantiate this Ant with access to the specified Trail.
   * 
   * @param initialTrail
   *          The Trail with which this Ant interacts.
   */
  public DefaultAnt(final Trail initialTrail) {
    this.trail = initialTrail;
    this.location = INITIAL_LOCATION.deepCopy();
    this.orientation = INITIAL_ORIENTATION.deepCopy();
  }

  /**
   * {@inheritDoc}
   * 
   * @see jmona.example.anttrail.Ant#advance()
   */
  @Override
  public void advance() {
    this.location.add(this.orientation, this.trail.bounds());
    this.consumeFood();
    this.movesMade += 1;
  }

  /**
   * Consume food, <em>if it exists</em>, at this Ant's current location on the
   * Trail.
   */
  protected void consumeFood() {
    if (this.trail.foodAt(this.location)) {
      this.foodEaten.add(this.location.deepCopy());
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.Ant#foodEaten()
   */
  @Override
  public int foodEaten() {
    return this.foodEaten.size();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.Ant#isFoodAhead()
   */
  @Override
  public boolean isFoodAhead() {
    return this.trail.foodAt(this.location.sumWith(this.orientation, this.trail
        .bounds()));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.Ant#location()
   */
  @Override
  public CoordinatePair location() {
    return this.location;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.Ant#maxSteps()
   */
  @Override
  public int maxSteps() {
    return this.maxSteps;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.Ant#movesMade()
   */
  @Override
  public int movesMade() {
    return this.movesMade;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.example.anttrail.Ant#orientation()
   */
  @Override
  public Orientation orientation() {
    return this.orientation;
  }

  /**
   * {@inheritDoc}
   * 
   * @see jmona.example.anttrail.Ant#reset()
   */
  @Override
  public void reset() {
    this.foodEaten.clear();
    this.movesMade = 0;
    this.orientation = INITIAL_ORIENTATION.deepCopy();
    this.location = INITIAL_LOCATION.deepCopy();
  }

  /**
   * {@inheritDoc}
   * 
   * @param newMaxSteps
   *          {@inheritDoc}
   * @see jmona.example.anttrail.Ant#setMaxSteps(int)
   */
  @Override
  public void setMaxSteps(final int newMaxSteps) {
    this.maxSteps = newMaxSteps;
  }

  /**
   * {@inheritDoc}
   * 
   * @see jmona.example.anttrail.Ant#turnLeft()
   */
  @Override
  public void turnLeft() {
    this.orientation = this.orientation.rotatedLeft();
    this.movesMade += 1;
  }

  /**
   * {@inheritDoc}
   * 
   * @see jmona.example.anttrail.Ant#turnRight()
   */
  @Override
  public void turnRight() {
    this.orientation = this.orientation.rotatedRight();
    this.movesMade += 1;
  }

}
