package com.miraway.selfservice.config;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TLSClient {

    public String request(
        InetAddress serverHost,
        int serverPort,
        String tlsVersion,
        String trustStoreName,
        char[] trustStorePassword,
        String keyStoreName,
        char[] keyStorePassword
    ) throws Exception {
        Logger logger = LoggerFactory.getLogger(TLSClient.class);

        Objects.requireNonNull(tlsVersion, "TLS version is mandatory");

        Objects.requireNonNull(serverHost, "Server host cannot be null");

        if (serverPort <= 0) {
            throw new IllegalArgumentException("Server port cannot be lesss than or equal to 0");
        }

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        //        InputStream trustInputStream = TLSClient.class.getClass().getResourceAsStream(trustStoreName);
        File file = new File(trustStoreName);
        InputStream trustInputStream = new FileInputStream(file);
        trustStore.load(trustInputStream, trustStorePassword);
        //        trustInputStream.close();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream keyInputStream = new FileInputStream(keyStoreName);
        keyStore.load(keyInputStream, keyStorePassword);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, keyStorePassword);

        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), SecureRandom.getInstanceStrong());
        SocketFactory factory = ctx.getSocketFactory();

        try (Socket connection = factory.createSocket(serverHost, serverPort)) {
            connection.setSoTimeout(60000);
            ((SSLSocket) connection).setEnabledProtocols(new String[] { tlsVersion });
            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            ((SSLSocket) connection).setSSLParameters(sslParams);
            logger.info("Log info TLS Client {}", connection.getInputStream().read());

            return connection.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
