package com.mingames.bridgit.nodeserver;

import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Mockito.mock;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 18.02.13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class NodeWebSocketTest {

    private NodeWebSocket socket;
    private Connection connection;
    private BridgItWebSocketHandler handler;

    @BeforeMethod
    public void setup() {
        socket = new NodeWebSocket();
        handler = mock(BridgItWebSocketHandler.class);
        socket.addHandler(handler);
        connection = mock(Connection.class);
        socket.onOpen(connection);
    }


}
