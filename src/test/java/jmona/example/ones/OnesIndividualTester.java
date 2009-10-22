/**
 * OnesIndividualTester.java
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
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * Test class for the OnesIndividual class.
 * 
 * @author jeff
 */
public class OnesIndividualTester {

  /**
   * Test method for {@link jmona.example.ones.OnesIndividual#copy()}.
   */
  @Test
  public void testCopy() {
    final short[] array = new short[]{1, 2, 3};
    final OnesIndividual individual = new OnesIndividual(array);
    final OnesIndividual clone = individual.copy();
    assertNotSame(clone, individual);
    assertNotSame(array, clone.gene());
    assertEquals(array[0], clone.gene()[0]);
    assertEquals(array[1], clone.gene()[1]);
    assertEquals(array[2], clone.gene()[2]);
  }

}
