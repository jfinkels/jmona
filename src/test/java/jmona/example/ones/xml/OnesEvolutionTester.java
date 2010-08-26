/**
 * OnesEvolutionTester.java
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
package jmona.example.ones.xml;

import jfcommon.test.TestUtils;
import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.DeepCopyableList;
import jmona.EvolutionException;
import jmona.PopulationEvolutionContext;
import jmona.impl.mutable.MutableByte;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for an evolution of the Ones example.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class OnesEvolutionTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(OnesEvolutionTester.class);

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<DeepCopyableList<MutableByte>> completionCondition = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private PopulationEvolutionContext<DeepCopyableList<MutableByte>> context = null;

  /** Test method for a Ones evolution. */
  @Test
  @DirtiesContext
  public final void testOnesEvolution() {
    try {
      LOG.debug("initial population: " + this.context.currentPopulation());
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();
        LOG.debug("Generation " + this.context.currentGeneration() + ": "
            + this.context.currentPopulation());
      }
    } catch (NullPointerException exception) {
      TestUtils.fail(exception);
    } catch (final CompletionException exception) {
      TestUtils.fail(exception);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }
}
