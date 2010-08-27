/**
 * TSPEvolutionTester.java
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
package jmona.example.tsp;

import jfcommon.functional.MappingException;
import jfcommon.test.TestUtils;
import jmona.CompletionCondition;
import jmona.DeepCopyableList;
import jmona.EvolutionException;
import jmona.PopulationEvolutionContext;
import jmona.impl.mutable.MutableInteger;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test of the travelling salesman problem evolution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TSPEvolutionTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TSPEvolutionTester.class);

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<DeepCopyableList<MutableInteger>> completionCondition = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private PopulationEvolutionContext<DeepCopyableList<MutableInteger>> context = null;

  /** Test method for the traveling salesman problem evolution. */
  @Test
  @DirtiesContext
  public final void testTSPEvolution() {
    try {
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();
        LOG.debug("Generation " + this.context.currentGeneration() + ": "
            + this.context.currentPopulation());
      }
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    } catch (final ArrayIndexOutOfBoundsException exception) {
      TestUtils.fail(exception);
    }
  }
}
