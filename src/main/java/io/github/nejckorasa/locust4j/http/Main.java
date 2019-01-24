package io.github.nejckorasa.locust4j.http;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.myzhan.locust4j.AbstractTask;
import com.github.myzhan.locust4j.Locust;
import io.github.nejckorasa.locust4j.http.config.ConfigurationContext;
import io.github.nejckorasa.locust4j.http.task.GetExampleTask;
import io.github.nejckorasa.locust4j.http.task.PostExampleTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);

  private Main() { }

  public static void main(final String[] args) {

    try {
      ConfigurationContext.init(args);

      // override options here

      ConfigurationContext.getConfiguration().setBaseUrl("http://localhost:7778/rest/");
      ConfigurationContext.getConfiguration().setMasterPort(5557);
      ConfigurationContext.getConfiguration().setMasterHost("127.0.0.1");
      ConfigurationContext.getConfiguration().setDryRun(false);
    }
    catch (final Exception ex) {
      logger.error("Failed to initialize GlobalConfiguration, will exit now", ex);
    }

    final Locust locust = Locust.getInstance();
    locust.setVerbose(true);
    locust.setMasterHost(ConfigurationContext.getConfiguration().getMasterHost());
    locust.setMasterPort(ConfigurationContext.getConfiguration().getMasterPort());

    if (ConfigurationContext.getConfiguration().getMaxRPS() > 0) {
      // enable max RPS control
      locust.setMaxRPS(ConfigurationContext.getConfiguration().getMaxRPS());
    }

    // define tasks here
    final AbstractTask[] tasks = {
        new GetExampleTask(30, "538743863"),
        new PostExampleTask(5, "427420394"),
        new PostExampleTask(5, "46324312"),
        new PostExampleTask(5, "538743855")
    };

    logger.info("Running tasks \n" + Arrays.stream(tasks)
        .map(t -> "(" + t.getWeight() + ", " + t.getName() + ") \n")
        .collect(Collectors.joining("[", ",", "]")));

    if (ConfigurationContext.getConfiguration().isDryRun()) {
      locust.dryRun(tasks);
    }
    else {
      locust.run(tasks);
    }
  }
}
