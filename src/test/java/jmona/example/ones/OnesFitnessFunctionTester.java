/**
 * OnesFitnessFunctionTester.java
 */
package jmona.example.ones;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the OnesFitnessFunction class.
 * 
 * @author jeff
 */
public class OnesFitnessFunctionTester {

  /**
   * Test method for
   * {@link jmona.example.ones.OnesFitnessFunction#fitness(jmona.example.ones.OnesIndividual)}
   * .
   */
  @Test
  public void testFitness() {
    final OnesFitnessFunction function = new OnesFitnessFunction();
    OnesIndividual individual = new OnesIndividual(new short[] { 0, 1, 0, 1 });
    
    final double epsilon = 0;
    assertEquals(2, function.fitness(individual), epsilon);

    individual = new OnesIndividual(new short[] { 1, 0, 1, 0 });
    assertEquals(2, function.fitness(individual), epsilon);

    individual = new OnesIndividual(new short[] { 1, 1, 1, 1 });
    assertEquals(4, function.fitness(individual), epsilon);

    individual = new OnesIndividual(new short[] {});
    assertEquals(0, function.fitness(individual), epsilon);
  }

}
