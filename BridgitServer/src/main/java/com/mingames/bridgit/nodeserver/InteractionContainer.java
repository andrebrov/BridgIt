package com.mingames.bridgit.nodeserver;

import org.eclipse.jetty.websocket.WebSocket;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 26.02.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
@Component
public class InteractionContainer {

    private NodeWebSocket socket;
    private String host;
    private String data;

    public void addDriverHost(String host) {
        this.host = host;
    }

    public String sendAction(String data) throws IOException, InterruptedException {
        socket.sendMessage(data);
        int counter = 0;
        while (data == null || counter < 1000) {
            counter++;
            Thread.sleep(10);
        }
        String temp = this.data;
        this.data = null;
        return temp;
    }

    public void setSocket(WebSocket socket) {
        this.socket = (NodeWebSocket) socket;
    }

    public void processResponseFromSocket(String data) {
        this.data = data;
    }
}
