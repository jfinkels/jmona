/**
 * OnesIndividualFactoryTester.java
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
package jmona.example.ones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jmona.InitializationException;
import jmona.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the OnesIndividualFactory class.
 * 
 * @author jeff
 */
public class OnesIndividualFactoryTester {

  /** The factory under test. */
  private OnesIndividualFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new OnesIndividualFactory();
  }

  /**
   * Test method for
   * {@link jmona.example.ones.OnesIndividualFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {
    try {
      this.factory.createIndividual();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.ones.OnesIndividualFactory#setLength(int)}.
   */
  @Test
  public void testSetLength() {
    OnesIndividual individual = null;
    try {
      individual = this.factory.createIndividual();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    assertEquals(OnesIndividualFactory.DEFAULT_LENGTH, individual.gene().length);

    final int newLength = 100;
    this.factory.setLength(newLength);
    try {
      individual = this.factory.createIndividual();
    } catch (final InitializationException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

    assertEquals(newLength, individual.gene().length);

  }

}
