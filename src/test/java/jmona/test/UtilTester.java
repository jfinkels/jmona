/**
 * UtilTester.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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
package jmona.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.InitializationException;
import jmona.gp.Tree;
import jmona.gp.TreeFactory;
import jmona.gp.impl.AbstractTreeFactory;
import jmona.gp.impl.example.ExampleTreeFactory;

import org.junit.Test;

/**
 * Test class for the testing utility class.
 * 
 * @author jfinkels
 */
public class UtilTester {

  /**
   * Test method for {@link jmona.test.Util#fail(java.lang.Throwable)}.
   */
  @Test
  public void testFail() {
    try {
      Util.fail(new Exception());
    } catch (final AssertionError error) {
      assertTrue(error instanceof AssertionError);
    }
  }

  /**
   * Test method for {@link jmona.test.Util#countNodes(jmona.gp.Tree)}.
   */
  @Test
  public void testCountNodes() {
    final TreeFactory<Integer> factory = new ExampleTreeFactory();

    Tree<Integer> tree = null;
    try {
      tree = factory.createIndividual();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    final double epsilon = 0;
    assertEquals(Math.pow(2, AbstractTreeFactory.DEFAULT_MAX_DEPTH) - 1,
        Util.countNodes(tree), epsilon);
  }

}
