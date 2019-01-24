package io.github.nejckorasa.locust4j.http.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("FieldAccessedSynchronizedAndUnsynchronized")
public class ConfigurationContext {

  private static final Logger logger = LogManager.getLogger(ConfigurationContext.class);
  private static GlobalConfiguration configuration = null;

  private static boolean initialized = false;

  private ConfigurationContext() { }

  public static synchronized void init(final String[] args) throws Exception {

    if (initialized) {
      throw new Exception("GlobalConfiguration had been initialized, don't do it again.");
    }

    final Options options = new Options();
    options.addOption("masterHost", true, "Locust master host, defaults to 127.0.0.1");
    options.addOption("masterPort", true, "Locust master port, defaults to 5557");
    options.addOption("maxRPS", true, "Max RPS that can generate, disabled by default");
    options.addOption("baseUrl", true, "Base URL like http://127.0.0.1:8080/");

    final CommandLineParser parser = new DefaultParser();
    final CommandLine cmd = parser.parse(options, args, false);

    configuration = new GlobalConfiguration();

    if (cmd.hasOption("masterHost")) {
      configuration.setMasterHost(cmd.getOptionValue("masterHost"));
    }
    if (cmd.hasOption("masterPort")) {
      configuration.setMasterPort(Integer.valueOf(cmd.getOptionValue("masterPort")));
    }
    if (cmd.hasOption("maxRPS")) {
      configuration.setMaxRPS(Integer.valueOf(cmd.getOptionValue("maxRPS")));
    }

    configuration.setBaseUrl(cmd.getOptionValue("baseUrl"));

    logger.info("Configuration: \n" + configuration);

    initialized = true;
  }

  public static GlobalConfiguration getConfiguration() {
    return configuration;
  }

  public static void setConfiguration(final GlobalConfiguration configuration) {
    ConfigurationContext.configuration = configuration;
  }
}
