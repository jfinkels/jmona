/**
 * AbstractTerminalNodeTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jmona.CopyingException;
import jmona.gp.Node;

import org.junit.Test;

/**
 * Test class for the AbstractTerminalNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class AbstractTerminalNodeTester {

  /**
   * Test method for {@link jmona.gp.impl.AbstractTerminalNode#arity()} and
   * {@link jmona.gp.impl.AbstractTerminalNode#children()}.
   */
  @Test
  public void testArity() {
    final AbstractTerminalNode node = new AbstractTerminalNode() {
      @Override
      public Node deepCopy() throws CopyingException {
        return null;
      }
    };
    assertEquals(AbstractTerminalNode.ARITY, node.arity());

    assertNull(node.children());
  }

}
