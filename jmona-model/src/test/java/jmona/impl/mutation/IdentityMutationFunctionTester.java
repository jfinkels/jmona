/**
 * IdentityMutationFunctionTester.java
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
package jmona.impl.mutation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the IdentityMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class IdentityMutationFunctionTester {

  /**
   * Test method for
   * {@link jmona.impl.mutation.IdentityMutationFunction#mutate(java.lang.Object)}.
   */
  @Test
  public void testMutate() {
    final IdentityMutationFunction<Object> function = new IdentityMutationFunction<Object>();
    final Object object = new Object();
    final int beforeHashCode = object.hashCode();
    function.mutate(object);
    final int afterHashCode = object.hashCode();

    assertEquals(beforeHashCode, afterHashCode);
  }

}
