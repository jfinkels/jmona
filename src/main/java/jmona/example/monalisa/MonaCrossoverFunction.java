/**
 * MonaCrossoverFunction.java
 */
package jmona.example.monalisa;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Map.Entry;

import jmona.CrossoverFunction;
import jmona.Pair;
import jmona.impl.Util;

/**
 * A crossover function which swaps a random polygon/color pair from two parents
 * to produce two offspring.
 * 
 * @author jeff
 */
public class MonaCrossoverFunction implements CrossoverFunction<MonaIndividual> {

  /**
   * Swap a random polygon/color pair from the parents to produce the children.
   * 
   * @param parents
   *          The individual to cross over a random polygon/color pair.
   * @return A copy of the parents with a random polygon/color pair swapped.
   * @see jmona.CrossoverFunction#crossover(jmona.Pair)
   */
  @Override
  public Pair<MonaIndividual, MonaIndividual> crossover(
      final Pair<MonaIndividual, MonaIndividual> parents) {

    // get the parents
    final MonaIndividual leftParent = parents.left();
    final MonaIndividual rightParent = parents.right();

    // copy the parents to get the children
    final MonaIndividual leftChild = leftParent.copy();
    final MonaIndividual rightChild = rightParent.copy();

    // get a random polygon/color pair from the left child
    int counter = Util.RANDOM.nextInt(leftChild.gene().size());
    Polygon leftPolygon = null;
    Color leftColor = null;
    for (final Entry<Polygon, Color> entry : leftChild.gene().entrySet()) {
      if (counter == 0) {
        leftPolygon = entry.getKey();
      } else {
        counter -= 1;
      }
    }
    leftColor = leftChild.gene().remove(leftPolygon);

    // get a random polygon/color pair from the right child
    counter = Util.RANDOM.nextInt(rightChild.gene().size());
    Polygon rightPolygon = null;
    Color rightColor = null;
    for (final Entry<Polygon, Color> entry : rightChild.gene().entrySet()) {
      if (counter == 0) {
        rightPolygon = entry.getKey();
      } else {
        counter -= 1;
      }
    }
    rightColor = rightChild.gene().remove(rightPolygon);

    // swap the polygons/colors
    leftChild.gene().put(rightPolygon, rightColor);
    rightChild.gene().put(leftPolygon, leftColor);

    return new Pair<MonaIndividual, MonaIndividual>(leftChild, rightChild);
  }

}
