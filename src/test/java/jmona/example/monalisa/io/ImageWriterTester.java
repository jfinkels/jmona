/**
 * ImageWriterTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.example.monalisa.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

/**
 * Test class for the ImageWriter class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ImageWriterTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(ImageWriterTester.class);

  /** The filename at which to write a test image file. */
  public static final String OUTPUT_FILENAME = "target/testoutput.png";

  /** Perform cleanup after each test. */
  @After
  public final void tearDown() {
    final File outputFile = new File(OUTPUT_FILENAME);
    if (outputFile.exists() && !outputFile.delete()) {
      LOG.debug("Failed to delete output file at " + OUTPUT_FILENAME);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.io.ImageWriter#writeImage(java.awt.image.RenderedImage, String)}
   * .
   */
  @Test
  public void testWriteImage() {
    new ImageWriter();

    final BufferedImage image = new BufferedImage(100, 100,
        BufferedImage.TYPE_INT_ARGB_PRE);
    try {
      ImageWriter.writeImage(image, OUTPUT_FILENAME);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }
  }
}
