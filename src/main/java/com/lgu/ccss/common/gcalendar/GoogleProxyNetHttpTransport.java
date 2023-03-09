package com.lgu.ccss.common.gcalendar;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

/**
 * Utilities for Google APIs based on {@link NetHttpTransport}.
 *
 * @since 1.14
 * @author Yaniv Inbar
 */
public class GoogleProxyNetHttpTransport {

  /**
   * Returns a new instance of {@link NetHttpTransport} that uses
   * {@link GoogleUtils#getCertificateTrustStore()} for the trusted certificates using
   * {@link com.google.api.client.http.javanet.NetHttpTransport.Builder#trustCertificates(KeyStore)}
   * .
   *
   * <p>
   * This helper method doesn't provide for customization of the {@link NetHttpTransport}, such as
   * the ability to specify a proxy. To do use, use
   * {@link com.google.api.client.http.javanet.NetHttpTransport.Builder}, for example:
   * </p>
   *
   * <pre>
  static HttpTransport newProxyTransport() throws GeneralSecurityException, IOException {
    NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
    builder.trustCertificates(GoogleUtils.getCertificateTrustStore());
    builder.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 3128)));
    return builder.build();
  }
   * </pre>
   */
  public static NetHttpTransport newTrustedTransport(String proxyHost, int proxyPort)
      throws GeneralSecurityException, IOException {
	  Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    return new NetHttpTransport.Builder().setProxy(proxy).trustCertificates(GoogleUtils.getCertificateTrustStore())
        .build();
  }

  private GoogleProxyNetHttpTransport() {
  }
}
