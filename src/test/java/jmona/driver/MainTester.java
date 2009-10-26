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
import jmona.EvolutionException;
import jmona.ProcessingException;

import org.junit.Test;
import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * Test class for the Main class.
 * 
 * @author jeff
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
  public static final String CONFIG_FILE_GOOD = "--config=src/test/resources/jmona/example/ones/OnesEvolutionContextTester-context.xml";

  /**
   * Print the stack trace of the specified Throwable cause of the failure, then
   * fail the current test.
   * 
   * @param cause
   *          The cause for the test failure.
   */
  protected static final void fail(final Throwable cause) {
    cause.printStackTrace(System.err);
    org.junit.Assert.fail(cause.getMessage());
  }

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])}.
   */
  @Test
  public void testMain() {
    try {
      Main.main(new String[] { CONFIG_FILE_BAD });
    } catch (final BeanDefinitionStoreException exception) {
      assertTrue(exception instanceof BeanDefinitionStoreException);
    } catch (final EvolutionException exception) {
      fail(exception);
    } catch (final ProcessingException exception) {
      fail(exception);
    }

    try {
      Main.main(new String[] { CONFIG_FILE_GOOD });
    } catch (final EvolutionException exception) {
      fail(exception);
    } catch (final ProcessingException exception) {
      fail(exception);
    }
  }
}
