/**
 * OnesMutatorFunctionTester.java
 */
package jmona.example.ones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jmona.MutationException;

import org.junit.Test;

/**
 * Test class for the OnesMutatorFunction class.
 * 
 * @author jeff
 */
public class OnesMutatorFunctionTester {

  /** The length of the genes of the individuals to test. */
  public static final int GENE_LENGTH = 100;
  
  /**
   * Test method for
   * {@link jmona.example.ones.OnesMutatorFunction#mutate(jmona.example.ones.OnesIndividual)}
   * .
   */
  @Test
  public void testMutate() {
    // create the mutator function
    final OnesMutatorFunction function = new OnesMutatorFunction();

    // initialize an array as a gene for an individual
    final short[] array = new short[GENE_LENGTH];
    for (int i = 0; i < GENE_LENGTH; ++i) {
      array[i] = 0;
    }

    // create an individual with a gene
    final OnesIndividual individual = new OnesIndividual(array);

    // mutate the individual's gene
    try {
      function.mutate(individual);
    } catch (final MutationException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

    final double prob = OnesMutatorFunction.PROB_BITWISE_MUTATION;
    final double expectedMutations = prob * individual.gene().length;
    final double epsilon = expectedMutations * .75;

    double actualMutations = 0;
    for (int i = 0; i < GENE_LENGTH; ++i) {
      if (individual.gene()[i] == 1) {
        actualMutations += 1;
      }
    }

    // TODO use standard deviation or something more official for epsilon
    assertEquals(expectedMutations, actualMutations, epsilon);
  }

}
