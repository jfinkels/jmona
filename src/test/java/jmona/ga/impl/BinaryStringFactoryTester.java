/**
 * BinaryStringFactoryTester.java
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
package jmona.ga.impl;

import jmona.DeepCopyableList;
import jmona.impl.mutable.MutableByte;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BinaryStringFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BinaryStringFactoryTester {

  /** The factory under test. */
  private BinaryStringFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new BinaryStringFactory(20);
  }

  /**
   * Test method for {@link jmona.ga.impl.BinaryStringFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    final DeepCopyableList<MutableByte> individual = this.factory.createObject();
    // TODO make assertions about this binary string
  }

}
