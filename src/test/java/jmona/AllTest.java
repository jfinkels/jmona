/**
 * AllTest.java
 */
package jmona;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author jeff
 */
@RunWith(Suite.class)
@SuiteClasses( { BreedingExceptionTester.class, EvolutionExceptionTester.class,
    FitnessExceptionTester.class, InitializationExceptionTester.class,
    MutationExceptionTester.class })
public class AllTest {

}
