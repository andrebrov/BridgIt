package com.mingames.bridgit.service;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 01.02.13
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class BridgitServerConnectorTest {

    public void shouldSendRequest() {
        BridgitServerConnector connector = BridgitServerConnector.getInstance();
        BridgItCommand command = new BridgItCommand();
        connector.sendRequest(command);
    }
}
