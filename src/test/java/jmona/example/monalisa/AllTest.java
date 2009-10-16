/**
 * AllTest.java
 */
package jmona.example.monalisa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author jeff
 */
@RunWith(Suite.class)
@SuiteClasses({ MonaCrossoverFunctionTester.class, 
    MonaEvolutionContextTester.class, MonaFitnessFunctionTester.class,
    MonaIndividualFactoryTester.class, MonaIndividualTester.class,
    MonaMutatorFunctionTester.class })
public class AllTest {

}
