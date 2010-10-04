/**
 * BaseDefaultTreeFactoryTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.gp.impl;

import static org.junit.Assert.assertTrue;
import jfcommon.test.TestUtils;
import jmona.InitializationException;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleTreeFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BaseDefaultTreeFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BaseDefaultTreeFactoryTester {
  /** The depth of a created tree. */
  public static final int DEPTH = 3;
  /** The number of times to create an Individual. */
  public static final int NUM_TESTS = 100;
  /** The AbstractTreeFactory under test. */
  private AbstractTreeFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new ExampleTreeFactory();
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractTreeFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    try {

      Tree individual = null;
      for (int i = 0; i < NUM_TESTS; ++i) {
        individual = this.factory.createObject();

        assertTrue(individual instanceof DefaultTree);
      }

    } catch (final InitializationException exception) {
      TestUtils.fail(exception);
    }
  }

}
