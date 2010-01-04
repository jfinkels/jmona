/**
 * AbstractNodeTester.java
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
package jmona.gp.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jmona.gp.impl.example.IntegerNode;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractNode class.
 * 
 * @author Jeffrey Finkelstein
 */
public class AbstractNodeTester {

  /** The Node under test. */
  private AbstractNode node = null;
  /** A parent Node for the Node under test. */
  private AbstractNode parent = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.node = new IntegerNode(1);
    this.parent = new IntegerNode(0);
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractNode#parent()}.
   */
  @Test
  public void testParent() {
    assertNull(this.node.parent());
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractNode#setParent(jmona.gp.Node)}
   * .
   */
  @Test
  public void testSetParent() {
    this.node.setParent(this.parent);
    assertSame(this.parent, this.node.parent());
  }

}
