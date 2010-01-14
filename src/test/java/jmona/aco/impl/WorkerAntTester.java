/**
 * WorkerAntTester.java
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
package jmona.aco.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.aco.AlreadyVisitedException;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the WorkerAnt class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class WorkerAntTester {

  /**
   * Test method for {@link jmona.aco.impl.WorkerAnt#WorkerAnt(int)}.
   */
  @Test
  public void testWorkerAnt() {
    final WorkerAnt ant = new WorkerAnt(0);
    assertEquals(0, ant.currentVertex().intValue());
  }

  /**
   * Test method for {@link jmona.aco.impl.WorkerAnt#memory()}.
   */
  @Test
  public void testMemory() {
    final WorkerAnt ant = new WorkerAnt(0);

    assertEquals(1, ant.memory().size());
    assertTrue(ant.memory().contains(0));

    ant.moveTo(1);
    assertEquals(2, ant.memory().size());
    assertTrue(ant.memory().contains(0));
    assertTrue(ant.memory().contains(1));

    ant.reset();
    assertEquals(1, ant.memory().size());
    assertTrue(ant.memory().contains(0));

  }

  /**
   * Test method for {@link jmona.aco.impl.WorkerAnt#currentVertex()}.
   */
  @Test
  public void testCurrentVertex() {
    final WorkerAnt ant = new WorkerAnt(0);
    assertEquals(0, ant.currentVertex().intValue());
    ant.moveTo(1);
    assertEquals(1, ant.currentVertex().intValue());
  }

  /**
   * Test method for {@link jmona.aco.impl.WorkerAnt#moveTo(java.lang.Integer)}.
   */
  @Test
  public void testMoveTo() {
    final WorkerAnt ant = new WorkerAnt(0);
    assertEquals(0, ant.currentVertex().intValue());
    ant.moveTo(1);
    assertEquals(1, ant.currentVertex().intValue());
    try {
      ant.moveTo(0);
      Util.shouldHaveThrownException();
    } catch (final AlreadyVisitedException exception) {
      assertEquals(2, ant.memory().size());
    }
  }

  /**
   * Test method for {@link jmona.aco.impl.WorkerAnt#reset()}.
   */
  @Test
  public void testReset() {
    final WorkerAnt ant = new WorkerAnt(0);

    assertEquals(1, ant.memory().size());
    assertTrue(ant.memory().contains(0));

    ant.reset();

    assertEquals(1, ant.memory().size());
    assertTrue(ant.memory().contains(0));

    ant.moveTo(1);

    assertEquals(2, ant.memory().size());
    assertTrue(ant.memory().contains(0));
    assertTrue(ant.memory().contains(1));

    ant.reset();

    assertEquals(1, ant.memory().size());
    assertTrue(ant.memory().contains(0));
  }

}
