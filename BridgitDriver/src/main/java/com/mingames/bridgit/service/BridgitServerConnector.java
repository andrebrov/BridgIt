package com.mingames.bridgit.service;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 31.01.13
 * Time: 17:10
 */
public class BridgitServerConnector {

    private static BridgitServerConnector instance;
    private final HttpClient client;

    private BridgitServerConnector() {
        client = new HttpClient();

    }

    public static BridgitServerConnector getInstance() {
        if (instance == null) {
            instance = new BridgitServerConnector();
        }
        return instance;
    }

    public Object sendRequest(BridgItCommand command, String url) {
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        method.getParams().setParameter("data", command);
        Object result = null;
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            result = method.getResponseBody();
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return result;
    }
}
