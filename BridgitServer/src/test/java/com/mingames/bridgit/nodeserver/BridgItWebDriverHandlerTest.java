package com.mingames.bridgit.nodeserver;

import org.eclipse.jetty.server.Request;
import org.powermock.reflect.Whitebox;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 26.02.13
 * Time: 12:21
 */
public class BridgItWebDriverHandlerTest {

    private BridgItWebDriverHandler handler;
    private Request request;
    private MockHttpServletRequest httpServletRequest;
    private MockHttpServletResponse httpServletResponse;
    private InteractionContainer container;

    @BeforeMethod
    public void setup() {
        handler = new BridgItWebDriverHandler();
        request = mock(Request.class);
        httpServletRequest = new MockHttpServletRequest();
        httpServletResponse = new MockHttpServletResponse();
        container = mock(InteractionContainer.class);
        Whitebox.setInternalState(handler, "container", container);
    }

    @Test
    public void shouldParseCommand() throws IOException, ServletException {
        handler.handle("url", request, httpServletRequest, httpServletResponse);
        verify(request).getParameter(BridgItWebDriverHandler.COMMAND);
    }

    @Test
    public void shouldProcessAuth() throws IOException, ServletException {
        when(request.getRemoteHost()).thenReturn("localhost");
        when(request.getParameter(BridgItWebDriverHandler.COMMAND)).thenReturn("auth");
        handler.handle("url", request, httpServletRequest, httpServletResponse);
        verify(container).addDriverHost("localhost");
    }

    @Test
    public void shouldSentMessageToSocketForActionCommandViaContainer() throws IOException, ServletException, InterruptedException {
        when(request.getParameter(BridgItWebDriverHandler.COMMAND)).thenReturn("action");
        when(request.getParameter(BridgItWebDriverHandler.DATA)).thenReturn("somedata");
        handler.handle("url", request, httpServletRequest, httpServletResponse);
        verify(request).getParameter(BridgItWebDriverHandler.DATA);
        verify(container).sendAction("somedata");
    }

    @Test
    public void shouldRespondActionResultToDriver() throws IOException, ServletException, InterruptedException {
        when(request.getParameter(BridgItWebDriverHandler.COMMAND)).thenReturn("action");
        when(request.getParameter(BridgItWebDriverHandler.DATA)).thenReturn("somedata");
        when(container.sendAction("somedata")).thenReturn("result");
        MockHttpServletResponse response = mock(MockHttpServletResponse.class);
        when(response.getWriter()).thenReturn(mock(PrintWriter.class));
        handler.handle("url", request, httpServletRequest, response);
        verify(response).getWriter();
    }
}
