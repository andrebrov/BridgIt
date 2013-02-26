package com.mingames.bridgit.nodeserver;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 01.02.13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BridgItWebSocketHandler extends WebSocketHandler {

    @Autowired
    private InteractionContainer container;

    private WebSocket webSocket;

    public WebSocket doWebSocketConnect(HttpServletRequest request,
                                        String protocol) {

        NodeWebSocket nodeWebSocket = new NodeWebSocket();
        nodeWebSocket.addHandler(this);
        return nodeWebSocket;
    }

    public void setWebSocket(WebSocket socket) {
        this.webSocket = socket;
        container.setSocket(socket);
    }

    public void process(String data) {
        container.processResponseFromSocket(data);
    }
}
