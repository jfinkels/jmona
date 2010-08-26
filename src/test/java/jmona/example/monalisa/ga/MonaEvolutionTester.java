/**
 * MonaEvolutionTester.java
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
package jmona.example.monalisa.ga;

import jfcommon.test.TestUtils;
import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.DeepCopyableList;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.PostProcessor;
import jmona.ProcessingException;
import jmona.example.monalisa.ColoredPolygon;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for an evolution of the Mona example.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MonaEvolutionTester {

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<DeepCopyableList<ColoredPolygon>> completionCondition = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private EvolutionContext<DeepCopyableList<ColoredPolygon>> context = null;

  /** Get the PostProcessor from the Spring XML configuration file. */
  @Autowired
  private PostProcessor<DeepCopyableList<ColoredPolygon>> processor = null;

  /** Perform cleanup after each test. */
  @After
  public final void tearDown() {
    // possible delete the output files here
  }

  /** Test method for a Mona evolution. */
  @Test
  @DirtiesContext
  public final void testEvolution() {
    try {
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();
        this.processor.process(this.context);
      }
    } catch (final CompletionException exception) {
      TestUtils.fail(exception);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    } catch (final ProcessingException exception) {
      TestUtils.fail(exception);
    }
  }
}
