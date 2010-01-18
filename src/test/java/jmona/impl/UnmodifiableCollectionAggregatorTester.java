/**
 * UnmodifiableCollectionAggregatorTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

/**
 * Test class for the UnmodifiableCollectionAggregator class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class UnmodifiableCollectionAggregatorTester {

  /**
   * Test method for
   * {@link jmona.impl.UnmodifiableCollectionAggregator#AbstractAggregator(java.util.Collection)}
   * .
   */
  @Test
  public void testUnmodifiableCollectionAggregator() {
    final List<Object> list = new Vector<Object>();
    list.add(new Object());
    list.add(new Object());
    list.add(new Object());
    final UnmodifiableCollectionAggregator<Object> aggregator = new UnmodifiableCollectionAggregator<Object>(
        list);

    assertEquals(list.size(), aggregator.collection().size());

    int i = 0;
    for (final Object object : aggregator.collection()) {
      assertSame(list.get(i), object);
      i += 1;
    }
  }

}
