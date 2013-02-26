package com.mingames.bridgit.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

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
    private String hostUrl = "http://localhost:8081/driver";

    private BridgitServerConnector() {
        client = new HttpClient();
        GetMethod method = new GetMethod(hostUrl);
        method.setQueryString(new NameValuePair[]{new NameValuePair("command", "auth")});
        executeMethod(method, new Object());
    }

    public static BridgitServerConnector getInstance() {
        if (instance == null) {
            instance = new BridgitServerConnector();
        }
        return instance;
    }

    public Object sendRequest(BridgItCommand command) {
        GetMethod method = new GetMethod(hostUrl);
        method.setQueryString(new NameValuePair[]{
                new NameValuePair("command", "action"),
                new NameValuePair("data", command.toString())}
        );
        Object result = null;
        result = executeMethod(method, result);
        return result;
    }

    private Object executeMethod(GetMethod method, Object result) {
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            result = method.getResponseBodyAsString();
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
