package com.miraway.selfservice.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Objects;
import javax.net.ssl.*;

public class TLSServer {

    public void serve(
        int port,
        String tlsVersion,
        String trustStoreName,
        char[] trustStorePassword,
        String keyStoreName,
        char[] keyStorePassword
    ) throws Exception {
        Objects.requireNonNull(tlsVersion, "TLS version is mandatory");

        if (port <= 0) {
            throw new IllegalArgumentException("Port number cannot be less than or equal to 0");
        }
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        //        InputStream trustInputStream = TLSServer.class.getClass().getResourceAsStream(trustStoreName);
        File file = new File(trustStoreName);
        InputStream trustInputStream = new FileInputStream(file);
        trustStore.load(trustInputStream, trustStorePassword);
        //        trustInputStream.close();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream keyInputStore = new FileInputStream(keyStoreName);
        keyStore.load(keyInputStore, keyStorePassword);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, keyStorePassword);
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), SecureRandom.getInstanceStrong());

        SSLServerSocketFactory factory = ctx.getServerSocketFactory();
        try (ServerSocket listener = factory.createServerSocket(port)) {
            SSLServerSocket sslListener = (SSLServerSocket) listener;

            sslListener.setNeedClientAuth(true);
            sslListener.setEnabledProtocols(new String[] { tlsVersion });
            // NIO to be implemented
            while (true) {
                try (Socket socket = sslListener.accept()) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hello World!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
