/**
 * AllTest.java
 */
package jmona.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author jeff
 */
@RunWith(Suite.class)
@SuiteClasses({ DefaultBreedingFunctionTester.class,
    DefaultEvolutionContextTester.class,
    MaxGenerationCompletionCriteriaTester.class })
public class AllTest {

}
