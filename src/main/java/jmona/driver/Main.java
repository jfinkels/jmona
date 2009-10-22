/**
 * Main.java
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
package jmona.driver;

import jmona.CompletionCriteria;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Runs the image-matching evolution.
 * 
 * @author jeff
 */
public class Main {
  /** The name of the CompletionCriteria bean. */
  public static final String COMPLETION_CRITERIA_NAME = "completionCriteria";
  /** The name of the EvolutionContext bean. */
  public static final String EVOLUTION_CONTEXT_NAME = "evolutionContext";
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger.getLogger(Main.class);
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
   * Run the image-matching evolution.
   * 
   * Provide the location of the Spring XML configuration file by using the
   * <em>--config</em> command line option, followed by the location of the
   * configuration file. By default, this program will look for a configuration
   * file at <code>./context.xml</code>.
   * 
   * @param args
   *          The command-line arguments to this program.
   * @throws EvolutionException
   *           If there is some serious problem during the evolution.
   */
  @SuppressWarnings("unchecked")
  public static void main(final String[] args) throws EvolutionException {
    // set up the list of options which the parser accepts
    setUpParser();

    // get the options from the command line arguments
    final OptionSet options = PARSER.parse(args);

    // get the location of the XML configuration file
    final String configFile = (String) options.valueOf(OPT_CONFIG_FILE);

    // create an application context from the specified XML configuration file
    final ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
        configFile);

    // get the evolution context and completion criteria from the app. context
    final EvolutionContext evolutionContext = (EvolutionContext) applicationContext
        .getBean(EVOLUTION_CONTEXT_NAME, EvolutionContext.class);
    final CompletionCriteria completionCriteria = (CompletionCriteria) applicationContext
        .getBean(COMPLETION_CRITERIA_NAME, CompletionCriteria.class);

    // while the criteria has not been satisfied, create the next generation
    while (!completionCriteria.isSatisfied(evolutionContext)) {
      evolutionContext.stepGeneration();
    }
  }

  /** Establish the set of options which the command line parser accepts. */
  private static void setUpParser() {
    // --config=/path/to/configfile.xml
    PARSER.accepts(OPT_CONFIG_FILE_LONG, OPT_CONFIG_FILE_DESC)
        .withRequiredArg().ofType(String.class).defaultsTo(
            OPT_CONFIG_FILE_DEFAULT);
  }

  /** Instantiation disallowed. */
  protected Main() {
    // intentionally unimplemented
  }
}
