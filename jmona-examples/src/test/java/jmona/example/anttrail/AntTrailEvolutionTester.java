/**
 * AntTrailEvolutionTester.java
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
package jmona.example.anttrail;

import jfcommon.test.TestUtils;
import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionException;
import jmona.GeneticEvolutionContext;
import jmona.Processor;
import jmona.ProcessingException;
import jmona.gp.Tree;
import jmona.impl.processing.FitnessLoggingProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for the ant trail genetic programming evolution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AntTrailEvolutionTester {
  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<Tree, GeneticEvolutionContext<Tree>> completionCondition = null;
  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private GeneticEvolutionContext<Tree> context = null;

  /** Test the evolution. */
  @Test
  @DirtiesContext
  public final void testEvolution() {
    final Processor<Tree, GeneticEvolutionContext<Tree>> processor = new FitnessLoggingProcessor<Tree, GeneticEvolutionContext<Tree>>();

    try {
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();
        processor.process(this.context);
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
