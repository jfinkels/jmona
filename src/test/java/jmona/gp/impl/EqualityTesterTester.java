/**
 * EqualityTesterTester.java
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the EqualityTester class.
 * 
 * @author jfinkels
 */
public class EqualityTesterTester {

  /**
   * Test method for
   * {@link jmona.gp.impl.EqualityTester#areEquivalent(java.lang.Object, java.lang.Object, java.lang.Object)}
   * .
   */
  @Test
  public void testAreEquivalent() {
    final EqualityTester<Object> tester = new EqualityTester<Object>();
    final Object input = new Object();
    final Object object1 = new Object();
    final Object object2 = new Object();
    final Object object3 = object1;
    
    assertTrue(tester.areEquivalent(object1, object1, input));
    assertTrue(tester.areEquivalent(object2, object2, input));
    assertTrue(tester.areEquivalent(object3, object3, input));
    
    assertTrue(tester.areEquivalent(object1, object3, input));
    assertTrue(tester.areEquivalent(object3, object1, input));
    
    assertFalse(tester.areEquivalent(object1, object2, input));
    assertFalse(tester.areEquivalent(object3, object2, input));
    assertFalse(tester.areEquivalent(object2, object1, input));
    assertFalse(tester.areEquivalent(object2, object3, input));
  }

}
