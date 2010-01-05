/**
 * Main.java
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
package jmona.driver;

import java.util.Map;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.PostProcessor;
import jmona.ProcessingException;
import jmona.impl.Util;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Runs an arbitrary evolution from an XML application context specified by the
 * <code>--config</code> option.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class Main {
  /** The long name of the option for specifying a configuration file. */
  public static final String OPT_CONFIG_FILE_LONG = "config";
  /**
   * The identifying name of the option for specifying a configuration file
   * (either the long option name or the short option name).
   */
  public static final String OPT_CONFIG_FILE = OPT_CONFIG_FILE_LONG;
  /** The default File containing the configuration for the evolution. */
  public static final String OPT_CONFIG_FILE_DEFAULT = "./context.xml";
  /** The description of the option for specifying a configuration file. */
  public static final String OPT_CONFIG_FILE_DESC = "The Spring configuration file containing the evolution context.";
  /** The parser for command line options. */
  private static final OptionParser PARSER = new OptionParser();

  /**
   * Run the {@link EvolutionContext#stepGeneration()} method until the
   * CompletionConditionon is met, executing any PostProcessors after each
   * generation step.
   * 
   * Provide the location of the Spring XML configuration file by using the
   * <em>--config</em> command line option, followed by the location of the
   * configuration file. By default, this program will look for a configuration
   * file at <code>./context.xml</code>.
   * 
   * @param args
   *          The command-line arguments to this program.
   */
  @SuppressWarnings("unchecked")
  public static void main(final String[] args) {
    // set up the list of options which the parser accepts
    setUpParser();

    // get the options from the command line arguments
    final OptionSet options = PARSER.parse(args);

    // get the location of the XML configuration file
    final String configFile = (String) options.valueOf(OPT_CONFIG_FILE);

    // create an application context from the specified XML configuration file
    final ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
        configFile);

    // get the evolution contexts, completion criteria, and post processors
    final Map<String, EvolutionContext> evolutionContextsMap = applicationContext
        .getBeansOfType(EvolutionContext.class);
    final Map<String, CompletionCondition> completionCriteriaMap = applicationContext
        .getBeansOfType(CompletionCondition.class);
    final Map<String, PostProcessor> postProcessorMap = applicationContext
        .getBeansOfType(PostProcessor.class);

    // assert that there is only one evolution context bean in the app. context
    if (evolutionContextsMap.size() != 1) {
      throw new RuntimeException("Application context contains "
          + evolutionContextsMap.size()
          + " EvolutionContext beans, but must contain only 1.");
    }

    // assert that there is only one completion criteria bean in the app context
    if (completionCriteriaMap.size() != 1) {
      throw new RuntimeException("Application context contains "
          + completionCriteriaMap.size()
          + "CompletionCondition beans, but must contain only 1.");
    }

    // get the evolution context and completion condition from their maps
    final EvolutionContext evolutionContext = Util
        .firstValue(evolutionContextsMap);
    final CompletionCondition completionCondition = Util
        .firstValue(completionCriteriaMap);

    try {
      // while the criteria has not been satisfied, create the next generation
      while (!completionCondition.isSatisfied(evolutionContext)) {
        // create the next generation in the evolution
        evolutionContext.stepGeneration();

        // perform all post-processing on the evolution context
        for (final PostProcessor postProcessor : postProcessorMap.values()) {
          postProcessor.process(evolutionContext);
        }
      }
    } catch (final CompletionException exception) {
      throw new RuntimeException(exception);
    } catch (final EvolutionException exception) {
      throw new RuntimeException(exception);
    } catch (final ProcessingException exception) {
      throw new RuntimeException(exception);
    }
  }

  /** Establish the set of options which the command line parser accepts. */
  private static void setUpParser() {
    // "--config=/path/to/configfile.xml"
    PARSER.accepts(OPT_CONFIG_FILE_LONG, OPT_CONFIG_FILE_DESC)
        .withRequiredArg().ofType(String.class).defaultsTo(
            OPT_CONFIG_FILE_DEFAULT);
  }

  /** Instantiation disallowed. */
  protected Main() {
    // intentionally unimplemented
  }
}
