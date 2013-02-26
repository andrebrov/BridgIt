package com.mingames.bridgit.nodeserver;

import org.eclipse.jetty.websocket.WebSocket;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 18.02.13
 * Time: 16:08
 */
public class NodeWebSocket implements WebSocket.OnTextMessage {

    private Connection connection;

    private BridgItWebSocketHandler webSocketHandler;

    public void onOpen(Connection connection) {
        this.connection = connection;
        webSocketHandler.setWebSocket(this);
    }

    public void onMessage(String data) {
        webSocketHandler.process(data);
    }

    public void onClose(int closeCode, String message) {
        webSocketHandler.setWebSocket(null);
    }

    public void addHandler(BridgItWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void sendMessage(String data) throws IOException {
        connection.sendMessage(data);
    }
}