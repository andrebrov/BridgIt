package com.mingames.bridgit.driver;

import com.mingames.bridgit.service.BridgItCommand;
import com.mingames.bridgit.service.ServerService;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static com.mingames.bridgit.service.BridgItCommand.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:30
 * To change this template use File | Settings | File Templates.
 */
public class BridgitDriverTest {

    private BridgitDriver driver;
    private ServerService serverService;
    private BridgItCommand[] commands;
    public static final String URL = "someUrl";

    @DataProvider
    public Object[][] data() {
        Object[][] data = new Object[1][];
        data[0] = new Object[2];
        data[0][0] = By.xpath("//someXpath");
        data[0][1] = FIND;
        return data;
    }

    @BeforeMethod
    public void setUp() {
        driver = new BridgitDriver();
        driver.get(URL);
        serverService = mock(ServerService.class);
        Whitebox.setInternalState(driver, "serverService", serverService);
        commands = new BridgItCommand[]{null};
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                commands[0] = (BridgItCommand) arguments[0];
                BridgitWebElement element = new BridgitWebElement();
                return Collections.singletonList(element);
            }
        }).when(serverService).sendFindElementCommand((BridgItCommand) any(), eq(URL));
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                commands[0] = (BridgItCommand) arguments[0];
                return "somesource";
            }
        }).when(serverService).getObject((BridgItCommand) any(), eq(URL));
    }

    @Test
    public void shouldGetCurrentUrl() {
        driver.getCurrentUrl();
        verify(serverService).getObject((BridgItCommand) any(), eq(URL));
        BridgItCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), GET_URL);
    }

    @Test
    public void shouldGetTitle() {
        driver.getTitle();
        verify(serverService).getObject((BridgItCommand) any(), eq(URL));
        BridgItCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), GET_TITLE);
    }

    @Test(dataProvider = "data")
    public void shouldSendFindByCommand(By by, String commandName) {
        WebElement element = driver.findElement(by);
        verify(serverService).sendFindElementCommand((BridgItCommand) any(), eq(URL));
        BridgItCommand command = commands[0];
        assertEquals("Wrong command", commandName, command.getFunction());
        assertEquals("Wrong command param", by.toString(), command.getParam()[0]);
        assertEquals("Wrong by expression", by, ((BridgitWebElement) element).getFindBy());
    }

    @Test(dataProvider = "data")
    public void shouldSendFindByCommandForList(By by, String commandName) {
        WebElement element = driver.findElement(by);
        verify(serverService).sendFindElementCommand((BridgItCommand) any(), eq(URL));
        BridgItCommand command = commands[0];
        assertEquals("Wrong command", commandName, command.getFunction());
        assertEquals("Wrong command param", by.toString(), command.getParam()[0]);
        assertEquals("Wrong by expression", by, ((BridgitWebElement) element).getFindBy());
    }

    @Test
    public void shouldGetPageSource() {
        driver.getPageSource();
        verify(serverService).getObject((BridgItCommand) any(), eq(URL));
        BridgItCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), GET_SOURCE);
    }

    @Test
    public void shouldExecuteCommand() {
        BridgItCommand commandToSend = new BridgItCommand();
        commandToSend.setFunction(GET_SOURCE);
        String executionResult = driver.execute(commandToSend);
        verify(serverService).getObject((BridgItCommand) any(), eq(URL));
        BridgItCommand command = commands[0];
        assertEquals("Wrong execution result", "somesource", executionResult);
        assertEquals("Wrong command send", commandToSend, command);
    }


}
