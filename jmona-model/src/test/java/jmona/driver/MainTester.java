/**
 * MainTester.java
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
package jmona.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jfcommon.test.TestUtils;
import jmona.impl.example.ExampleProcessor;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Test class for the Main class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class MainTester {

  /** An option which is not recognized by the option parser in Main. */
  public static final String BAD_OPTION = "-hey";
  /** A bogus filename which is unlikely to be a real file. */
  public static final String CONFIG_FILE_BAD = "--config=/improbable/path/name/bOgUsFiLe.DoEs.N#t.3Xi5t";
  /**
   * A Spring XML configuration file containing an EvolutionContext and a
   * CompletionConditionn.
   */
  public static final String CONFIG_FILE_GOOD = "--config=src/test/resources/jmona/example/ones/xml/OnesEvolutionTester-context.xml";
  /**
   * A Spring XML configuration file containing too few of both EvolutionContext
   * andCompletionConditionon beans.
   */
  public static final String CONFIG_TOO_FEW_BOTH = "--config=src/test/resources/jmona/driver/TooFewBoth-context.xml";
  /**
   * A Spring XML configuration file containing too feCompletionConditionion
   * beans.
   */
  public static final String CONFIG_TOO_FEW_CC = "--config=src/test/resources/jmona/driver/TooFewCC-context.xml";
  /**
   * A Spring XML configuration file containing too few EvolutionContext beans.
   */
  public static final String CONFIG_TOO_FEW_EC = "--config=src/test/resources/jmona/driver/TooFewEC-context.xml";
  /**
   * A Spring XML configuration file containing too many EvolutionContext and
   * CompletionConditiontion beans.
   */
  public static final String CONFIG_TOO_MANY_BOTH = "--config=src/test/resources/jmona/driver/TooManyBoth-context.xml";
  /**
   * A Spring XML configuration file containing too mCompletionConditionition
   * beans.
   */
  public static final String CONFIG_TOO_MANY_CC = "--config=src/test/resources/jmona/driver/TooManyCC-context.xml";
  /**
   * A Spring XML configuration file containing too many EvolutionContext beans.
   */
  public static final String CONFIG_TOO_MANY_EC = "--config=src/test/resources/jmona/driver/TooManyEC-context.xml";

  /**
   * The path to the Spring XML configuration file containing multiple
   * Processors.
   */
  public static final String CONFIG_MULTIPLE_PROCESSORS_PATH = "src/test/resources/jmona/driver/MultipleProcessors-context.xml";

  /** A Spring XML configuration file containing multiple Processors. */
  public static final String CONFIG_MULTIPLE_PROCESSORS = "--config="
      + CONFIG_MULTIPLE_PROCESSORS_PATH;

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])} with
   * multiple Processors.
   */
  @Ignore
  @Test
  public void testMultipleProcessors() {
    Main.main(new String[] { CONFIG_MULTIPLE_PROCESSORS });
    // TODO how can we access objects in the application context created in
    // Main?
    final ApplicationContext context = new FileSystemXmlApplicationContext(
        CONFIG_MULTIPLE_PROCESSORS_PATH);
    final ExampleProcessor processor1 = context.getBean("processor1",
        ExampleProcessor.class);
    final ExampleProcessor processor2 = context.getBean("processor2",
        ExampleProcessor.class);
    final int generations = context.getBean("maxGenerations", Integer.class);
    assertEquals(generations, processor1.count());
    assertEquals(generations / 2, processor2.count());
  }

  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])}.
   */
  @Test
  public void testMain() {
    new Main();
    
    try {
      Main.main(new String[] { CONFIG_FILE_BAD });
      TestUtils.shouldHaveThrownException();
    } catch (final BeanDefinitionStoreException exception) {
      assertTrue(exception instanceof BeanDefinitionStoreException);
    } catch (final RuntimeException exception) {
      TestUtils.fail(exception);
    }
/*
    try {
      Main.main(new String[] { CONFIG_FILE_GOOD });
    } catch (final RuntimeException exception) {
      TestUtils.fail(exception);
    }*/
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
      TestUtils.shouldHaveThrownException();
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too many CompletionConditiondition beans
    try {
      Main.main(new String[] { CONFIG_TOO_FEW_CC });
      TestUtils.shouldHaveThrownException();
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too few of both classes of beans
    try {
      Main.main(new String[] { CONFIG_TOO_FEW_BOTH });
      TestUtils.shouldHaveThrownException();
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }
  }
  
  /**
   * Test method for {@link jmona.driver.Main#main(java.lang.String[])} when the
   * specified configuration file does has too many EvolutionContext
   * and/orCompletionConditionndition beans.
   */
  @Test
  public void testTooManyBeans() {
    // toCompletionConditionondition beans
    try {
      Main.main(new String[] { CONFIG_TOO_MANY_CC });
      TestUtils.shouldHaveThrownException();
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too many EvolutionContext beans
    try {
      Main.main(new String[] { CONFIG_TOO_MANY_EC });
      TestUtils.shouldHaveThrownException();
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }

    // too many of both classes of beans
    try {
      Main.main(new String[] { CONFIG_TOO_MANY_BOTH });
      TestUtils.shouldHaveThrownException();
    } catch (final RuntimeException exception) {
      assertTrue(exception instanceof RuntimeException);
    }
  }
}
