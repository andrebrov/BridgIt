package com.mingames.bridgit.service;

import com.mingames.bridgit.driver.BridgitWebElement;
import org.mockito.internal.util.reflection.Whitebox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.mingames.bridgit.driver.BridgitDriverTest.URL;
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
    private BridgitServerConnector server;

    @BeforeTest
    public void setUp() {
        serverService = new ServerService();
        server = mock(BridgitServerConnector.class);
        Whitebox.setInternalState(serverService, "server", server);
    }

    @Test
    public void shouldSendFindElementCommand() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(BridgItCommand.GET_URL);
        serverService.sendFindElementCommand(command, URL);
        verify(server).sendRequest(command, URL);
    }


    @Test
    public void shouldReturnNotNullListByFindElementCommand() {
        BridgItCommand command = new BridgItCommand();
        List<WebElement> webElements = serverService.sendFindElementCommand(command, URL);
        assertNotNull("Null list was returned", webElements);
    }

    @Test
    public void shouldReturnListWhenElementsExistsByFindElementCommand() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(BridgItCommand.FIND);
        By someId = By.id("someId");
        command.setParam(someId.toString());
        String value = "[{\"fullpath\":\"someId\"}]";
        when(server.sendRequest(command, URL)).thenReturn(value);
        List<WebElement> webElements = serverService.sendFindElementCommand(command, URL);
        assertTrue("List is empty", webElements.size() > 0);
        BridgitWebElement webElement = (BridgitWebElement) webElements.get(0);
        assertEquals("Wrong fullpath", "someId", webElement.getFullPath());
    }

    @Test
    public void shouldGetObject() {
        BridgItCommand command = new BridgItCommand();
        serverService.getObject(command, URL);
        verify(server).sendRequest(command, URL);
    }

}
