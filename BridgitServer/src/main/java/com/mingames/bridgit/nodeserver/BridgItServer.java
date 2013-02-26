package com.mingames.bridgit.nodeserver;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 01.02.13
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BridgItServer {

    private static ApplicationContext context;

    public void startServer() {

        try {
            Server server = new Server(8081);

            ContextHandler webSocketContext = new ContextHandler();
            Handler bridgItWebSocketHandler = context.getBean(BridgItWebSocketHandler.class);
            webSocketContext.setHandler(bridgItWebSocketHandler);
            webSocketContext.setResourceBase(".");
            webSocketContext.setClassLoader(Thread.currentThread().getContextClassLoader());

            ContextHandler webDriverContext = new ContextHandler();
            webDriverContext.setContextPath("/driver");
            Handler bridgItWebDriverHandler = context.getBean(BridgItWebDriverHandler.class);
            webDriverContext.setHandler(bridgItWebDriverHandler);
            webDriverContext.setResourceBase(".");
            webDriverContext.setClassLoader(Thread.currentThread().getContextClassLoader());

            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.setHandlers(new Handler[]
                    {webSocketContext, webDriverContext});

            server.setHandler(contexts);
            server.start();
            server.join();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("application.xml");
        BridgItServer bridgItServer = context.getBean(BridgItServer.class);
        bridgItServer.startServer();
    }

}
