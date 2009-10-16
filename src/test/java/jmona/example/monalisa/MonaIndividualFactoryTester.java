/**
 * MonaIndividualFactoryTester.java
 */
package jmona.example.monalisa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MonaIndividualFactory class.
 * 
 * @author jeff
 */
// TODO strict inequality or non-strict inequality?
public class MonaIndividualFactoryTester {

  /** The factory under test. */
  private MonaIndividualFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new MonaIndividualFactory();
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {
    MonaIndividual individual = this.factory.createIndividual();

    assertEquals(MonaIndividualFactory.DEFAULT_INITIAL_POLYGONS, individual
        .gene().size());
    Polygon polygon = null;
    Color color = null;
    for (final Entry<Polygon, Color> entry : individual.gene().entrySet()) {
      // get the current polygon
      polygon = entry.getKey();

      // number of points in the polygon must be between default min and max
      assertTrue(polygon.npoints < MonaIndividualFactory.DEFAULT_MAX_POINTS);
      assertTrue(polygon.npoints >= MonaIndividualFactory.DEFAULT_MIN_POINTS);

      // iterate over each coordinate pair in the polygon
      for (int i = 0; i < polygon.npoints; ++i) {
        // each coordinate must be between 0 and default max
        assertTrue(polygon.xpoints[i] >= 0
            && polygon.xpoints[i] < MonaIndividualFactory.DEFAULT_MAX_X);
        assertTrue(polygon.ypoints[i] >= 0
            && polygon.ypoints[i] < MonaIndividualFactory.DEFAULT_MAX_Y);
      }

      // each component of the color must be between 0 and max
      color = entry.getValue();
      assertTrue(color.getRed() >= 0
          && color.getRed() < MonaIndividualFactory.MAX_VALUE);
      assertTrue(color.getGreen() >= 0
          && color.getGreen() < MonaIndividualFactory.MAX_VALUE);
      assertTrue(color.getBlue() >= 0
          && color.getBlue() < MonaIndividualFactory.MAX_VALUE);
      assertTrue(color.getAlpha() >= 0
          && color.getAlpha() < MonaIndividualFactory.MAX_VALUE);
    }

  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#setAlphaValue(int)}.
   */
  @Test
  public void testSetAlphaValue() {
    final int alphaValue = 66;
    this.factory.setAlphaValue(alphaValue);
    // assert that the alpha value is as assigned
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#setInitialPolygons(int)}
   * .
   */
  @Test
  public void testSetInitialPolygons() {
    final int newInitialPolygons = 10;
    this.factory.setInitialPolygons(newInitialPolygons);
    final MonaIndividual individual = this.factory.createIndividual();
    assertEquals(newInitialPolygons, individual.gene().size());
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#setMaxPoints(int)}.
   */
  @Test
  public void testSetMaxPoints() {
    final int newMaxPoints = 5;
    this.factory.setMaxPoints(newMaxPoints);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual.gene().keySet()) {
      assertTrue(polygon.npoints < newMaxPoints);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#setMaxX(int)}.
   */
  @Test
  public void testSetMaxX() {
    final int newMaxX = 10;
    this.factory.setMaxX(newMaxX);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual.gene().keySet()) {
      for (int i = 0; i < polygon.npoints; ++i) {
        assertTrue(polygon.xpoints[i] < newMaxX);
        assertTrue(polygon.xpoints[i] >= 0);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#setMaxY(int)}.
   */
  @Test
  public void testSetMaxY() {
    final int newMaxY = 10;
    this.factory.setMaxY(newMaxY);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual.gene().keySet()) {
      for (int i = 0; i < polygon.npoints; ++i) {
        assertTrue(polygon.ypoints[i] < newMaxY);
        assertTrue(polygon.ypoints[i] >= 0);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaIndividualFactory#setMinPoints(int)}.
   */
  @Test
  public void testSetMinPoints() {
    final int newMinPoints = 5;
    this.factory.setMinPoints(newMinPoints);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual.gene().keySet()) {
      assertTrue(polygon.npoints >= newMinPoints);
    }
  }

}
