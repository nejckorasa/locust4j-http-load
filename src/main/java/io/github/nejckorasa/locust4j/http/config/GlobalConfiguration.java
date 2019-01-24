package io.github.nejckorasa.locust4j.http.config;

public class GlobalConfiguration {

  /**
   * Locust master host, defaults to 127.0.0.1.
   */
  private String masterHost = "127.0.0.1";

  /**
   * Locust master port, defaults to 5557.
   */
  private int masterPort = 5557;

  /**
   * Max RPS that Locust4j can generate.
   */
  private int maxRPS = -1;

  /**
   * Base URL like http://127.0.0.1:8080/
   */
  private String baseUrl = null;

  /**
   * Should only do dry run
   */
  private boolean dryRun = false;

  public String getMasterHost() {
    return masterHost;
  }

  public void setMasterHost(final String masterHost) {
    this.masterHost = masterHost;
  }

  public int getMasterPort() {
    return masterPort;
  }

  public void setMasterPort(final int masterPort) {
    this.masterPort = masterPort;
  }

  public int getMaxRPS() {
    return maxRPS;
  }

  public void setMaxRPS(final int maxRPS) {
    this.maxRPS = maxRPS;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public boolean isDryRun() {
    return dryRun;
  }

  public void setDryRun(final boolean dryRun) {
    this.dryRun = dryRun;
  }

  @Override
  public String toString() {
    return String.format(
        "GlobalConfiguration{masterHost='%s', masterPort=%d, maxRPS=%d, baseUrl='%s', dryRun=%s}",
        masterHost,
        masterPort,
        maxRPS,
        baseUrl,
        dryRun);
  }
}
