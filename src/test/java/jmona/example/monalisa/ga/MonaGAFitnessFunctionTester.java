/**
 * MonaGAFitnessFunctionTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.example.monalisa.ga;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jfcommon.test.TestUtils;
import jmona.FitnessException;
import jmona.example.monalisa.ColoredPolygon;

import org.junit.Test;

/**
 * Test class for the MonaGAFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MonaGAFitnessFunctionTester {

  /** The image against which to compare Lists of ColoredPolygon objects. */
  public static final String TEST_IMAGE = "src/test/resources/jmona/example/monalisa/images/purple-red.png";

  /**
   * Test method for
   * {@link jmona.example.monalisa.ga.MonaGAFitnessFunction#MonaGAFitnessFunction(java.awt.image.BufferedImage)}
   * .
   */
  @Test
  public void testMonaGAFitnessFunction() {

    // create the target image
    BufferedImage targetImage = null;
    try {
      targetImage = ImageIO.read(new File(TEST_IMAGE));
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }

    // create the fitness function
    try {
      new MonaGAFitnessFunction(targetImage);
    } catch (final InterruptedException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ga.MonaGAFitnessFunction#rawFitness(Iterable)}
   * .
   */
  @Test
  public void testRawFitness() {

    // create the target image
    BufferedImage targetImage = null;
    try {
      targetImage = ImageIO.read(new File(TEST_IMAGE));
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }

    // create the fitness function
    MonaGAFitnessFunction function = null;
    try {
      function = new MonaGAFitnessFunction(targetImage);
    } catch (final InterruptedException exception) {
      TestUtils.fail(exception);
    }

    // create an example individual
    final int width = 5;
    final int height = 2;

    final ColoredPolygon polygon1 = new ColoredPolygon();
    polygon1.xpoints = new int[] { 0, width, width, 0 };
    polygon1.ypoints = new int[] { 0, 0, height / 2, height / 2 };
    polygon1.npoints = 4;
    polygon1.setColor(new Color(0x55, 0x00, 0xAA));

    final ColoredPolygon polygon2 = new ColoredPolygon();
    polygon2.xpoints = new int[] { 0, width, width, 0 };
    polygon2.ypoints = new int[] { height / 2, height / 2, height, height };
    polygon2.npoints = 4;
    polygon2.setColor(Color.RED);

    final List<ColoredPolygon> individual = new ArrayList<ColoredPolygon>();
    individual.add(polygon1);
    individual.add(polygon2);

    System.out.println(individual);

    double fitness = 0.0;
    try {
      fitness = function.rawFitness(individual);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
    assertEquals(0.0, fitness, 0.0);

    individual.remove(1);

    try {
      fitness = function.rawFitness(individual);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
    assertEquals(0xFF * width, fitness, 0.0);

  }

}
