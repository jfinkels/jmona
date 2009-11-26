/**
 * SecondOrderFunctionTester.java
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
package jmona.example.gp.calc.functions;

import static org.junit.Assert.assertSame;
import jmona.example.gp.calc.functions.SecondOrderFunction;
import jmona.example.gp.calc.operations.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the SecondOrderFunction class.
 * 
 * @author jfinkels
 */
public class SecondOrderFunctionTester {

  /** The function under test. */
  private SecondOrderFunction function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new SecondOrderFunction(Util.ZERO_FUNCTION,
        Util.ONE_FUNCTION) {
      @Override
      public Double execute(final Double input) {
        return null;
      }
    };
  }

  /**
   * Test method for
   * {@link jmona.example.gp.calc.functions.SecondOrderFunction#function1()}.
   */
  @Test
  public void testFunction1() {
    assertSame(Util.ZERO_FUNCTION, this.function.function1());
  }

  /**
   * Test method for
   * {@link jmona.example.gp.calc.functions.SecondOrderFunction#function2()}.
   */
  @Test
  public void testFunction2() {
    assertSame(Util.ONE_FUNCTION, this.function.function2());
  }

}
