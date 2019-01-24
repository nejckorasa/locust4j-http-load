package io.github.nejckorasa.locust4j.http.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Creates and holds singelton instance of CloseableHttpClient
 */
public class HttpClientSingleton {

  private HttpClientSingleton() { }

  public static CloseableHttpClient getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private static class SingletonHolder {

    private static final CloseableHttpClient INSTANCE = createInstance();

    private static CloseableHttpClient createInstance() {

      final HttpClientBuilder builder = HttpClientBuilder.create();

      final PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
      manager.setMaxTotal(1024);
      manager.setDefaultMaxPerRoute(1024);
      builder.setConnectionManager(manager);

      return builder.build();
    }
  }
}
