/**
 * AllTest.java
 */
package jmona.example.ones;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author jeff
 */
@RunWith(Suite.class)
@SuiteClasses({ OnesCompletionCriteriaTester.class,
    OnesCrossoverFunctionTester.class, OnesFitnessFunctionTester.class,
    OnesEvolutionContextTester.class, OnesMutatorFunctionTester.class,
    OnesSelectionFunctionTester.class })
public class AllTest {

}
