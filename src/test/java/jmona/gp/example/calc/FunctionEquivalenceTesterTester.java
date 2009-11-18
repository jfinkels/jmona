/**
 * FunctionEquivalenceTesterTester.java
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
package jmona.gp.example.calc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jmona.gp.EquivalenceTester;
import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.example.calc.operations.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the FunctionEquivalenceTester class.
 * 
 * @author jfinkels
 */
public class FunctionEquivalenceTesterTester {

  /** The EquivalenceTester under test in this class. */
  private EquivalenceTester<SingleInputFunction<Double, Double>, Double> equivalenceTester = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.equivalenceTester = new FunctionEquivalenceTester<Double, Double>();
  }

  /**
   * Test method for
   * {@link jmona.gp.example.calc.FunctionEquivalenceTester#areEquivalent(jmona.gp.example.calc.functions.SingleInputFunction, jmona.gp.example.calc.functions.SingleInputFunction, java.lang.Object)}
   * .
   */
  @Test
  public void testAreEquivalent() {
    assertFalse(this.equivalenceTester.areEquivalent(Util.ZERO_FUNCTION,
        Util.ONE_FUNCTION, 0.0));
    assertFalse(this.equivalenceTester.areEquivalent(Util.ZERO_FUNCTION,
        Util.ONE_FUNCTION, -1.0));
    assertFalse(this.equivalenceTester.areEquivalent(Util.ZERO_FUNCTION,
        Util.ONE_FUNCTION, 1.0));

    assertTrue(this.equivalenceTester.areEquivalent(Util.ONE_FUNCTION,
        Util.ONE_FUNCTION, 0.0));
    assertTrue(this.equivalenceTester.areEquivalent(Util.ONE_FUNCTION,
        Util.ONE_FUNCTION, -1.0));
    assertTrue(this.equivalenceTester.areEquivalent(Util.ONE_FUNCTION,
        Util.ONE_FUNCTION, 1.0));

    assertTrue(this.equivalenceTester.areEquivalent(Util.TWO_FUNCTION,
        Util.TWO_FUNCTION, 0.0));
    assertTrue(this.equivalenceTester.areEquivalent(Util.TWO_FUNCTION,
        Util.TWO_FUNCTION, -1.0));
    assertTrue(this.equivalenceTester.areEquivalent(Util.TWO_FUNCTION,
        Util.TWO_FUNCTION, 1.0));

    assertTrue(this.equivalenceTester.areEquivalent(Util.HALF_FUNCTION,
        Util.HALF_FUNCTION, 0.0));
    assertTrue(this.equivalenceTester.areEquivalent(Util.HALF_FUNCTION,
        Util.HALF_FUNCTION, -1.0));
    assertTrue(this.equivalenceTester.areEquivalent(Util.HALF_FUNCTION,
        Util.HALF_FUNCTION, 1.0));

  }
}
