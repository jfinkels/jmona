/**
 * ImageWriter.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * An utility class for outputting images.
 * 
 * @author jfinkels
 */
public final class ImageWriter {

  /** The identifier for the PNG image format. */
  public static final String IMAGE_FORMAT_PNG = "png";

  /**
   * Write the specified image to the specified file.
   * 
   * @param image
   *          The image to write.
   * @param filename
   *          The filename in which to write the image.
   * @throws IOException
   *           If there is a problem writing the image.
   */
  public static void writeImage(final RenderedImage image, final String filename)
      throws IOException {
    LOG.debug("Writing image " + image + " to filename " + filename);
    ImageIO.write(image, IMAGE_FORMAT_PNG, new File(filename));
  }
private static final transient Logger LOG = Logger.getLogger(ImageWriter.class);
  /** Instantiation disallowed. */
  protected ImageWriter() {
    // intentionally unimplemented
  }
}
