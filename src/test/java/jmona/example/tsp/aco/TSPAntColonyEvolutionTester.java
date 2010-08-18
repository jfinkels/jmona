/**
 * TSPAntColonyEvolution.java
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
package jmona.example.tsp.aco;

import jmona.CompletionCondition;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.MappingException;
import jmona.PostProcessor;
import jmona.ProcessingException;
import jmona.aco.impl.WorkerAnt;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for the ant colony optimization for the traveling salesman
 * problem.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TSPAntColonyEvolutionTester {
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TSPAntColonyEvolutionTester.class);

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<WorkerAnt> completionCondition = null;
  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private EvolutionContext<WorkerAnt> context = null;

  /** Get the PathLoggingPostProcessor from the XML configuration file. */
  @Autowired
  private PostProcessor<WorkerAnt> processor = null;

  /**
   * Test method for the ant colony optimization of the traveling salesman
   * problem evolution.
   */
  @Test
  @DirtiesContext
  public final void testTSPEvolution() {

    try {
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();
        this.processor.process(this.context);
      }
    } catch (final MappingException exception) {
      Util.fail(exception);
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    } catch (final NullPointerException exception) {
      Util.fail(exception);
    } catch (final ProcessingException exception) {
      Util.fail(exception);
    }
  }
}
