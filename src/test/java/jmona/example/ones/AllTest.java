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
    OnesCrossoverFunctionTester.class, OnesEvolutionContextTester.class,
    OnesFitnessFunctionTester.class, OnesMutatorFunctionTester.class, })
public class AllTest {

}
