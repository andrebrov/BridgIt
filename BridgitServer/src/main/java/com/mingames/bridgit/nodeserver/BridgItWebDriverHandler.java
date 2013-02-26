package com.mingames.bridgit.nodeserver;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 26.02.13
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BridgItWebDriverHandler extends AbstractHandler {

    @Autowired
    private InteractionContainer container;

    private Logger logger = Logger.getLogger(BridgItWebDriverHandler.class);


    public static final String DATA = "data";
    public static final String COMMAND = "command";

    public void handle(String target, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        String parameter = request.getParameter(COMMAND);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        if ("auth".equals(parameter)) {
            String remoteHost = request.getRemoteHost();
            container.addDriverHost(remoteHost);
            logger.info(remoteHost + " registred.");
        } else if ("action".equals(parameter)) {
            String data = request.getParameter("data");
            logger.info("New action, data: " + data);
            String result;
            try {
                result = container.sendAction(data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            PrintWriter writer = httpServletResponse.getWriter();
            writer.print(result);
        } else {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            logger.error("Wrong command: " + parameter);
            httpServletResponse.getWriter().println("<h1>Wrong request</h1>");
        }
    }
}
