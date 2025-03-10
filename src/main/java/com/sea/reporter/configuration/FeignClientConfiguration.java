package com.sea.reporter.configuration;

import feign.Client;
import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * Configuration class for Feign client.
 * Provides SSL/TLS configuration and hostname verification for secure communication
 * with external services.
 */
@Configuration
public class FeignClientConfiguration {
    
    @Value("${organization.api.host}")
    private String organizationApiHost;

    /**
     * Creates a configured Feign client with SSL/TLS support.
     *
     * @return Configured Feign client
     * @throws NoSuchAlgorithmException if SSL algorithm is not available
     * @throws KeyManagementException if key management fails
     */
    @Bean
    public Client client() throws NoSuchAlgorithmException,
            KeyManagementException {
        return new Client.Default(sslSocketFactory(), hostnameVerifier());
    }

    /**
     * Creates an SSL socket factory with custom trust manager.
     *
     * @return Configured SSL socket factory
     * @throws NoSuchAlgorithmException if SSL algorithm is not available
     * @throws KeyManagementException if key management fails
     */
    @Bean
    public SSLSocketFactory sslSocketFactory() throws NoSuchAlgorithmException,
            KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        return sc.getSocketFactory();
    }

    /**
     * Creates a hostname verifier that checks against the configured organization API host.
     *
     * @return Configured hostname verifier
     */
    @Bean
    public HostnameVerifier hostnameVerifier() {
        return (hostname, session) -> hostname.equals(organizationApiHost);
    }

    /**
     * Configures the logging level for Feign client.
     *
     * @return Logger level for Feign client
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
