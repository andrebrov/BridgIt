package com.mingames.bridgit.service;

import org.mockito.internal.util.reflection.Whitebox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 31.01.13
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
public class ServerServiceTest {

    private ServerService serverService;
    private BridgitServer server;

    @BeforeTest
    public void setUp() {
        serverService = new ServerService();
        server = mock(BridgitServer.class);
        Whitebox.setInternalState(serverService, "server", server);
    }

    @Test
    public void shouldSendFindElementCommand() {
        BridgitCommand command = new BridgitCommand();
        serverService.sendFindElementCommand(command);
        verify(server).sendRequest(command);
    }


    @Test
    public void shouldReturnNotNullListByFindElementCommand() {
        BridgitCommand command = new BridgitCommand();
        List<WebElement> webElements = serverService.sendFindElementCommand(command);
        assertNotNull("Null list was returned", webElements);
    }

    @Test
    public void shouldReturnListWhenElementsExistsByFindElementCommand() {
        BridgitCommand command = new BridgitCommand();
        command.setFunction(BridgitCommand.FIND);
        command.setParam(By.id("someId").toString());
        String value = "[{\"attributes\":[{\"id\":\"someId\"}]},{\"attributes\":[]}]";
        when(server.sendRequest(command)).thenReturn(value);
        List<WebElement> webElements = serverService.sendFindElementCommand(command);
        assertTrue("List is empty", webElements.size() > 0);
        assertEquals("Wrong id", "someId", webElements.get(0).getAttribute("id"));
    }

    @Test
    public void shouldGetObject() {
        BridgitCommand command = new BridgitCommand();
        serverService.getObject(command);
        verify(server).sendRequest(command);
    }

}
