package com.mingames.bridgit.driver;

import com.mingames.bridgit.service.BridgitCommand;
import com.mingames.bridgit.service.ServerService;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
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
    private ServerService serviceService;
    private BridgitCommand[] commands;

    @DataProvider
    public Object[][] data() {
        Object[][] data = new Object[1][];
        data[0] = new Object[2];
        data[0][0] = By.xpath("//someXpath");
        data[0][1] = BridgitCommand.FIND;
        return data;
    }

    @BeforeTest
    public void setUp() {
        driver = new BridgitDriver();
        serviceService = mock(ServerService.class);
        Whitebox.setInternalState(driver, "serverService", serviceService);
        commands = new BridgitCommand[]{null};
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                commands[0] = (BridgitCommand) arguments[0];
                return null;
            }
        }).when(serviceService).sendFindElementCommand((BridgitCommand) any());
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                commands[0] = (BridgitCommand) arguments[0];
                return null;
            }
        }).when(serviceService).getObject((BridgitCommand) any());
    }

    @Test
    public void shouldGetCurrentUrl() {
        driver.getCurrentUrl();
        BridgitCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), BridgitCommand.GETURL);
    }

    @Test
    public void shouldGetTitle() {
        driver.getTitle();
        BridgitCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), BridgitCommand.GETTITLE);
    }

    @Test(dataProvider = "data")
    public void shouldSendFindByCommand(By by, String commandName) {
        driver.findElement(by);
        BridgitCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), commandName);
        assertEquals("Wrong command param", command.getParam(), by.toString());
    }

    @Test(dataProvider = "data")
    public void shouldSendFindByCommandForList(By by, String commandName) {
        driver.findElements(by);
        BridgitCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), commandName);
        assertEquals("Wrong command param", command.getParam(), by.toString());
    }

    @Test
    public void shouldGetPageSource() {
        driver.getPageSource();
        BridgitCommand command = commands[0];
        assertEquals("Wrong command", command.getFunction(), BridgitCommand.GETSOURCE);
    }


}
