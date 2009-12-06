/**
 * MainTester.java
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
package jmona.driver;

import static org.junit.Assert.assertTrue;
import jmona.test.Util;

import org.junit.Test;
import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * Test class for the Main class.
 * 
 * @author jfinkels
 */
public class MainTester {

  /** An option which is not recognized by the option parser in Main. */
  public static final String BAD_OPTION = "-hey";
  /** A bogus filename which is unlikely to be a real file. */
  public static final String CONFIG_FILE_BAD = "--config=/improbable/path/name/bOgUsFiLe.DoEs.N#t.3Xi5t";
  /**
   * A Spring XML configuration file containing an EvolutionContext and a
   * CompletionCriteria.
   */
  public static final String CONFIG_FILE_GOOD = "--config=src/test/resources/jmona/example/ga/ones/OnesEvolutionContextTester-context.xml";
  /**
   * A Spring XML configuration file containing too few of both EvolutionContext
   * and CompletionCriteria beans.
   */
  public static final String CONFIG_TOO_FEW_BOTH = "--config=src/test/resources/jmona/driver/TooFewBoth-context.xml";
  /**
   * A Spring XML configuration file containing too few CompletionCriteria
   * beans.
   */
  public static final String CONFIG_TOO_FEW_CC = "--config=src/test/resources/jmona/driver/TooFewCC-context.xml";
  /**
   * A Spring XML configuration file containing too few EvolutionContext beans.
   */
  public static final String CONFIG_TOO_FEW_EC = "--config=src/test/resources/jmona/driver/TooFewEC-context.xml";
  /**
   * A Spring XML configuration file containing too many EvolutionContext and
   * CompletionCriteria beans.
   */
  public static final String CONFIG_TOO_MANY_BOTH = "--config=src/test/resources/jmona/driver/TooManyBoth-context.xml";
  /**
   * A Spring XML configuration file containing too many CompletionCriteria
   * beans.
   */
  public static final String CONFIG_TOO_MANY_CC = "--config=src/test/resources/jmona/driver/TooManyCC-context.xml";
  /**
   * A Spring XML configuration file containing too many EvolutionContext beans.
   */
  public static final String CONFIG_TOO_MANY_EC = "--config=src/test/resources/jmona/driver/TooManyEC-context.xml";

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])} with
   * several PostProcessors.
   */
  @Test
  public void severalPostProcessors() {
    // not yet implemented
  }

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])}.
   */
  @Test
  public void testMain() {
    try {
      Main.main(new String[] { CONFIG_FILE_BAD });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final BeanDefinitionStoreException exception) {
      assertTrue(exception instanceof BeanDefinitionStoreException);
    } catch (final RuntimeException exception) {
      Util.fail(exception);
    }

    try {
      Main.main(new String[] { CONFIG_FILE_GOOD });
    } catch (final RuntimeException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])} when the
   * specified configuration file does not have the necessary beans.
   */
  @Test
  public void testTooFewBeans() {
    // too few EvolutionContext beans
    try {
      Main.main(new String[] { CONFIG_TOO_FEW_EC });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too few CompletionCriteria beans
    try {
      Main.main(new String[] { CONFIG_TOO_FEW_CC });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too few of both classes of beans
    try {
      Main.main(new String[] { CONFIG_TOO_FEW_BOTH });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }
  }

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])} when the
   * specified configuration file does has too many EvolutionContext and/or
   * CompletionCriteria beans.
   */
  @Test
  public void testTooManyBeans() {
    // too many CompletionCriteria beans
    try {
      Main.main(new String[] { CONFIG_TOO_MANY_CC });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too many EvolutionContext beans
    try {
      Main.main(new String[] { CONFIG_TOO_MANY_EC });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too many of both classes of beans
    try {
      Main.main(new String[] { CONFIG_TOO_MANY_BOTH });
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }
  }
}
